lazy val http4sKamonDemo = (project in file("."))
  .settings(
    scalaVersion := "2.12.12",
    libraryDependencies ++= Seq(
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "org.http4s" %% "http4s-blaze-server" % "0.21.15",
      "org.http4s" %% "http4s-dsl" % "0.21.15",
      "io.kamon" %% "kamon-bundle" % "2.1.9",
      "io.kamon" %% "kamon-http4s" % "2.0.3",
    ),
  )
