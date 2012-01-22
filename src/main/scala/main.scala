package main.scala

object Direction extends Enumeration {
  type Direction = Value
  val North, East, South, West = Value
}

object Style {
  private val CSI = "\033["
  val BOLD = CSI + "1m"
}


case class Room(title: String, exits: Seq[(Direction.Direction, Room)] = Nil) {
  override def toString() = "%s %s".format(Style.BOLD, title)
}

object main {
  def main(args: Array[String]) {
    println("ohai")
    import Direction._

    val roomOne = Room("A room", Seq((North, Room("Another room"))))
    println(roomOne)
  }
}
