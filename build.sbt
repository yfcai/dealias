val sversion = "2.11.1"

val mySettings = Project.defaultSettings ++ Seq(
  scalaVersion := sversion,
  scalacOptions := Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
  resolvers += Resolver sonatypeRepo "releases",
  libraryDependencies += compilerPlugin("org.scalamacros" % s"paradise_$sversion" % "2.0.0"),
  libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.4")

// macro definition
lazy val macro = project in file("macro") settings (mySettings: _*)

// client code
lazy val client = project in file("client") settings (mySettings: _*) dependsOn macro
