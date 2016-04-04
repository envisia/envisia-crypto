organization := "de.envisia"

name := """envisia-crypto"""

autoScalaLibrary := false
crossPaths := false

libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % "4.0" % Provided,
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "com.google.inject" % "guice" % "4.0" % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

fork in Test := true