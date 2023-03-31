lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """play-scala-hello-world-tutorial""",
    organization := "com.example",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.13.8",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
      "com.typesafe.play" %% "play-slick" % "5.0.2",
      "com.typesafe.play" %% "play-slick-evolutions" % "5.0.2",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      // "org.slf4j" % "slf4j-nop" % "1.7.36", // throws exception
      "com.typesafe.slick" %% "slick-hikaricp" % "3.3.3",
      "mysql" % "mysql-connector-java" % "8.0.29",
      "org.mindrot" % "jbcrypt" % "0.4",
      "com.github.jwt-scala" %% "jwt-play" % "9.0.6"
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )
