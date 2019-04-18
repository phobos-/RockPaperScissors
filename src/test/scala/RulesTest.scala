import org.scalatest.{FlatSpec, Matchers}
import Rules.winner

class RulesTest extends FlatSpec with Matchers {
  behavior of "Rules"

  "Paper" should "beat Rock or Spock and lose to Scissors or Lizard" in {
    winner(Paper, Rock) shouldBe Paper
    winner(Paper, Scissors) shouldBe Scissors
    winner(Paper, Paper) shouldBe Paper
    winner(Paper, Spock) shouldBe Paper
    winner(Paper, Lizard) shouldBe Lizard
  }

  "Rock" should "beat Scissors or Lizard and lose to Paper or Spock" in {
    winner(Rock, Rock) shouldBe Rock
    winner(Rock, Scissors) shouldBe Rock
    winner(Rock, Paper) shouldBe Paper
    winner(Rock, Spock) shouldBe Spock
    winner(Rock, Lizard) shouldBe Rock
  }

  "Scissors" should "beat Paper or Lizard and lose to Rock or Spock" in {
    winner(Scissors, Rock) shouldBe Rock
    winner(Scissors, Scissors) shouldBe Scissors
    winner(Scissors, Paper) shouldBe Scissors
    winner(Scissors, Spock) shouldBe Spock
    winner(Scissors, Lizard) shouldBe Scissors
  }

  "Spock" should "beat Rock or Scissors and lose to Lizard or Paper" in {
    winner(Spock, Rock) shouldBe Spock
    winner(Spock, Scissors) shouldBe Spock
    winner(Spock, Paper) shouldBe Paper
    winner(Spock, Spock) shouldBe Spock
    winner(Spock, Lizard) shouldBe Lizard
  }

  "Lizard" should "beat Paper or Spock and lose to Scissors or Rock" in {
    winner(Lizard, Rock) shouldBe Rock
    winner(Lizard, Scissors) shouldBe Scissors
    winner(Lizard, Paper) shouldBe Lizard
    winner(Lizard, Spock) shouldBe Lizard
    winner(Lizard, Lizard) shouldBe Lizard
  }
}
