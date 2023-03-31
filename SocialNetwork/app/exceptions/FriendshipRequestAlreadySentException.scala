package exceptions

case class FriendshipRequestAlreadySentException(private val message: String = "Friendship request already sent",
                                                 private val cause: Throwable = None.orNull)
  extends Exception(message, cause)
