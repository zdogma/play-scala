package models

import play.api.db._
import anorm._
import play.api.Play.current
import anorm.SqlParser._

case class Mydata (
  name: String,
  mail: String,
  tel:  String
) {

  def addData {
    DB.withConnection { implicit c =>
      val id: Int = SQL("INSERT INTO mydata (name,mail,tel) VALUES ( {name}, {mail}, {tel}) ") .
      on ( 'name -> this.name, 'mail -> this.mail, 'tel -> this.tel) .executeUpdate()
    }
  }
}

object Mydata {
  val data = {
    get[String]("name") ~
    get[String]("mail") ~
    get[String]("tel") map {
      case name ~ mail ~ tel => Mydata(name, mail, tel)
    }
  }

  def getAll: List[Mydata] = {
    DB.withConnection { implicit c =>
      val data = SQL("SELECT * FROM mydata").as(Mydata.data *)
      return data
    }
  }
}

