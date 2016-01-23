name := "MyAkkaHttpExample"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= {
  val akkaStreamV = "2.0.2"
  Seq(
    "com.typesafe.akka" % "akka-http-experimental_2.11" % akkaStreamV,
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaStreamV
  )
}
