package dtos

import dtos.FriendshipStatus.FriendshipStatusType
import play.api.libs.json.{Json, OFormat}

object FriendshipStatus {
  type FriendshipStatusType = String
  val FRIENDS = "Friends"
  val NOT_FRIENDS = "Not friends"
  val REQUEST_SENT = "Request sent"
  val REQUEST_RECEIVED = "Request received"
}

case class OtherUserProfileDTO(userData: UserDataDTO, friendshipStatus: FriendshipStatusType)

object OtherUserProfileDTO {
  implicit val otherUserFormat: OFormat[OtherUserProfileDTO] = Json.format[OtherUserProfileDTO]
}
