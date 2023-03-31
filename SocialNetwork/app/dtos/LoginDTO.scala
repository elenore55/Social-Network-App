package dtos

import play.api.libs.json.{Json, OFormat}

case class LoginDTO(
  username: String = "",
  password: String = ""
)

object LoginDTO {
  implicit val loginFormat: OFormat[LoginDTO] = Json.format[LoginDTO]
}