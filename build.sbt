name := "bean-validation-scala"

version := "1.0"

scalaVersion := "2.11.5"

// Add warnings
scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xlint",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-unused",
  "-Ywarn-unused-import",
  "-Ywarn-value-discard",
  "-Xfatal-warnings"
)


libraryDependencies ++= Seq(
  "org.hibernate" % "hibernate-validator" % "5.1.3.Final", // Bean validation
  "javax.validation" % "validation-api" % "1.1.0.Final", // Bean validation
  "org.glassfish" % "javax.el" % "3.0.0", // Bean validation
  "org.specs2" %% "specs2-core" % "2.4.16" % "test",
  "org.specs2" %% "specs2-mock" % "2.4.16" % "test"
)
