package repositories

import dtos.{CommentDTO, UserDataDTO}
import models.Comment
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.{GetResult, JdbcProfile}
import slick.lifted.ProvenShape
import utils.Util

import java.time.LocalDateTime
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CommentRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._

  implicit val getCommentDTOResult: AnyRef with GetResult[CommentDTO] = GetResult(r =>
    CommentDTO(id = r.nextLong(), content = r.nextString(), postId = r.nextLong(), user = UserDataDTO(id = r.nextLong(),
      name = r.nextString(), surname = r.nextString(), username = r.nextString(), profilePicture = r.nextString()),
      created = Util.SQLTextToDateTimeString(r.nextString()), lastUpdated = Util.SQLTextToDateTimeString(r.nextString()))
  )


  def add(comment: Comment): Future[Comment] = {
    val commentWithId =
      (commentTable returning commentTable.map(_.id)
        into ((c, id) => c.copy(id = id))) += comment
    db.run(commentWithId)
  }

  def update(comment: CommentDTO): Future[Int] = {
    db.run(commentTable.filter(_.id === comment.id).map(x => (x.content, x.lastUpdated)).update(comment.content, LocalDateTime.now()))
  }

  def getComments(postId: Long): Future[Seq[CommentDTO]] = {
    db.run(
      sql"""
            select c.comment_id, content, post_id, u.user_id, name, surname, username, profile_picture, created, last_updated
            from comments c join users u on u.user_id = c.user_id
            where post_id = $postId
         """.as[CommentDTO])
  }

  def remove(id: Long): Future[Int] = {
    db.run(commentTable.filter(_.id === id).delete)
  }


  class CommentTable(tag: Tag) extends Table[Comment](tag, "COMMENTS") {
    def id: Rep[Long] = column[Long]("COMMENT_ID", O.PrimaryKey, O.AutoInc)
    def content: Rep[String] = column[String]("CONTENT")
    def postId: Rep[Long] = column[Long]("POST_ID")
    def userId: Rep[Long] = column[Long]("USER_ID")
    def created: Rep[LocalDateTime] = column[LocalDateTime]("CREATED")
    def lastUpdated: Rep[LocalDateTime] = column[LocalDateTime]("LAST_UPDATED")

    override def * : ProvenShape[Comment] = (id, content, postId, userId, created, lastUpdated) <> (Comment.tupled, Comment.unapply)
  }

  val commentTable = TableQuery[CommentTable]
}
