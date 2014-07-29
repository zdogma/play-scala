package controllers

import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._

object Application extends Controller {

  case class MyFormData(name:String, mail:String)
  val form1 = Form(
    mapping(
      "name" -> text,
      "mail" -> text
    ) (MyFormData.apply) (MyFormData.unapply)
  )

  def index = Action {
    val title = "Sample Page"
    val msg   = "This is a sample page."
    Ok(views.html.index(title, msg, form1))
  }

  def sendform = Action { implicit request =>
    var myForm = form1.bindFromRequest
    val data: MyFormData = myForm.get
    val title = "Sample Page"
    val msg   = "Name: " + data.name + ", Mail: " + data.mail
    Ok(views.html.index(title, msg, myForm))
  }

}
