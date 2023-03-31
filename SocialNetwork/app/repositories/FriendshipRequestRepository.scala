package repositories

import dtos.{FriendshipRequestDTO, UserDataDTO}
import models.RequestStatus.RequestStatus
import models.{FriendshipRequest, RequestStatus}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.ast.BaseTypedType
import slick.jdbc.{GetResult, JdbcProfile, JdbcType}
import slick.lifted.ProvenShape
import utils.Util

import java.time.LocalDateTime
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}
import scala.language.implicitConversions

class FriendshipRequestRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  implicit val getReqDTOResult: AnyRef with GetResult[FriendshipRequestDTO] = GetResult(r =>
    FriendshipRequestDTO(id = r.nextLong(), otherUser = new UserDataDTO(id = r.nextLong(), name = r.nextString(),
      surname = r.nextString(), username = r.nextString(), email = r.nextString(), profilePicture = r.nextString()),
      dateTime = Util.SQLTextToDateTimeString(r.nextString()), status = r.nextString())
  )


  def getReceivedRequests(id: Long): Future[Seq[FriendshipRequestDTO]] = {
    db.run(
      sql"""
            select f.req_id, u.user_id, name, surname, username, email, profile_picture, date_time, status
            from friend_reqs f join users u on f.sender_id = u.USER_ID
            where f.receiver_id = $id and status = 'PENDING'
         """.as[FriendshipRequestDTO])
  }

  def getSentRequests(id: Long): Future[Seq[FriendshipRequestDTO]] = {
    db.run(
      sql"""
            select f.req_id, u.user_id, name, surname, username, email, profile_picture, date_time, status
            from friend_reqs f join users u on f.receiver_id = u.USER_ID
            where f.sender_id = $id and status = 'PENDING'
         """.as[FriendshipRequestDTO])
  }

  def add(request: FriendshipRequest): Future[FriendshipRequest] = {
    val reqWithId =
      (friendshipRequestTable returning friendshipRequestTable.map(_.id)
        into ((fr, id) => fr.copy(id = id))) += request
    db.run(reqWithId)
  }

  def getRequestBetween(user1Id: Long, user2Id: Long): Future[Option[FriendshipRequest]] = {
    db.run(friendshipRequestTable.filter(
      req => {
        (req.senderId === user1Id && req.receiverId === user2Id) ||
          (req.senderId === user2Id && req.receiverId === user1Id)
      }
    ).result.headOption)
  }

  def getById(id: Long): Future[Option[FriendshipRequest]] = {
    db.run(friendshipRequestTable.filter(_.id === id).result.headOption)
  }

  def update(id: Long, s: String): Future[Int] = {
    db.run(
      sql"""
            update friend_reqs
            set status = $s where req_id = $id and status = 'PENDING'
         """.as[Int].head)
  }


  class FriendshipRequestTable(tag: Tag) extends Table[FriendshipRequest](tag, "FRIEND_REQS") {
    def id: Rep[Long] = column[Long]("REQ_ID", O.PrimaryKey, O.AutoInc)

    def senderId: Rep[Long] = column[Long]("SENDER_ID")

    def receiverId: Rep[Long] = column[Long]("RECEIVER_ID")

    def dateTime: Rep[LocalDateTime] = column[LocalDateTime]("DATE_TIME")

    def status: Rep[RequestStatus] = column[RequestStatus]("STATUS")

    override def * : ProvenShape[FriendshipRequest] = (id, senderId, receiverId, dateTime, status) <> (FriendshipRequest.tupled, FriendshipRequest.unapply)
  }

  implicit val statusMapper: JdbcType[RequestStatus] with BaseTypedType[RequestStatus] = MappedColumnType.base[RequestStatus, String](
    e => e.toString,
    s => RequestStatus.withName(s)
  )
  val friendshipRequestTable = TableQuery[FriendshipRequestTable]
}
