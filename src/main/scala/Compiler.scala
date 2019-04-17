import cats.~>
import cats.effect.IO

object Compiler {
  import IOActions._

  def ioCompiler(strategy: () => Shape): Action ~> IO = new (Action ~> IO) {
    override def apply[A](fa: Action[A]): IO[A] = fa match {
      case PlayRound(s1, s2) => IO.delay { Rules.winner(s1, s2) }
      case AskForShape => IO.delay { println("Your shape") } flatMap { _ => inShape(input) }
      case AskAIForShape => IO.delay { val shape = strategy.apply(); println(s" against $shape"); shape }
      case EndGame(Score(player, ai)) => IO.delay { println(s"Total score is $player : $ai . Bye bye") }
      case NextRound => IO.delay { println("Do you  want to continue") } flatMap { _ => inYesNo }
    }
  }
}
