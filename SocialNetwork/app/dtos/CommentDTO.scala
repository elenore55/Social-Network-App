package dtos

import play.api.libs.json.{Json, OFormat}

case class CommentDTO(
  id: Long = -1,
  content: String = "",
  postId: Long = -1,
  user: UserDataDTO = UserDataDTO(),
  created: String = "",
  lastUpdated: String = ""
)

object CommentDTO {
  implicit val commentFormat: OFormat[CommentDTO] = Json.format[CommentDTO]
}
