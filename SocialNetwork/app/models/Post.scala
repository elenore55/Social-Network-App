package models

import java.time.LocalDateTime

case class Post(
  id: Long = -1,
  content: String = "",
  userId: Long = -1,
  created: LocalDateTime = LocalDateTime.now(),
  lastUpdated: LocalDateTime = LocalDateTime.now()
)
