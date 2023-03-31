package controllers

import dtos.{OtherUserProfileDTO, UserDataDTO}
import exceptions.ObjectNotFoundException
import play.api.libs.json.{JsError, JsResult, JsValue, Json}
import play.api.mvc._
import security.SecuredAction
import services.UserService
import services.validation.UserDataValidationService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class UserController @Inject()(cc: ControllerComponents, userService: UserService, securedAction: SecuredAction)(implicit executionContext: ExecutionContext)
  extends AbstractController(cc) {

  def getOtherUserData(id: Long): Action[AnyContent] = securedAction.async { implicit request =>
    val myId: Long = request.userId
    if (id == myId) {
      Future.successful {
        Redirect("/users/me/profile")
      }
    }
    try {
      val future = userService.getUserWithFriendshipStatus(id, myId)
      future map {
        user: OtherUserProfileDTO => Ok(Json.toJson(user))
      }
    }
    catch {
      case e1: ObjectNotFoundException => Future.successful {
        NotFound(Json.obj("message" -> e1.getMessage))
      }
      case e2: Exception => Future.successful {
        BadRequest(Json.obj("message" -> e2.getMessage))
      }
    }
  }

  def getMyData: Action[AnyContent] = securedAction.async { implicit request =>
    val id: Long = request.userId
    try {
      val future = userService.getById(id)
      future map {
        user: UserDataDTO => Ok(Json.toJson(user))
      }
    }
    catch {
      case e1: ObjectNotFoundException => Future.successful {
        NotFound(Json.obj("message" -> e1.getMessage))
      }
      case e2: Exception => Future.successful {
        BadRequest(Json.obj("message" -> e2.getMessage))
      }
    }
  }

  def updateUserData(): Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val userDataResult: JsResult[UserDataDTO] = request.body.validate[UserDataDTO]
    userDataResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      userData => {
        UserDataValidationService.validate(userData) match {
          case Left(errorMessage) => Future.successful {
            BadRequest(Json.obj("message" -> errorMessage))
          }
          case Right(_) =>
            userService.update(userData) map {
              data => Ok(Json.toJson(data))
            }
        }
      }
    )
  }

  def getUsersFriends: Action[AnyContent] = securedAction.async { implicit request =>
    val id: Long = request.userId
    val future: Future[Seq[UserDataDTO]] = userService.getUsersFriends(id)
    future map {
      friends => Ok(Json.toJson(friends))
    }
  }

  def search(term: String): Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val id: Long = request.userId
    val future: Future[Seq[UserDataDTO]] = userService.search(term, id)
    future map {
      users => Ok(Json.toJson(users))
    }
  }
}
