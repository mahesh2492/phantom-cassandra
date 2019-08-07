name := "phantom-cassandra"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "com.outworkers" %% "phantom-dsl" % "2.7.6",
  "com.outworkers" %% "phantom-connectors" % "2.7.6",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.cassandraunit" % "cassandra-unit" % "3.1.3.2",
  "com.typesafe" % "config" % "1.2.1",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value
)