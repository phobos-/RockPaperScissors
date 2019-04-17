import org.scalatest.{FlatSpec, Matchers}
import fs2._
import IOActions._
import cats.data.State
import cats.effect.IO

import scala.util.Random

class IOActionsTest extends FlatSpec with Matchers {

  "Parse char" should "parse correctly" in {
    Stream.emit('c'.toByte).through(parseChar).compile.toList.unsafeRunSync().head shouldBe 'c'
  }

  "Shape" should "be valid eventually as well" in {
    inShape(IO.delay("r")).unsafeRunSync() shouldBe Rock
  }

  it should "be going until the valid one" in new RandomTasks {
    inShape(randomTask(Vector("invalid", "crap", "Rock"))).unsafeRunSync() shouldBe Rock
  }
}

trait RandomTasks {

  private def tasks: State[List[String], IO[String]] = for {
    str <- State.get[List[String]]
    _ <- State.set(str.tail)
  } yield IO.delay(str.head)

  def randomTask(sequence: Vector[String]): IO[String] = {
    IO.delay(sequence(Random.nextInt(sequence.length)))
  }

  def tasksFromSequence(sequence: List[String]): (List[String], IO[String]) = {
    tasks.run(sequence).value
  }
}
