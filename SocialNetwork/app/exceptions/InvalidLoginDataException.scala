package exceptions

final case class InvalidLoginDataException(private val message: String = "Invalid login data",
                                           private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
