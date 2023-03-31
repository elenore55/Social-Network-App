package exceptions

case class UserAlreadyRegisteredException(private val message: String = "User already registered",
                                          private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
