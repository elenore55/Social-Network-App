package services.validation

import dtos.{CreateUserDTO, UserDataDTO}


object UserDataValidationService {

  def validate(user: UserDataDTO): Either[String, Unit] = {
    var errorMessage: String = ""
    if (user.name.isEmpty) errorMessage += "Name must not be empty\n"
    if (user.surname.isEmpty) errorMessage += "Surname must not be empty\n"
    if (!isValidEmail(user.email)) errorMessage += "Invalid email format\n"
    if (user.username.isEmpty) errorMessage += "Username must not be empty\n"
    if (!isValidDateOfBirth(user.dateOfBirth)) errorMessage += "Invalid date of birth\n"

    if (errorMessage == "")
      Right(())
    else
      Left(errorMessage)
  }

  def validate(user: CreateUserDTO): Either[String, Unit] = {
    var errorMessage: String = ""
    if (user.name.isEmpty) errorMessage += "Name must not be empty\n"
    if (user.surname.isEmpty) errorMessage += "Surname must not be empty\n"
    if (!isValidEmail(user.email)) errorMessage += "Invalid email format\n"
    if (user.username.isEmpty) errorMessage += "Username must not be empty\n"
    if (!isValidPassword(user.password))
      errorMessage += "Password must be at least 8 characters long and must contain at least one lowercase letter, " +
        "one uppercase letter and one digit\n"
    if (!isValidDateOfBirth(user.dateOfBirth)) errorMessage += "Invalid date of birth\n"

    if (errorMessage == "")
      Right(())
    else
      Left(errorMessage)
  }

  def isValidPassword(password: String): Boolean = {
    password.length >= 8 && password.exists(_.isUpper) && password.exists(_.isLower) && password.exists(_.isDigit)
  }

  def isValidEmail(email: String): Boolean = {
    email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
  }

  def isValidDateOfBirth(date: String): Boolean = {
    date.matches("(?:0[1-9]|[12][0-9]|3[01])[-/.](?:0[1-9]|1[012])[-/.](?:19\\d{2}|20[01][0-9]|2020)")
  }
}
