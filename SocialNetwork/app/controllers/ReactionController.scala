package controllers

import models.Reaction
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import security.SecuredAction
import services.ReactionService

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class ReactionController @Inject()(cc: ControllerComponents, reactionService: ReactionService, securedAction: SecuredAction)
                                  (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  def like(postId: Long): Action[AnyContent] = securedAction.async { implicit request =>
    val userId: Long = request.userId
    val reaction: Reaction = Reaction(id = -1, points = 1, postId = postId, userId = userId)
    val future = reactionService.add(reaction)
    future map {
      _ => NoContent
    }
  }

  def dislike(postId: Long): Action[AnyContent] = securedAction.async { implicit request =>
    val userId: Long = request.userId
    val reaction: Reaction = Reaction(id = -1, points = -1, postId = postId, userId = userId)
    val future = reactionService.add(reaction)
    future map {
      _ => NoContent
    }
  }

  def removeReaction(postId: Long): Action[AnyContent] = securedAction.async { implicit request =>
    val userId: Long = request.userId
    val future = reactionService.remove(postId, userId)
    future map {
      _ => NoContent
    }
  }
}
