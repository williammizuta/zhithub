import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "zhithub"
  val appVersion      = "1.0-SNAPSHOT"


  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "br.com.caelum" % "zhit" % "1.0-SNAPSHOT"
  )


   val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
     resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
   )

}
