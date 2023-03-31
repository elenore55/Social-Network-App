package security

import pdi.jwt._
import play.api.Configuration
import play.api.libs.json.JsValue
import play.api.mvc._
import services.UserService

import java.time.Clock
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

case class UserRequest[A](userId: Long, request: Request[A])
  extends WrappedRequest[A](request)

class SecuredAction @Inject()(val parser: BodyParsers.Default, userService: UserService)(implicit val executionContext: ExecutionContext)
  extends ActionBuilder[UserRequest, AnyContent] {

  override def invokeBlock[A](request: Request[A],
                              block: UserRequest[A] => Future[Result]): Future[Result] = {
    try {
      val jwt: String = request.headers.get("Authorization").fold("")(identity)
      val token: String = jwt.split(" ")(1)

      implicit val clock: Clock = Clock.systemUTC
      implicit val conf: Configuration = Configuration.reference
      val user: Option[JsValue] = JwtSession.deserialize(token).get("user")
      val userId: JsValue = user.get("id")
      block(UserRequest(userId.toString().toLong, request))
    }
    catch {
      case _: Exception => Future.successful(Results.Unauthorized("Unauthorized access"))
    }
  }
}