import java.util.concurrent.Executors

import fs2.Pipe
import cats.effect.{ContextShift, IO}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

object IOActions {

  implicit val contextShift: ContextShift[IO] = IO.contextShift(ExecutionContext.global)
  val blockingEC: ExecutionContextExecutor = ExecutionContext.fromExecutor(Executors.newCachedThreadPool())

  def input: IO[String] = fs2.io.stdin[IO](24, blockingEC).through(parseChar).take(1).map(_.toString).compile.string

  def parseString[F[_]]: Pipe[F, Vector[Byte], String] = _.map(in => new String(in.toArray.map(_.toChar)))
  def parseChar: Pipe[IO, Byte, Char] = _.map(_.toChar)

  def inShape(in: IO[String]): IO[Shape] =
    in.map(Parser.getShape)
      .flatMap(
        _.fold(
          _ => {println("Incorrect shape. Please, select (R)ock, (P)aper, (S)cissors, (L)izard or (V)Spock"); inShape(in)},
          s => IO.delay(s)))
      .map(shape => { println(shape); shape })

  def inYesNo: IO[Boolean] =
    input.map(Parser.yesNo)
}
