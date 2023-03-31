package services

import models.Reaction
import repositories.ReactionRepository

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ReactionService @Inject()(reactionRepository: ReactionRepository) (implicit executionContext: ExecutionContext){

  def add(reaction: Reaction): Future[Unit] = {
    reactionRepository.add(reaction) map {
      _ => ()
    }
  }

  def remove(reaction: Reaction): Future[Unit] = {
    reactionRepository.remove(reaction) map {
      _ => ()
    }
  }

  def remove(postId: Long, userId: Long): Future[Unit] = {
    reactionRepository.remove(postId, userId) map {
      _ => ()
    }
  }
}
