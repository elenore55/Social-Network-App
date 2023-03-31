package dtos

import play.api.libs.json.{Json, OFormat}

case class CreateCommentDTO(content: String, postId: Long)

object CreateCommentDTO {
  implicit val createCommentFormat: OFormat[CreateCommentDTO] = Json.format[CreateCommentDTO]
}
