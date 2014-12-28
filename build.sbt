val commonSettings = Seq(
  version := "0.1.0-SNAPSHOT",
  organization := "com.ataraxer",
  scalaVersion := "2.10.4",
  scalacOptions ++= Seq(
    "-g:vars",
    "-deprecation",
    "-unchecked",
    "-feature",
    "-Xlint",
    "-Xfatal-warnings")) ++
  Revolver.settings

val dependencies = Seq(
  libraryDependencies ++= Seq(
    "org.mongodb" %% "casbah" % "2.7.4",
    "com.novus" %% "salat" % "1.9.8",
    "com.typesafe.akka" %% "akka-actor" % "2.3.3",
    "com.typesafe" % "config" % "1.2.1",
    "io.spray" % "spray-can" % "1.3.1",
    "io.spray" % "spray-routing" % "1.3.1",
    "org.json4s" %% "json4s-native" % "3.2.10"))

lazy val athenaCore = project.in(file("athena-core"))
  .settings(commonSettings: _*)
  .settings(dependencies: _*)


