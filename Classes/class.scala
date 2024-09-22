class User  //User has a default constructor which takes no arguments because no constructor was defined

val user1=new User
//new is used to create an instance of the class.

class Point(var x: Int, var y: Int){
  def move(dx: Int, dy: Int): Unit={
    x+=dx
    y+=dy
  }

  override def toString: String={
    s"($x, $y)"
  }
}

val pt1=new Point(2,3)
println(pt1.x) //2
println(pt1) // (2, 3)
pt1.move(1,1)
println(pt1) // (3, 4)
