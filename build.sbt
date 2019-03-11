name := "phantom-cassandra"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.outworkers" %% "phantom-dsl" % "2.7.6",
  "com.outworkers" %% "phantom-connectors" % "2.7.6",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.cassandraunit" % "cassandra-unit" % "3.1.3.2"
)