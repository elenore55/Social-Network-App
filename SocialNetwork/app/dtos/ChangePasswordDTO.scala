package dtos

import play.api.libs.json.{Json, OFormat}

case class ChangePasswordDTO(oldPassword: String, newPassword: String)

object ChangePasswordDTO {
  implicit val changePasswordFormat: OFormat[ChangePasswordDTO] = Json.format[ChangePasswordDTO]
}
