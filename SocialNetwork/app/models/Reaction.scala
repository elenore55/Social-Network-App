package models

case class Reaction(
  id: Long = -1,
  points: Int = 0,
  postId: Long = -1,
  userId: Long = -1
)
