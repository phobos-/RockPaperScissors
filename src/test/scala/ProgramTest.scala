import cats.{Id, ~>}
import org.scalatest.{FlatSpec, Matchers}

class ProgramTest extends FlatSpec with Matchers with TestInterpreter {
  "Program" should "work for one round" in {
    Program.startGame.foldMap(testInterpreter) shouldBe Score(1, 0)
  }
}

trait TestInterpreter extends TestStrategy{
  val testInterpreter: Action ~> Id = new (Action ~> Id) {
    override def apply[A](fa: Action[A]) = fa match {
      case PlayRound(s1, s2) => Rules.winner(s1, s2)
      case AskForShape => Paper
      case EndGame(_) => ()
      case NextRound => false
      case AskAIForShape => testStrategy
    }
  }
}

trait TestStrategy {
  val testStrategy = Rock
}
