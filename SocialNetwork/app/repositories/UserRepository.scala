package repositories

import dtos.{OtherUserProfileDTO, UserDataDTO}
import models.User
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.{GetResult, JdbcProfile}
import slick.lifted.ProvenShape
import utils.Util

import java.text.SimpleDateFormat
import java.time.LocalDate
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}


class UserRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  implicit val getUserResult: AnyRef with GetResult[User] = GetResult(r =>
    User(id = r.nextLong(), name = r.nextString(), surname = r.nextString(), username = r.nextString(), password = r.nextString(),
      email = r.nextString(), dateOfBirth = r.nextDate().toLocalDate, profilePicture = r.nextString()))

  implicit val getUserDTOResult: AnyRef with GetResult[UserDataDTO] = GetResult(r =>
    UserDataDTO(id = r.nextLong(), name = r.nextString(), surname = r.nextString(), username = r.nextString(), email = r.nextString(),
      dateOfBirth = new SimpleDateFormat(Util.DATE_FORMAT).format(r.nextDate()), profilePicture = r.nextString()))

  implicit val getUserWithFriendshipStatusResult: AnyRef with GetResult[OtherUserProfileDTO] = GetResult(r =>
    OtherUserProfileDTO(userData = UserDataDTO(id = r.nextLong(), name = r.nextString(), surname = r.nextString(),
      username = r.nextString(), email = r.nextString(), dateOfBirth = new SimpleDateFormat(Util.DATE_FORMAT).format(r.nextDate()),
      profilePicture = r.nextString()), friendshipStatus = r.nextString()))


  def getById(id: Long): Future[Option[User]] = {
    db.run(userTable.filter(_.id === id).result.headOption)
  }

  def add(user: User): Future[User] = {
    val userWithId =
      (userTable returning userTable.map(_.id)
        into ((us, id) => us.copy(id = id))) += user
    db.run(userWithId)
  }

  def update(user: User): Future[Int] = {
    db.run(userTable.filter(_.id === user.id)
      .map(x => (x.name, x.surname, x.email, x.dateOfBirth, x.profilePicture))
      .update(user.name, user.surname, user.email, user.dateOfBirth, user.profilePicture))
  }

  def getUsersFriends(id: Long): Future[Seq[User]] = {
    db.run(
      sql"""
           select user_id, name, surname, username, password, email, date_of_birth, profile_picture from users
           where user_id in (
               select sender_id from friend_reqs where receiver_id = $id and status = 'ACCEPTED' union
               select receiver_id from friend_reqs where sender_id = $id and status = 'ACCEPTED'
           )
      """.as[User])
  }

  def getByUsername(username: String): Future[Option[User]] = {
    db.run(userTable.filter(_.username === username).result.headOption)
  }

  def search(term: String, myId: Long): Future[Seq[User]] = {
    db.run(userTable.filter(user => {
      (user.id =!= myId) && ((user.name like s"%$term%") || (user.surname like s"%$term%"))
    }).result)
  }

  def getUserDataDTO(id: Long): Future[Option[UserDataDTO]] = {
    db.run(
      sql"""
            select user_id, name, surname, username, email, date_of_birth, profile_picture
            from users where user_id = $id
         """.as[UserDataDTO].headOption)
  }

  def changePassword(id: Long, newPassword: String): Future[Int] = {
    val userPw = for {u <- userTable if u.id === id} yield u.password
    db.run(userPw.update(newPassword))
  }


  class UserTable(tag: Tag) extends Table[User](tag, "USERS") {
    def id: Rep[Long] = column[Long]("USER_ID", O.PrimaryKey, O.AutoInc)

    def name: Rep[String] = column[String]("NAME")

    def surname: Rep[String] = column[String]("SURNAME")

    def username: Rep[String] = column[String]("USERNAME")

    def password: Rep[String] = column[String]("PASSWORD")

    def email: Rep[String] = column[String]("EMAIL")

    def dateOfBirth: Rep[LocalDate] = column[LocalDate]("DATE_OF_BIRTH")

    def profilePicture: Rep[String] = column[String]("PROFILE_PICTURE")

    override def * : ProvenShape[User] = (id, name, surname, username, password, email, dateOfBirth, profilePicture) <> (User.tupled, User.unapply)
  }

  val userTable = TableQuery[UserTable]
}
