package services

import dtos.{CreateFriendshipRequestDTO, FriendshipRequestDTO, UserDataDTO}
import exceptions.{FriendshipRequestAlreadySentException, ObjectNotFoundException}
import models.{FriendshipRequest, RequestStatus}
import repositories.{FriendshipRequestRepository, UserRepository}
import utils.Util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class FriendshipRequestService @Inject()(friendshipRequestRepository: FriendshipRequestRepository, userRepository: UserRepository)
                                        (implicit executionContext: ExecutionContext) {

  def getReceivedRequests(id: Long): Future[Seq[FriendshipRequestDTO]] = {
    val future: Future[Seq[FriendshipRequestDTO]] = friendshipRequestRepository.getReceivedRequests(id)
    future map {
      reqs => reqs
    }
  }

  def getSentRequests(id: Long): Future[Seq[FriendshipRequestDTO]] = {
    val future: Future[Seq[FriendshipRequestDTO]] = friendshipRequestRepository.getSentRequests(id)
    future map {
      reqs => reqs
    }
  }

  def add(request: CreateFriendshipRequestDTO, id: Long): Future[FriendshipRequestDTO] = {
    val optionRequest: Future[Option[FriendshipRequest]] = friendshipRequestRepository.getRequestBetween(id, request.receiverId)
    optionRequest flatMap {
      case Some(_) => throw FriendshipRequestAlreadySentException()
      case None =>
        val future: Future[FriendshipRequest] = friendshipRequestRepository.add(convertFromDTO(request, id))
        future flatMap {
          req => getDTOWithUserData(req, id)
        }
    }
  }

  def changeStatus(id: Long, status: String): Future[Int] = {
    val future = friendshipRequestRepository.update(id, status)
    future map {
      x: Int =>
        if (x == 0) throw ObjectNotFoundException()
        else x
    }
  }


  private def getDTOWithUserData(req: FriendshipRequest, userId: Long): Future[FriendshipRequestDTO] = {
    val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern(Util.DATETIME_FORMAT)
    val optionUser = userRepository.getUserDataDTO(userId)
    optionUser flatMap {
      case Some(value) => Future.successful {
        FriendshipRequestDTO(id = req.id, otherUser = value, dtf.format(req.dateTime), status = req.status.toString)
      }
      case None => throw ObjectNotFoundException()
    }
  }

  private def convertFromDTO(dto: CreateFriendshipRequestDTO, id: Long): FriendshipRequest = {
    FriendshipRequest(senderId = id, receiverId = dto.receiverId, dateTime = LocalDateTime.now(), status = RequestStatus.PENDING)
  }
}
