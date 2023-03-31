package dtos

import play.api.libs.json.{Json, OFormat}

case class CreateUserDTO (
   name: String,
   surname: String,
   username: String,
   password: String,
   email: String,
   dateOfBirth: String,
   profilePicture: String
)

object CreateUserDTO {
  implicit val createUserFormat: OFormat[CreateUserDTO] = Json.format[CreateUserDTO]
}