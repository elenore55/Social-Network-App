package controllers

import dtos.{CreateFriendshipRequestDTO, FriendshipRequestDTO}
import play.api.libs.json.{JsError, JsResult, JsValue, Json}
import play.api.mvc._
import security.SecuredAction
import services.FriendshipRequestService
import services.validation.FriendshipRequestValidationService

import javax.inject.Inject
import scala.concurrent._

class FriendshipRequestController @Inject()(cc: ControllerComponents, friendshipRequestService: FriendshipRequestService, securedAction: SecuredAction)
                                           (implicit executionContext: ExecutionContext) extends AbstractController(cc) {

  def getReceivedRequests: Action[AnyContent] = securedAction.async { implicit request =>
    val id: Long = request.userId
    val future: Future[Seq[FriendshipRequestDTO]] = friendshipRequestService.getReceivedRequests(id)
    future map {
      reqs: Seq[FriendshipRequestDTO] => Ok(Json.toJson(reqs))
    }
  }

  def getSentRequests: Action[AnyContent] = securedAction.async { implicit request =>
    val id: Long = request.userId
    val future: Future[Seq[FriendshipRequestDTO]] = friendshipRequestService.getSentRequests(id)
    future map {
      reqs: Seq[FriendshipRequestDTO] => Ok(Json.toJson(reqs))
    }
  }

  def create: Action[JsValue] = securedAction(parse.json).async { implicit request =>
    val reqResult: JsResult[CreateFriendshipRequestDTO] = request.body.validate[CreateFriendshipRequestDTO]

    reqResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      req => {
        val future: Future[FriendshipRequestDTO] = friendshipRequestService.add(req, request.userId)
        future map {
          r => Ok(Json.toJson(r))
        }
      }
    )
  }

  def changeStatus(id: Long, status: String): Action[AnyContent] = securedAction.async { implicit request =>
    FriendshipRequestValidationService.validateStatus(status) match {
      case Left(errorMessage) => Future.successful {
        BadRequest(Json.obj("message" -> errorMessage))
      }
      case Right(_) =>
        try {
          // future recover instead of try catch
          val future = friendshipRequestService.changeStatus(id, status)
          future map {
            _ => Ok(Json.obj("message" -> "status changed"))
          }
        }
        catch {
          case e: Exception => Future.successful {
            BadRequest(Json.obj("message" -> e.getMessage))
          }
        }
    }
  }
}
