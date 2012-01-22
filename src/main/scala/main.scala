package main.scala

object Direction extends Enumeration {
  type Direction = Value
  val North = Value("North")
  val East = Value("East")
  val South = Value("South")
  val West = Value("West")
  val Unknown = Value("")
  
  def parseInput(input: String) = {
    Direction.withName(input)
  }
}

// use streams / lazily initialized values to create a network of rooms.
// rather fancy really.
// http://stackoverflow.com/questions/8962044/how-do-i-refer-to-a-variable-while-assigning-a-value-to-it-whilst-retaining-imm 
case class Room(title: String, exitMap: Stream[Map[Direction.Direction, Room]]) {
  def exits = exitMap(1) // skip the Streams empty head
  def hasExit(direction: Direction.Direction) = exits contains direction
  def exit(inDirection: Direction.Direction) = exits(inDirection)

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

// lists all the rooms in this 'level'.
object Rooms {
  def _exitMap(mappedItems: => Map[Direction.Direction, Room]) = {
    Map[Direction.Direction, Room]() #::
    mappedItems #::
    Stream.empty
  }

  import Direction._
  lazy val first: Room = Room("North room", _exitMap(Map(South -> second)))
  lazy val second: Room = Room("South room", _exitMap(Map(North -> first)))
}
            
class Game(initialLocation: Room) {
   var currentLocation = initialLocation

   def go(direction: Direction.Direction) {
    if (currentLocation.hasExit(direction)) {
      currentLocation = currentLocation.exit(direction)
    }
   }

   override def toString = "current location: %s".format(currentLocation)
}

object main {
  def main(args: Array[String]) {
    import Direction._

    var isRunning = true
    val game = new Game(Rooms.first)

    while(isRunning) {
      println(game.currentLocation)
      val input = readLine
      // parse the input
      if (input == "exit") {
        isRunning = false
      } else {
        val direction = Direction.parseInput(input)
        game.go(direction)
      }
      println("bai")
    }
  }
}
