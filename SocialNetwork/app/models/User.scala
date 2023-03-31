package models

import java.time.LocalDate

case class User(
  id: Long = -1,
  name: String = "",
  surname: String = "",
  username: String = "",
  password: String = "",
  email: String = "",
  dateOfBirth: LocalDate = LocalDate.now(),
  profilePicture: String = ""
)
