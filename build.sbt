name := """SoulsECSTest"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

//scalacOptions ++= Seq(
//  "-feature"
//)
//"-language:implicitConversions"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

