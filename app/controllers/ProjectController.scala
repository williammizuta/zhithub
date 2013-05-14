package controllers

import play.api._
import play.api.mvc._
import models.Project
import play.api.data._
import play.api.data.Forms._
import br.com.caelum.zhit.factory.RepositoryFactory
import java.io.File

object ProjectController extends Controller {

  val projectForm = Form(
    "projectName" -> nonEmptyText)

  def newProjectForm = Action {
    Ok(views.html.projectForm(projectForm))
  }

  def saveProject = Action {
    implicit request =>
      projectForm.bindFromRequest.fold(
        errors => BadRequest(views.html.projectForm(projectForm)),
        projectName => {
          val repo = new RepositoryFactory(RepositoriesHome.repos).buildBare(projectName);
          Project.create(projectName)
          Redirect(routes.ProjectController.projects)
        })
  }

  def projects = Action {
    Ok(views.html.listProjects(Project.all))
  }

  def showProject(name: String) = Action {
    Ok(views.html.project(Project.find(name)))
  }

}

object RepositoriesHome {
  val rootPath = play.Configuration.root().getString("projects.repositories.root");
  val sshServer = play.Configuration.root().getString("ssh.server.address");
  val serverUser = play.Configuration.root().getString("ssh.server.user");
  val repos = new File(rootPath)
  def urlFor(project: Project): String = {
    serverUser + "@" + sshServer + ":" + rootPath + "/" + project.name
  }
}