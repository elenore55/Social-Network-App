package dtos

import play.api.libs.json.{Json, OFormat}

case class FriendshipRequestDTO(
   id: Long = -1,
   otherUser: UserDataDTO = UserDataDTO(),
   dateTime: String = "",
   status: String = "Pending"
)

object FriendshipRequestDTO {
  implicit val reqFormat: OFormat[FriendshipRequestDTO] = Json.format[FriendshipRequestDTO]
}