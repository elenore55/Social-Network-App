package dtos

import play.api.libs.json.{Json, OFormat}

case class PostDTO(
  id: Long = -1,
  content: String = "",
  created: String = "",
  lastUpdated: String = "",
  points: Int = 0,
  user: UserDataDTO = UserDataDTO(),
  myPoints: Int = 0
)

object PostDTO {
  implicit val postFormat: OFormat[PostDTO] = Json.format[PostDTO]
}
