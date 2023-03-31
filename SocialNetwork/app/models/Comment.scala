package models

import java.time.LocalDateTime

case class Comment(
  id: Long = -1,
  content: String = "",
  postId: Long = -1,
  userId: Long = -1,
  created: LocalDateTime = LocalDateTime.now(),
  lastUpdated: LocalDateTime = LocalDateTime.now()
)
