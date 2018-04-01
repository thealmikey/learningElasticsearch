name := "learningElasticsearch"

version := "0.1"

scalaVersion := "2.12.5"

// https://mvnrepository.com/artifact/com.sksamuel.elastic4s/elastic4s-core
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-core" % "6.2.3"
// https://mvnrepository.com/artifact/com.sksamuel.elastic4s/elastic4s-tcp
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-tcp" % "6.2.3"
// https://mvnrepository.com/artifact/com.sksamuel.elastic4s/elastic4s-circe
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-circe" % "6.2.3"
// https://mvnrepository.com/artifact/com.sksamuel.elastic4s/elastic4s-http
libraryDependencies += "com.sksamuel.elastic4s" %% "elastic4s-http" % "6.2.3"


libraryDependencies += "org.typelevel" %% "cats-core" % "1.0.1"

val circeVersion = "0.9.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)
