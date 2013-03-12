name := "rws"

scalaVersion := "2.10.0"

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.0.0-M8",
  "com.twitter" %% "finagle-core" % "6.2.0",
  "org.scalacheck" %% "scalacheck" % "1.10.0" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-optimise",
  "-Yinline-warnings",
  "-feature",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:postfixOps"
)
