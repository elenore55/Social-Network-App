package utils

object Util {
  val DATE_FORMAT: String = "dd/MM/yyyy"
  val DATETIME_FORMAT: String = "dd/MM/yyyy hh:mm"
  val MAX_CONTENT_LENGTH: Int = 2000

  def SQLTextToDateTimeString(text: String): String = {
    if (text.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}")) {
      val tokens = text.split("T")
      val date = tokens(0).split("-")
      val time = tokens(1).split(':')
      date(2) + "/" + date(1) + "/" + date(0) + " " + time(0) + ":" + time(1)
    }
    else text
  }
}
