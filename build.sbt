
lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "org.dthg",
  scalaVersion := "2.11.8",
  test in assembly := {}
)


lazy val root =  (project in file(".")).
    settings(commonSettings: _*).
    settings(libraryDependencies ++= Seq(
      "org.openstreetmap.osmosis" % "osmosis-pbf" % "0.46",
      "org.openstreetmap.osmosis" % "osmosis-osm-binary" % "0.46")).
    settings(
        assemblyJarName in assembly := "osm_uber_046.jar",
	assemblyShadeRules in assembly := Seq(
	  ShadeRule.rename("com.google.protobuf.**" -> "shadedprotobuf.@1").inAll
	))
	assemblyMergeStrategy in assembly := {
	  case "osmosis-plugins.conf" => MergeStrategy.first
	  case x =>
	    val oldStrategy = (assemblyMergeStrategy in assembly).value
	    oldStrategy(x)
      }


