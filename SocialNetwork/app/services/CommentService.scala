package services

import dtos.{CommentDTO, CreateCommentDTO}
import exceptions.ObjectNotFoundException
import models.Comment
import repositories.{CommentRepository, UserRepository}
import utils.Util
import utils.Util.DATETIME_FORMAT

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class CommentService @Inject()(commentRepository: CommentRepository, userRepository: UserRepository)(implicit executionContext: ExecutionContext) {

  def add(comment: CreateCommentDTO, id: Long): Future[CommentDTO] = {
    commentRepository.add(convertFromDTO(comment, id)) flatMap {
      c => getDTOWithUserData(c, id)
    }
  }

  def update(comment: CommentDTO): Future[CommentDTO] = {
    val currentDate = LocalDateTime.now()
    val newComment: CommentDTO = comment.copy(lastUpdated = currentDate.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)))
    commentRepository.update(newComment) map (_ => newComment)
  }

  def getComments(postId: Long): Future[Seq[CommentDTO]] = {
    commentRepository.getComments(postId)
  }

  def remove(id: Long): Future[Unit] = {
    commentRepository.remove(id) map {
      _ => ()
    }
  }

  private def getDTOWithUserData(comment: Comment, userId: Long): Future[CommentDTO] = {
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(Util.DATETIME_FORMAT)
    val createdStr: String = dtf.format(comment.created)
    val lastUpdatedStr: String = dtf.format(comment.lastUpdated)
    val optionUser = userRepository.getUserDataDTO(userId)
    optionUser flatMap {
      case Some(value) =>
        Future.successful {
          CommentDTO(id = comment.id, content = comment.content, postId = comment.postId, user = value,
            created = createdStr, lastUpdated = lastUpdatedStr)
        }
      case None => throw ObjectNotFoundException()
    }
  }

  private def convertFromDTO(dto: CreateCommentDTO, id: Long): Comment = {
    Comment(content = dto.content, postId = dto.postId, userId = id, created = LocalDateTime.now(),
      lastUpdated = LocalDateTime.now())
  }
}
