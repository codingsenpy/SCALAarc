object UserID{
  def apply(userid:String)=s"$userid--${Random.nextLong()}"

  def unapply(userid:String):Option[String]={
    val arrofstr: Array[String]=userid.split("--")
    if(arrofstr.tail.nonEmpty) Some(arrofstr.head) else None
  }
}

val user1= UserID("Zain")
println(user1)  ///Zain--12345678
user1 match{
  case UserID(userid)=>println(userid)  //zain
  case _=>println("not found")
}

or

//this works like the pattern match
val UserID(name)=user1  /When you write val UserID(name) = userid, Scala implicitly calls CustomerID.unapply(customer2ID)
println(name)  //zain  //If there is no match, a scala.MatchError is thrown:
