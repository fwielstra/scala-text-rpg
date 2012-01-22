package main.scala

object Direction extends Enumeration {
  type Direction = Value
  val North, East, South, West = Value
}

object Style {
  private val CSI = "\033["
  def addStyle(content: String, style: String = Style.NONE) = "%s %s".format(style, content)

  val NONE = ""
  val BOLD = CSI + "1m"
}

case class Room(title: String, exits: Seq[(Direction.Direction, Room)] = Nil) {
  override def toString() = Style.addStyle(title, Style.BOLD)
}

object main {
  def main(args: Array[String]) {
    import Direction._

    val roomOne = Room("A room", Seq((North, Room("Another room"))))
    println(roomOne)
  }
}
