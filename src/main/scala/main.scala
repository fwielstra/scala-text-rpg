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
case class Room(title: String, description: String, exitMap: Stream[Map[Direction.Direction, Room]]) {
  def exits = exitMap(1) // skip the Streams empty head
  def hasExit(direction: Direction.Direction) = exits contains direction
  def exit(inDirection: Direction.Direction) = exits(inDirection)

  val outputFormat =
    """|{BOLD}{title}{/BOLD}
       | 
       | {description}
       |
       | Exits:
       |  {exits}""".stripMargin

  override def toString() = {
    Formatter.parse(outputFormat, Map(
    "title" -> title,
    "description" -> description,
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
  lazy val first: Room = Room("North room", "This is the north room. It is very big. There is shit on the wall.", _exitMap(Map(South -> second)))
  lazy val second: Room = Room("South room", "This is the south room. It is small. There are small people here that want to eat you. Om nom nom nom.", _exitMap(Map(North -> first)))
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

    println("Welcome to Fuck Yeah Awesome Pre-Beta 3, the Game that will Give you a Huge Boner.")
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
