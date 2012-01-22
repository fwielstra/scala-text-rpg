package main.scala

object Direction extends Enumeration {
  type Direction = Value
  val North, East, South, West = Value
}

object Style {
  private val CSI = "\033["
  val ENDC = CSI + "0m"
  def addStyle(content: String, style: String = Style.NONE) = "%s %s".format(style, content)

  val NONE = ""
  val BOLD = CSI + "1m"
}

case class Room(title: String, exits: Seq[(Direction.Direction, Room)] = Nil) {
  override def toString() = Style.addStyle(title, Style.BOLD)
}

class Game(initialLocation: Room) {
   var currentLocation = initialLocation
   override def toString = "current location: %s".format(currentLocation)
}

object main {
  def print(text: Any) = println("%s%s".format(Style.ENDC, text))
  def main(args: Array[String]) {
    import Direction._

    val roomOne = Room("A room", Seq((North, Room("Another room"))))
    val game = new Game(roomOne)
    print(roomOne)
    print(game)
  }
}
