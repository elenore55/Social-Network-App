package controllers

import dtos.{CreatePostDTO, PostDTO}
import play.api.libs.json.{JsError, JsResult, JsValue, Json}
import play.api.mvc._
import security.SecuredAction
import services.validation.PostValidationService
import services.{PostService, UserService}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

@Singleton
class PostController @Inject()(cc: ControllerComponents, postService: PostService, userService: UserService, securedAction: SecuredAction)
                              (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  def create: Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val postResult: JsResult[CreatePostDTO] = request.body.validate[CreatePostDTO]
    postResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      post => {
        PostValidationService.validate(post) match {
          case  Left(errorMessage) => Future.successful {
            BadRequest(Json.obj("message" -> errorMessage))
          }
          case Right(_) =>
            postService.add(post, request.userId) map {
              p => Ok(Json.toJson(p))
            }
        }
      }
    )
  }

  def getMyPosts: Action[AnyContent] = securedAction.async { implicit request =>
    val id: Long = request.userId
    val future: Future[Seq[PostDTO]] = postService.getUsersPosts(id)
    future map {
      posts => Ok(Json.toJson(posts))
    }
  }

  def getUsersPosts(id: Long): Action[AnyContent] = securedAction.async { implicit request =>
    val future: Future[Seq[PostDTO]] = postService.getUsersPosts(id)
    future map {
      posts => Ok(Json.toJson(posts))
    }
  }

  def getFriendsPosts: Action[AnyContent] = securedAction.async { implicit request =>
    val id: Long = request.userId
    val future: Future[Seq[PostDTO]] = postService.getFriendsPosts(id)
    future map {
      posts => Ok(Json.toJson(posts))
    }
  }

  def update: Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val postResult: JsResult[PostDTO] = request.body.validate[PostDTO]
    postResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      post => {
        PostValidationService.validate(post) match {
          case Left(errorMessage) => Future.successful {
            BadRequest(Json.obj("message" -> errorMessage))
          }
          case Right(_) =>
            postService.update(post) map {
              p => Ok(Json.toJson(p))
            }
        }
      }
    )
  }

  def remove(id: Long ): Action[AnyContent] = securedAction.async { implicit request =>
    val future = postService.remove(id)
    future map {
      _ => NoContent
    }
  }
}
