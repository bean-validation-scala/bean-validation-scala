organization := "com.tsukaby"

name := "bean-validation-scala"

version := "0.4.2"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.1")

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

// Maven deploy settings
publishMavenStyle := true

publishArtifact in Test := false

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

pomIncludeRepository := { _ => false }

pomExtra := <url>https://github.com/bean-validation-scala/bean-validation-scala</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:bean-validation-scala/bean-validation-scala.git</url>
    <connection>scm:git:git@github.com:bean-validation-scala/bean-validation-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>tsukaby</id>
      <name>Shuya Tsukamoto</name>
      <url>https://github.com/tsukaby</url>
    </developer>
  </developers>

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
  "-Xfatal-warnings",
  "-Yrangepos"

)

scalacOptions in(Compile, doc) ++= {
  // Work around 2.12 bug which prevents javadoc in nested java classes from compiling.
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, v)) if v == 12 =>
      Seq("-no-java-comments")
    case _ =>
      Nil
  }
}

libraryDependencies ++= Seq(
  "org.hibernate" % "hibernate-validator" % "5.4.0.Final", // Bean validation
  "javax.validation" % "validation-api" % "1.1.0.Final", // Bean validation
  "org.glassfish" % "javax.el" % "3.0.0", // Bean validation
  "org.joda" % "joda-convert" % "1.8", // scalac compile helper for joda-time.
  "joda-time" % "joda-time" % "2.9.2",
  "org.jsoup" % "jsoup" % "1.8.3",
  "org.specs2" %% "specs2-core" % "3.8.8" % "test",
  "org.specs2" %% "specs2-mock" % "3.8.8" % "test"
)
