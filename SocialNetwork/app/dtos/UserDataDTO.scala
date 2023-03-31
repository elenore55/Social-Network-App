package dtos

import play.api.libs.json.{Json, OFormat}

case class UserDataDTO (
  id: Long = -1,
  name: String = "",
  surname: String = "",
  username: String = "",
  email: String = "",
  dateOfBirth: String = "",
  profilePicture: String = ""
)

object UserDataDTO {
  implicit val userDataFormat: OFormat[UserDataDTO] = Json.format[UserDataDTO]
}
