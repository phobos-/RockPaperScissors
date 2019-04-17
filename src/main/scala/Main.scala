import cats.free.Free
import Actions._
import cats.implicits._

object Main extends App {
  Program.startGame.foldMap(Compiler.ioCompiler(Strategies.random)).unsafeRunSync()
}

object Program {

  private def playOneRound(you: Int, ai: Int): Free[Action, Score] = for {
    s1 <- askForShape
    s2 <- askAiForShape
    winner <- playRound(s1, s2)
  } yield {
    if (s1 == s2) Score(you, ai)
    else if (winner == s1) Score(you + 1, ai)
    else Score(you, ai + 1)
  }

  private def loopIt(score: Score): Free[Action, Score] = for {
    nextScore <- playOneRound(score.you, score.ai)
    agree <- askForNextRound
    result <- if (agree) loopIt(nextScore) else nextScore.pure[ActionFr]
  } yield result

  def startGame: Free[Action, Score] = for {
    result <- loopIt(Score(0, 0))
    _ <- endGame(result)
  } yield result
}
