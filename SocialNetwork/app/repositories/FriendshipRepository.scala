package repositories

import models.Friendship
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import slick.lifted.ProvenShape

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class FriendshipRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  class FriendshipTable(tag: Tag) extends Table[Friendship](tag, "FRIENDSHIPS") {
    def id: Rep[Long] = column[Long]("FRIENDSHIP_ID", O.PrimaryKey, O.AutoInc)
    def user1Id: Rep[Long] = column[Long]("USER1_ID")
    def user2Id: Rep[Long] = column[Long]("USER2_ID")

    override def * : ProvenShape[Friendship] = (id, user1Id, user2Id) <> (Friendship.tupled, Friendship.unapply)
  }
  val friendshipTable = TableQuery[FriendshipTable]
}
