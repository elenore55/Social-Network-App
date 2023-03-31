package exceptions

final case class IncorrectOldPasswordException(private val message: String = "Invalid login data",
                                               private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
