package services.validation

import dtos.{CommentDTO, CreateCommentDTO}
import utils.Util

object CommentValidationService {
  def validate(comment: CommentDTO): Either[String, Unit] = {
    if (comment.content.isEmpty) {
      Left("Comment content must not be empty")
    }
    else if (comment.content.length > Util.MAX_CONTENT_LENGTH) {
      Left(s"Comment content must not be greater than ${Util.MAX_CONTENT_LENGTH} characters")
    }
    else {
      Right(())
    }
  }

  def validate(comment: CreateCommentDTO): Either[String, Unit] = {
    if (comment.content.isEmpty) {
      Left("Comment content must not be empty")
    }
    else if (comment.content.length > Util.MAX_CONTENT_LENGTH) {
      Left(s"Comment content must not be greater than ${Util.MAX_CONTENT_LENGTH} characters")
    }
    else {
      Right(())
    }
  }
}
