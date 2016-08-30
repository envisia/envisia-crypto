organization := "de.envisia"

name := """crypto"""

autoScalaLibrary := false
crossPaths := false

libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % "4.0" % Provided,
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "com.google.inject" % "guice" % "4.0" % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")

licenses +=("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
bintrayOrganization := Some("envisia")