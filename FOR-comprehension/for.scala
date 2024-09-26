//take any collection to iterate over
case class User(name: String, age: Int)

val userBase = List(
  User("Travis", 28),
  User("Kelly", 33),
  User("Jennifer", 44),
  User("Dennis", 23))

//for comprehension
val newuserbase=for(i<- userBase if i.age>20 && i.age<30) yield i.name  //yield is used to create a new collection based on filters applied on an pre existing collection which we're looping over
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


def findele(x:Int,y:Int)={
  for(i<-0 until x;
      j<-0 until x if i+j==y)
  yield(i,j)
}

findele(6,6).foreach{
  case(i,j)=>println(s"$i + $j =6")  //0 + 6 = 6
                                     //1 + 5 = 6
}                                    //2 + 4 = 6
                                     //3 + 3 = 6
                                     //4 + 2 = 6
                                     //5 + 1 = 6

