package dtos

import play.api.libs.json.{Json, OFormat}

case class CreatePostDTO(content: String)

object CreatePostDTO {
  implicit val createPostFormat: OFormat[CreatePostDTO] = Json.format[CreatePostDTO]
}
