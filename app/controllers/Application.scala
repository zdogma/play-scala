package controllers

import models._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Form
import play.api.data.Forms._

object Application extends Controller {

  val form1 = Form(
    mapping(
      "name" -> text,
      "mail" -> text,
      "tel" -> text
    ) (Mydata.apply) (Mydata.unapply)
  )

  def index = Action {
    val title = "Sample Page"
    val msg   = "This is a sample page."
    val data  = Mydata.getAll
    Ok(views.html.index(title, msg, form1, data))
  }

  def sendform = Action { implicit request =>
    var myForm = form1.bindFromRequest
    val data: Mydata = myForm.get
    val result = data.addData
    val title = "Sample Page"
    val msg   = "This is a sample page."
    val listData  = Mydata.getAll
    Ok(views.html.index(title, msg, myForm, listData))
  }

}
