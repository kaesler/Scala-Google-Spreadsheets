import org.scalajs.linker.interface.ESVersion

organization := "org.kae"
version      := "0.1-SNAPSHOT"
scalaVersion := "3.10.0"

@transient
val fastCompileRenderer = taskKey[File]("Return main file")

lazy val fastCompileCreateFunctions =
  taskKey[Unit]("Fast compile, and adds to the compiled file the created functions")

@transient
val fullCompileRenderer = taskKey[File]("Return full optimized main file")

lazy val fullCompileCreateFunctions =
  taskKey[Unit]("Full compile, and adds to the compiled file the created functions")

ThisBuild / scalacOptions ++= Seq(
  "-Wnonunit-statement",
  "-Wunused:explicits",
  "-Wunused:implicits",
  "-Wunused:imports",
  "-Wunused:locals",
  "-Wunused:params",
  "-Wunused:privates",
  "-Wvalue-discard",
  "-Werror",
  "-Xmigration",
  "-deprecation",
  "-explain-types",
  "-feature",
  "-language:implicitConversions",
  "-source:future",
  "-unchecked"
)

lazy val root = project
  .in(file("."))
  .enablePlugins(ScalaJSPlugin)
  .aggregate(
    cells,
    facade,
    tutorial
  )
  .settings(
    name := "ScalaSpreadSheets"
  )

lazy val cells = project
  .in(file("./modules/cells"))
  .enablePlugins(ScalaJSPlugin)

lazy val facade = project
  .in(file("./modules/facade"))
  .dependsOn(cells)
  .enablePlugins(ScalaJSPlugin)

lazy val tutorial = project
  .in(file("./modules/tutorial"))
  .dependsOn(facade, cells)
  .enablePlugins(ScalaJSPlugin)
  .settings(
    publish / skip := true,
    scalaJSLinkerConfig ~= {
      _.withESFeatures(
        _.withESVersion(
          // Note: Anything later results in linked output
          // which uses the "||=" operator, which fails to
          // parse in the Google AppScript (V8) interpreter.
          ESVersion.ES2020
        )
      )
    },
    fastCompileRenderer := {
      (Compile / fastOptJS).value.data
      // TODO: https://www.scala-sbt.org/2.x/docs/en/concepts/caching.html#caching-files
    },
    fullCompileRenderer := {
      (Compile / fullOptJS).value.data
      // TODO: https://www.scala-sbt.org/2.x/docs/en/concepts/caching.html#caching-files
    },
    fastCompileCreateFunctions := {
      GSheetFunctions.createGoogleFunctions(
        fastCompileRenderer.value,
        baseDirectory.value
      )
    },
    fullCompileCreateFunctions := {
      GSheetFunctions.createGoogleFunctions(
        fullCompileRenderer.value,
        baseDirectory.value
      )
    }
  )
