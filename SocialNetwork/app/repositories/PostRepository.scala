package repositories

import dtos.{PostDTO, UserDataDTO}
import models.Post
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.{GetResult, JdbcProfile}
import slick.lifted.ProvenShape
import utils.Util

import java.time.LocalDateTime
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PostRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  implicit val getPostDTOResult: AnyRef with GetResult[PostDTO] = GetResult(r =>
    PostDTO(id = r.nextLong(), content = r.nextString(), created = Util.SQLTextToDateTimeString(r.nextString()),
      lastUpdated = Util.SQLTextToDateTimeString(r.nextString()), points = r.nextInt(), myPoints = r.nextInt(),
      user = UserDataDTO(id = r.nextLong(), name = r.nextString(), surname = r.nextString(), username = r.nextString(),
        profilePicture = r.nextString())))


  def getFriendsPosts(id: Long): Future[Seq[PostDTO]] = {
    db.run(
      sql"""
            select p.post_id, content, created, last_updated, coalesce(sum(points), 0),
                   coalesce(sum(case when r.user_id = $id then r.points else 0 end), 0),
                   u.user_id, name, surname, username, profile_picture
            from reactions r
            right outer join posts p on r.post_id = p.post_id
            join users u on p.user_id = u.user_id
            where u.user_id in (
                select sender_id from friend_reqs
                where receiver_id = $id union
                select receiver_id from friend_reqs
                where sender_id = $id
            )
            group by post_id, last_updated
            order by last_updated desc
         """.as[PostDTO])
  }

  def add(post: Post): Future[Post] = {
    val postWithId =
      (postTable returning postTable.map(_.id)
        into ((p, id) => p.copy(id = id))) += post
    db.run(postWithId)
  }

  def update(post: PostDTO): Future[Int] = {
    db.run(postTable.filter(_.id === post.id).map(x => (x.content, x.lastUpdated)).update(post.content, LocalDateTime.now()))
  }

  def remove(id: Long): Future[Int] = {
    db.run(postTable.filter(_.id === id).delete)
  }

  def getById(id: Long): Future[Option[Post]] = {
    db.run(postTable.filter(_.id === id).result.headOption)
  }

  def getUsersPosts(id: Long): Future[Seq[PostDTO]] = {
    db.run(
      sql"""
            select p.post_id, content, created, last_updated, coalesce(sum(points), 0),
                   coalesce(sum(case when r.user_id = $id then r.points else 0 end), 0),
                   u.user_id, name, surname, username, profile_picture
            from reactions r
            right outer join posts p on r.post_id = p.post_id
            join users u on p.user_id = u.user_id
            where u.user_id = $id
            group by post_id, last_updated
            order by last_updated desc
         """.as[PostDTO])
  }


  class PostTable(tag: Tag) extends Table[Post](tag, "POSTS") {
    def id: Rep[Long] = column[Long]("POST_ID", O.PrimaryKey, O.AutoInc)

    def content: Rep[String] = column[String]("CONTENT")

    def userId: Rep[Long] = column[Long]("USER_ID")

    def created: Rep[LocalDateTime] = column[LocalDateTime]("CREATED")

    def lastUpdated: Rep[LocalDateTime] = column[LocalDateTime]("LAST_UPDATED")

    override def * : ProvenShape[Post] = (id, content, userId, created, lastUpdated) <> (Post.tupled, Post.unapply)
  }

  val postTable = TableQuery[PostTable]
}
