package controllers

import dtos.{CommentDTO, CreateCommentDTO}
import play.api.libs.json.{JsError, JsResult, JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import security.SecuredAction
import services.CommentService
import services.validation.CommentValidationService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CommentController @Inject()(cc: ControllerComponents, commentService: CommentService, securedAction: SecuredAction)
                                 (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  def create: Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val commentResult: JsResult[CreateCommentDTO] = request.body.validate[CreateCommentDTO]
    commentResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      comment => {
        CommentValidationService.validate(comment) match {
          case Left(errorMessage) => Future.successful {
            BadRequest(Json.obj("message" -> errorMessage))
          }
          case Right(_) =>
            commentService.add(comment, request.userId) map {
              c => Ok(Json.toJson(c))
            }
        }
      }
    )
  }

  def getComments(postId: Long): Action[AnyContent] = securedAction.async { implicit request =>
    val future: Future[Seq[CommentDTO]] = commentService.getComments(postId)
    future map {
      comments => Ok(Json.toJson(comments))
    }
  }

  def update: Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val commentResult: JsResult[CommentDTO] = request.body.validate[CommentDTO]
    commentResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      comment => {
        CommentValidationService.validate(comment) match {
          case Left(errorMessage) => Future.successful {
            BadRequest(Json.obj("message" -> errorMessage))
          }
          case Right(_) =>
            commentService.update(comment) map {
              c => Ok(Json.toJson(c))
            }
        }
      }
    )
  }

  def remove(id: Long): Action[AnyContent] = securedAction.async { implicit request =>
    commentService.remove(id) map {
      _ => NoContent
    }
  }

}
