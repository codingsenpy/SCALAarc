class User  //User has a default constructor which takes no arguments because no constructor was defined

val user1=new User
//new is used to create an instance of the class.

class Point(var x: Int, var y: Int){
  def move(dx: Int, dy: Int): Unit={
    x+=dx
    y+=dy
  }

  override def toString: String={  // when you attempt to print an object using println,
    s"($x, $y)"                    //  it automatically calls the object's toString method, so we're overriding the default toString method
  }
}

val pt1=new Point(2,3)
println(pt1.x) //2
println(pt1) // (2, 3)
pt1.move(1,1)
println(pt1) // (3, 4)
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


DEFAULT PARAMETERS:

constructors can have default parameters which will be used when no values are specified during construction of an instance

class Point(var x: Int = 0, var y: Int = 0)

val origin = new Point    // x and y are both set to 0
val point1 = new Point(1) // x is set to 1 and y is set to 0
println(point1)           // prints (1, 0)
val point2 = new Point(y = 2)
println(point2)               // prints (0, 2)
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

PRIVATE MEMBERS, GETTERS & SETTERS:

members are variables(fields) and method(function) of a class or trait,
member access can be controlled using private keyword,
this makes the member accesible only withing the scope its defined in(scope of class)    
getters are special methods used to access private members without modifying
setters are special methods used to assign values to a private member

class Point{
  private var _x=0
  private var _y=0
  private val bound=100
  def x: Int= _x
  def y: Int=_y

  def x_=(newValue: Int): Unit = {    // _= specifically for defining setters to enable property-like access.
    if (newValue < bound)
      _x = newValue
    else
      printWarning()
  }
def y_=(newValue: Int): Unit = {  // _= specifically for defining setters to enable property-like access.
    if (newValue < bound)
      _y = newValue
    else
      printWarning()
  }
  private def printWarning(): Unit =
    println("WARNING: Out of bounds")
}

val point1 = new Point
point1.x = 99
point1.y = 101 // prints the warning
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


Primary constructor parameters with val and var are public. However, because vals are immutable, you can’t write the following.

class Point(val x: Int, val y: Int)
val point = new Point(1, 2)
point.x = 3  // <-- does not compile 

Parameters without val or var are private values, visible only within the class.

class Point(x: Int, y: Int)
val point = new Point(1, 2)
point.x  // <-- does not compile
