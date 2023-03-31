package services

import dtos.{ChangePasswordDTO, CreateUserDTO}
import exceptions.{IncorrectOldPasswordException, InvalidLoginDataException, ObjectNotFoundException, UserAlreadyRegisteredException}
import models.User
import org.mindrot.jbcrypt.BCrypt
import pdi.jwt.JwtSession
import play.api.Configuration
import play.api.libs.json.{Json, OFormat}
import repositories.UserRepository
import utils.Util

import java.time.{Clock, LocalDate}
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class AuthenticationService @Inject()(userRepository: UserRepository)(implicit executionContext: ExecutionContext) {

  def loginUser(username: String, password: String): Future[JwtSession] = {
    val optionUser: Future[Option[User]] = userRepository.getByUsername(username)
    optionUser flatMap {
      case Some(value) =>
        if (!BCrypt.checkpw(password, value.password))
          throw InvalidLoginDataException()
        else {
          implicit val clock: Clock = Clock.systemUTC
          implicit val conf: Configuration = Configuration.reference
          implicit val formatUser: OFormat[User] = Json.format[User]
          var session = JwtSession()
          session = session + ("user", value)
          Future.successful {
            session
          }
        }
      case None => throw InvalidLoginDataException()
    }
  }

  def registerUser(dto: CreateUserDTO): Future[User] = {
    val optionUser: Future[Option[User]] = userRepository.getByUsername(dto.username)
    optionUser flatMap {
      case Some(_) =>
        throw UserAlreadyRegisteredException()
      case None =>
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(Util.DATE_FORMAT)
        val user: User = User(name = dto.name, surname = dto.surname, username = dto.username,
          email = dto.email, dateOfBirth = LocalDate.parse(dto.dateOfBirth, dtf),
          profilePicture = dto.profilePicture)
        val newPass: String = BCrypt.hashpw(dto.password, BCrypt.gensalt())
        val newUser: User = user.copy(password = newPass)
        userRepository.add(newUser)
    }
  }

  def changePassword(id: Long, dto: ChangePasswordDTO): Unit = {
    val optionUser: Future[Option[User]] = userRepository.getById(id)
    optionUser flatMap {
      case Some(value) =>
        if (!BCrypt.checkpw(dto.oldPassword, value.password))
          throw IncorrectOldPasswordException()
        else {
          userRepository.changePassword(id, BCrypt.hashpw(dto.newPassword, BCrypt.gensalt()))
        }
      case None => throw ObjectNotFoundException()
    }
  }
}
