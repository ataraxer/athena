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
    "-Xfatal-warnings"))

val dependencies = Seq(
  libraryDependencies ++= Seq(
    "org.mongodb" %% "casbah" % "2.6.3",
    "com.novus" %% "salat" % "1.9.8"))

lazy val athenaCore = project.in(file("athena-core"))
  .settings(commonSettings: _*)
  .settings(dependencies: _*)

