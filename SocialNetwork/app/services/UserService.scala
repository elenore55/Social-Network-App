package services

import dtos.{FriendshipStatus, OtherUserProfileDTO, UserDataDTO}
import exceptions.ObjectNotFoundException
import models.{FriendshipRequest, RequestStatus, User}
import repositories.{FriendshipRequestRepository, UserRepository}
import utils.Util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserService @Inject()(userRepository: UserRepository, friendshipRequestRepository: FriendshipRequestRepository)
                           (implicit executionContext: ExecutionContext) {

  def getById(id: Long): Future[UserDataDTO] = {
    val optionUser: Future[Option[User]] = userRepository.getById(id)
    optionUser flatMap {
      case Some(value: User) => Future.successful {
        convertToDTO(value)
      }
      case None => throw ObjectNotFoundException()
    }
  }

  def getUserWithFriendshipStatus(otherId: Long, myId: Long): Future[OtherUserProfileDTO] = {
    val optionUser: Future[Option[User]] = userRepository.getById(otherId)
    optionUser flatMap {
      case Some(value: User) => Future.successful {
        val userData: UserDataDTO = convertToDTO(value)
        val optionRequest: Future[Option[FriendshipRequest]] = friendshipRequestRepository.getRequestBetween(otherId, myId)
        optionRequest flatMap {
          case Some(value: FriendshipRequest) =>
            getOtherUserProfile(myId, userData, value)
          case None => Future.successful {
            OtherUserProfileDTO(userData = userData, friendshipStatus = FriendshipStatus.NOT_FRIENDS)
          }
        }
      } flatMap (x => x)
      case None => throw ObjectNotFoundException()
    }
  }

  def update(user: UserDataDTO): Future[UserDataDTO] = {
    userRepository.update(convertFromDTO(user)) map (_ => user)
  }

  def getUsersFriends(id: Long): Future[Seq[UserDataDTO]] = {
    userRepository.getUsersFriends(id) map {
      users => users map (x => convertToDTO(x))
    }
  }

  def search(term: String, myId: Long): Future[Seq[UserDataDTO]] = {
    userRepository.search(term, myId) map {
      users => users map (x => convertToDTO(x))
    }
  }


  private def getOtherUserProfile(myId: Long, userData: UserDataDTO, value: FriendshipRequest): Future[OtherUserProfileDTO] = {
    if (value.status == RequestStatus.ACCEPTED) Future.successful {
      OtherUserProfileDTO(userData = userData, friendshipStatus = FriendshipStatus.FRIENDS)
    }
    else if (value.status == RequestStatus.REJECTED) Future.successful {
      OtherUserProfileDTO(userData = userData, friendshipStatus = FriendshipStatus.NOT_FRIENDS)
    }
    else if (value.senderId == myId) Future.successful {
      OtherUserProfileDTO(userData = userData, friendshipStatus = FriendshipStatus.REQUEST_SENT)
    }
    else Future.successful {
      OtherUserProfileDTO(userData = userData, friendshipStatus = FriendshipStatus.REQUEST_RECEIVED)
    }
  }

  private def convertToDTO(user: User): UserDataDTO = {
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(Util.DATE_FORMAT)
    val dateOfBirthStr = dtf.format(user.dateOfBirth)
    val dto: UserDataDTO = UserDataDTO(user.id, user.name, user.surname, username = user.username, email = user.email,
      dateOfBirth = dateOfBirthStr, profilePicture = user.profilePicture)
    dto
  }

  private def convertFromDTO(dto: UserDataDTO): User = {
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(Util.DATE_FORMAT)
    User(id = dto.id, name = dto.name, surname = dto.surname, username = dto.username, email = dto.email,
      dateOfBirth = LocalDate.parse(dto.dateOfBirth, dtf), profilePicture = dto.profilePicture)
  }
}
