package models

import models.RequestStatus.{PENDING, RequestStatus}

import java.time.LocalDateTime

case class FriendshipRequest(
  id: Long = -1,
  senderId: Long = -1,
  receiverId: Long = -1,
  dateTime: LocalDateTime = LocalDateTime.MIN,
  status: RequestStatus = PENDING
)
