package models

object RequestStatus extends Enumeration {
  type RequestStatus = Value
  val PENDING, ACCEPTED, REJECTED = Value
}
