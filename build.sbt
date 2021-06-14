name := "sdk-scala-2"

version := "1.0.0"

scalaVersion := "2.11.12"

organization := "com.sidemash"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.7.4"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-Xfatal-warnings",
  "-Xlint",
  "-Yinline-warnings",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Xfuture"
)


homepage := Some(url("https://github.com/sidemash/sdk-scala-2"))
scmInfo := Some(ScmInfo(url("https://github.com/sidemash/sdk-scala-2"), "git@github.com:sidemashcloud/sdk-scala-2.git"))
developers := List(Developer("sidemash", "SidemashCloud", "opensource@sidemash.com", url("https://github.com/sidemash")))

licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))

publishMavenStyle := true

// disable publish ith scala version, otherwise artifact name will include scala version
// e.g cassper_2.11
crossPaths := false

// add sonatype repository settings
// snapshot versions publish to sonatype snapshot repository
// other versions publish to sonatype staging repository
publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)

