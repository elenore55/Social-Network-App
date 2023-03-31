package repositories

import models.Reaction
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReactionRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {

  import profile.api._


  def add(reaction: Reaction): Future[Reaction] = {
    remove(reaction.postId, reaction.userId)
    val reactWithId =
      (reactionTable returning reactionTable.map(_.id)
        into ((r, id) => r.copy(id = id))) += reaction
    db.run(reactWithId)
  }

  def remove(reaction: Reaction): Future[Int] = {
    db.run(reactionTable.filter(_.id === reaction.id).delete)
  }

  def remove(postId: Long, userId: Long): Future[Int] = {
    db.run(reactionTable.filter(_.postId === postId).filter(_.userId === userId).delete)
  }


  class ReactionTable(tag: Tag) extends Table[Reaction](tag, "REACTIONS") {
    def id: Rep[Long] = column[Long]("REACTION_ID", O.PrimaryKey, O.AutoInc)

    def points: Rep[Int] = column[Int]("POINTS")

    def postId: Rep[Long] = column[Long]("POST_ID")

    def userId: Rep[Long] = column[Long]("USER_ID")

    override def * : ProvenShape[Reaction] = (id, points, postId, userId) <> (Reaction.tupled, Reaction.unapply)
  }

  val reactionTable = TableQuery[ReactionTable]
}
