package dtos

import play.api.libs.json.{Json, OFormat}

case class CreateFriendshipRequestDTO(
  receiverId: Long = -1
)

object CreateFriendshipRequestDTO {
  implicit val createReqFormat: OFormat[CreateFriendshipRequestDTO] = Json.format[CreateFriendshipRequestDTO]
}
