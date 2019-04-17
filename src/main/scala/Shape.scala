import scala.util.Random

sealed trait Shape
case object Rock extends Shape
case object Scissors extends Shape
case object Paper extends Shape

object Rules {
  def winner: (Shape, Shape) => Shape = {
    case (Rock, Scissors) => Rock
    case (Scissors, Paper) => Scissors
    case (Paper, Rock) => Paper
    case (_, s2) => s2
  }
}

object Strategies {
  val random: () => Shape = () => Vector(Rock, Scissors, Paper)(Random.nextInt(3))
}

