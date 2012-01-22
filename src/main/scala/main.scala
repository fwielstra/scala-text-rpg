package main.scala

object Direction extends Enumeration {
  type Direction = Value
  val North, East, South, West = Value
}

case class Room(exits: Seq[(Direction.Direction, Room)] = Nil)

object main {
  def main(args: Array[String]) {
    println("ohai")
    import Direction._


    val roomOne = Room(Seq((North, Room())))
    println(roomOne)
  }
}
// vim: set ts=2 sw=2 et:
