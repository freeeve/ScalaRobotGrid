name := "ScalaRobotGrid"
 
version := "0.1"
 
scalaVersion := "2.9.1"
 
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test"
)

libraryDependencies <+= (libraryDependencies, sbtVersion) { (deps, version) => 
    "de.element34" %% "sbt-eclipsify" % "0.10.0-SNAPSHOT"
}
