package exceptions

final case class ObjectNotFoundException(private val message: String = "Object not found",
                                         private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
