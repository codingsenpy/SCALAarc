Class and object which have same name are called as companions, they can access each other's private members

case class Circle(radius:Int){
  def area:Double=calculateArea(radius)
}
object Circle{
  private def calculateArea(radius:Int):Double=3.14*(radius**2)
}
val circle1 = Circle(5.0)

circle1.area
//////////////////////////////////////////////////////////////////////////////////////////////////////////////'

FACTORY METHOD:

class Email(val username: String, val domainName: String)

object Email{
  def fromString(emailString:String):Option[Email]={
    emailString.split('@') match {
      case Array(a, b) => Some(new Email(a, b))
      case _ => None
    }
  }
}

val scalaCenterEmail = Email.fromString("scala.center@epfl.ch")
scalaCenterEmail match {
  case Some(email) => println(
    s"""Registered an email
       |Username: ${email.username}
       |Domain name: ${email.domainName}
     """.stripMargin)
  case None => println("Error: could not parse email")
}
