package controllers

import dtos.{ChangePasswordDTO, CreateUserDTO, LoginDTO}
import pdi.jwt.JwtSession
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, ControllerComponents, Request}
import security.SecuredAction
import services.AuthenticationService
import services.validation.UserDataValidationService

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class LoginController @Inject()(cc: ControllerComponents, authService: AuthenticationService, securedAction: SecuredAction)(implicit executionContext: ExecutionContext)
  extends AbstractController(cc) {

  def login(): Action[JsValue] = Action(parse.json).async { implicit request =>
    val loginResult: JsResult[LoginDTO] = request.body.validate[LoginDTO]
    loginResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      login => {
        try {
          val future: Future[JwtSession] = authService.loginUser(login.username, login.password)
          future map {
            session: JwtSession => Ok(Json.obj("jwt" -> session.serialize))
          }
        } catch {
          case e: Exception => Future.successful {
            BadRequest(Json.obj("message" -> e.getMessage))
          }
        }
      }
    )
  }

  def register(): Action[JsValue] = Action(parse.json).async { implicit request: Request[JsValue] =>
    val registerResult: JsResult[CreateUserDTO] = request.body.validate[CreateUserDTO]
    registerResult.fold(
      errors => {
        Future.successful {
          BadRequest(Json.obj("message" -> JsError.toJson(errors)))
        }
      },
      register => {
        UserDataValidationService.validate(register) match {
          case Left(errorMessage) => Future.successful {
            BadRequest(Json.obj("message" -> errorMessage))
          }
          case Right(_) =>
            try {
              val future = authService.registerUser(register)
              future map {
                _ => Ok(Json.obj("message" -> "registration successful"))
              }
            }
            catch {
              case e: Exception =>
                Future.successful {
                  BadRequest(Json.obj("message" -> e.getMessage))
                }
            }
        }
      }
    )
  }

  def changePassword: Action[JsValue] = securedAction(parse.json) { implicit request =>
    val id: Long = request.userId
    val passwordResult: JsResult[ChangePasswordDTO] = request.body.validate[ChangePasswordDTO]
    passwordResult.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      password => {
        try {
          authService.changePassword(id, password)
          Ok(Json.obj("message" -> "Password changed"))
        }
        catch {
          case e: Exception => BadRequest(Json.obj("message" -> e.getMessage))
        }
      }
    )
  }
}
