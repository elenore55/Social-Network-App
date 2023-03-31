package services.validation

import dtos.{CreatePostDTO, PostDTO}
import utils.Util

object PostValidationService {
  def validate(post: PostDTO): Either[String, Unit] = {
    if (post.content.isEmpty) {
      Left("Post content must not be empty")
    }
    else if (post.content.length > Util.MAX_CONTENT_LENGTH) {
      Left(s"Post content must not be larger than ${Util.MAX_CONTENT_LENGTH} characters")
    }
    else {
      Right(())
    }
  }

  def validate(post: CreatePostDTO): Either[String, Unit] = {
    if (post.content.isEmpty) {
      Left("Post content must not be empty")
    }
    else if (post.content.length > Util.MAX_CONTENT_LENGTH) {
      Left(s"Post content must not be larger than ${Util.MAX_CONTENT_LENGTH} characters")
    }
    else {
      Right(())
    }
  }
}

