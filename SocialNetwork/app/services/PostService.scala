package services

import dtos.{CreatePostDTO, PostDTO}
import exceptions.ObjectNotFoundException
import models.Post
import repositories.{PostRepository, UserRepository}
import utils.Util
import utils.Util.DATETIME_FORMAT

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class PostService @Inject()(postRepository: PostRepository, userRepository: UserRepository)(implicit executionContext: ExecutionContext) {

  def add(post: CreatePostDTO, id: Long): Future[PostDTO] = {
    postRepository.add(convertFromDTO(post, id)) flatMap {
      p => getDTOWithUserData(p, id)
    }
  }

  def update(post: PostDTO): Future[PostDTO] = {
    val newPost: PostDTO = post.copy(lastUpdated = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)))
    postRepository.update(newPost) map (_ => newPost)
  }

  def remove(id: Long): Future[Unit] = {
    postRepository.remove(id) map {
      _ => ()
    }
  }

  def getUsersPosts(id: Long): Future[Seq[PostDTO]] = {
    postRepository.getUsersPosts(id)
  }

  def getFriendsPosts(id: Long): Future[Seq[PostDTO]] = {
    postRepository.getFriendsPosts(id)
  }

  private def getDTOWithUserData(post: Post, userId: Long): Future[PostDTO] = {
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(Util.DATETIME_FORMAT)
    val createdStr: String = dtf.format(post.created)
    val lastUpdatedStr: String = dtf.format(post.lastUpdated)
    val optionUser = userRepository.getUserDataDTO(userId)
    optionUser flatMap {
      case Some(value) =>
        Future.successful {
          PostDTO(id = post.id, content = post.content, user = value,
            created = createdStr, lastUpdated = lastUpdatedStr)
        }
      case None => throw ObjectNotFoundException()
    }
  }

  private def convertFromDTO(dto: CreatePostDTO, id: Long): Post = {
    Post(content = dto.content, userId = id, created = LocalDateTime.now(), lastUpdated = LocalDateTime.now())
  }
}
