import scala.util.Random

sealed trait Shape
case object Rock extends Shape
case object Scissors extends Shape
case object Paper extends Shape
case object Spock extends Shape
case object Lizard extends Shape

object Rules {
  def winner: (Shape, Shape) => Shape = {
    case (Rock, Scissors) => Rock
    case (Scissors, Paper) => Scissors
    case (Paper, Rock) => Paper
    case (Rock, Lizard) => Rock
    case (Lizard, Spock) => Lizard
    case (Spock, Scissors) => Spock
    case (Lizard, Paper) => Lizard
    case (Paper, Spock) => Paper
    case (Spock, Rock) => Spock
    case (Scissors, Lizard) => Scissors
    case (_, s2) => s2
  }
}

object Strategies {
  val random: () => Shape = () => Vector(Rock, Scissors, Paper, Spock, Lizard)(Random.nextInt(5))
}

