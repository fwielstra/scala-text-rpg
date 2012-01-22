package main.scala

object Direction extends Enumeration {
  type Direction = Value
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
}

case class Room(title: String, exits: Seq[(Direction.Direction, Room)] = Nil) {
  val outputFormat =
    """|{BOLD}{title}{/BOLD}
       | Exits:
       |  {exits}""".stripMargin

  override def toString() = {
    Formatter.parse(outputFormat, Map(
      "title" -> title,
      "exits" -> exits.map(exit => "  %s\n".format(exit._1)).mkString))
  }
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
  }
}
