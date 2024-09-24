import scala.util.Random

val x = Random.nextInt(10)
x match {
  case 0 => println("zero")
  case 1 => println("one")
  case 2 => println("two")
  case _ => println("other")
}
/////////////////////////////

METHOD FOR MATCH:

def matchpattern(x:Int):String=x match{
  case 0 =>"zero"
  case 1 =>"one"
  case 2 =>"two"
  case _ =>"other"
}
matchpattern(5342)//other
matchpattern(1)//one
////////////////////////////////////////

sealed trait Notification

class Email(sender: String,title:String,body:String) extends Notification
class SMS(sender: String,message:String) extends Notification
class Whatsapp(sender: String,text:String) extends Notification

class ShowNotification(notification:Notification):String=match{
  case  Email(sender, title, _) =>
      s"You got an email from $sender with title: $title"
    case SMS(sebder, message) =>
      s"You got an SMS from $number! Message: $message"
    case Whatsapp(sender, text) =>
      s"You received a Voice Recording from $name! Click the link to hear it: $link"
  }
}
val someSms = SMS("12345", "Are you there?")
println(showNotification(someSms))  // prints You got an SMS from 12345! Message: Are you there?
