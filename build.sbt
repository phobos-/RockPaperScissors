name := "rpsls"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-free" % "1.6.0",
  "com.chuusai" %% "shapeless" % "2.3.3",
  "co.fs2" %% "fs2-core" % "1.0.4",
  "co.fs2" %% "fs2-io" % "1.0.4",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

