package models

import play.api.db.DB
import play.api.Play.current
import anorm._
import anorm.SqlParser._

case class Project(id: Long, name: String)

object Project {

  val project = {
    get[Long]("id") ~
      get[String]("name") map {
        case id ~ name => Project(id, name)
      }
  }

  def create(name: String) {
    DB.withConnection { implicit c =>
      SQL("insert into Project (name) values ({name})").on(
        "name" -> name).executeUpdate()
    }
  }

  def all: List[Project] = {
    DB.withConnection { implicit c =>
      SQL("select * from Project").as(project *)
    }
  }

  def find(name: String): Project = {
    DB.withConnection { implicit c =>
      SQL("select * from Project where name={name}").on("name" -> name).as(project single)
    }
  }
}
