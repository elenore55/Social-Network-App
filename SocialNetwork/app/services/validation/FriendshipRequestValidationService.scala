package services.validation

object FriendshipRequestValidationService {

  def validateStatus(status: String): Either[String, Unit] = {
    if (!(status.toLowerCase == "accepted" || status.toLowerCase == "rejected")) {
      Left("Friendship request status must be either 'ACCEPTED' or 'REJECTED'")
    }
    else {
      Right(())
    }
  }
}
