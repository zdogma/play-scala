package models

import play.api.db._
import anorm._
import play.api.Play.current
import anorm.SqlParser._

case class Mydata (
  name: String,
  message: String,
  createdAt:  String
) {

  def addData {
    DB.withConnection { implicit c =>
      val id: Int = SQL("INSERT INTO mydata (name,message,createdAt) VALUES ( {name}, {message}, {createdAt}) ") .
      on ( 'name -> this.name, 'message -> this.message, 'createdAt -> this.createdAt) .executeUpdate()
    }
  }
}

object Mydata {
  val data = {
    get[String]("name") ~
    get[String]("message") ~
    get[String]("createdAt") map {
      case name ~ message ~ createdAt => Mydata(name, message, createdAt)
    }
  }

  def getAll: List[Mydata] = {
    DB.withConnection { implicit c =>
      val data = SQL("SELECT * FROM mydata").as(Mydata.data *)
      return data
    }
  }
}

