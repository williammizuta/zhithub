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
          val repos = new File("/tmp/repos")
          repos.mkdirs();
          val repo = new RepositoryFactory(repos).buildBare(projectName);
          Project.create(projectName)
          Redirect(routes.ProjectController.projects)
        })
  }

  def projects = Action {
    Ok(views.html.listProjects(Project.all))
  }
}