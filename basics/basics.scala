Expressions are computable statements: //
                                       //
1 + 1                                  //
/////////////////////////////////////////


///////////////////////////////////////////
println(1)//O/P: 1                       //
println(1+1)//O/P:2                      //
println("hello world")//O/P:hello world  //
println("hello"+"world")//O/P:hello world//
///////////////////////////////////////////

////////////////////////////////////////////
to name values of expressions we use val: //
                                          //
val x=1+1                                 //  x is called a value
println(x) //2                            //
////////////////////////////////////////////
value cannot be reasigned (for val only)

////////////////////////////////////////////
explicitly defining type                  //
                                          //
val x:Int=1+1                             //
////////////////////////////////////////////


///////////////////////////////////////////////////////////////////
var or known as variables are like values but u can reassign them//
                                                                 // 
var x=2                                                          //
x=4                                                              //
println(x)                                                       //
                                                                 // 
u can also explicity state                                       //
var y: Int 2                                                     //
///////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
we can create blocks of code using { } to surround them. the last expression which is executed in the block is the value of the block//
                                                                                                                                     //
println({                                                                                                                            //
  val x= 3                                                                                                                           //
  x+2                                                                                                                                //
})//O/P: 5                                                                                                                           //    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////
FUNCTIONS:                                                        //
//defining an anaonymous function(func with no name):             //
(x: int)=>x+1  //this function accepts a value x and returns x+!  //
                                                                  //
//naming functions:                                               //
val addone=(x:Int)=>x+1                                           //
println(addone(1)) //O/P: 2                                       //
                                                                  //
//function with multiple parameters                               //
val multiply=(x: Int, y: Int)=>x*y                                //
println(multiply(2,3))                                            //
                                                                  //
//func with no parameters                                         //
val ans=()=>42                                                    //
println(ans()) //O/P: 42                                          //
////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
METHODS:                                                                                                                  //
                                                                                                                          // 
methods are similaar to functions                                                                                         // 
methods are defined using def keyword                                                                                     //
in methods parameters are list of values or variables                                                                     //
                                                                                                                          //
def name(x: Int, y: Int): Int=x+y                                                                                         //
                        //this int defines the return type of method                                                      //
u can also create multiple list of parameters in methods                                                                  //
                                                                                                                          //
def addthenmultiply(x:Int, y:Int)(multiplier: Int): Int= (x+y)*multiplier                                                 //
println(addThenMultiply(1, 2)(3)) //O/P: 9                                                                                //
                                                                                                                          //
making a no parameter method                                                                                              //
                                                                                                                          //
def ans:String="Hello"                                                                                                    //
println(ans)                                                                                                              //
OR                                                                                                                        //
def ans(): String= "Hello"                                                                                                //
println(ans())                                                                                                            //
                                                                                                                          //
methods can have multiline expressions                                                                                    //
                                                                                                                          //
def squaredString(x: Double):String= {                                                                                    //
  val square=x*x                                                                                                          //
  square.toString                                                                                                         // 
}                                                                                                                         //
println(squaredString(2.5)) // 6.25                                                                                       //
//The last expression in the body is the method’s return value. (Scala does have a return keyword, but it is rarely used.)//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
CLASSES:                                                                                                                                        //
                                                                                                                                                //
classes are defined using the class keyword                                                                                                     //
ex:                                                                                                                                             //
                                                                                                                                                //
class Greeter(prefix: String, suffix: String){  //naming convention: class name should start with capital letter                                //
  def greet(name: String): Unit=                //return type of method is unit because we are not returning anything. it is like "void" in c   //
    println(prefix+name+suffix)                                                                                                                 //
}                                                                                                                                               //
val greeter=new Greeter("Hello", "how are you?")                                                                                                //
greeter.greet("Zain")                                                                                                                           //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////
CASE CLASSES:                                                  //
                                                               //
instance of case class is immutable                            // 
u can define it using case class keyword                       // 
                                                               // 
case class Point(x: Int, y: Int)                               //
                                                               //
creating instanaces of the case class                          //
                                                               // 
val point1=Point(1,2)                                          //
val point2=Point(2,3)                                          //
val point3=Point(2,3)                                          //
//no need of "new" keyword                                     //
                                                               // 
instances of case classes are compared by value not reference: //
println(point2==point3) //O/P: true                            //
/////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
OBJECTS:                                                                                                                                                              //
                                                                                                                                                                      //
objects in scala are singleton classes(classes which can have only a single instance)                                                                                 //
objects are created using the object keyword                                                                                                                          //
                                                                                                                                                                      //
object IDmaker{          here the object has name IDmaker, scala automatically creates instance with the name 'IDmaker' which will be the only instance of this object//
  private var id=0  //private keyword makes the variable only accesible in the object scope only u cant access it using IDaker.id                                     //
def creatID(): Int={                                                                                                                                                  //
    id++                                                                                                                                                              //
    id                                                                                                                                                                //
              }                                                                                                                                                       //
              }                                                                                                                                                       //
val id1= IDmaker.create()                                                                                                                                             //
println(id1)                                                                                                                                                          //
val id2= IDmaker.create()                                                                                                                                             //
println(id2)                                                                                                                                                          // 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
TRAITS:                                                                                                                      //
                                                                                                                             //
traits are like classes, in scala a class can only inherit from only 1 other class, but a class can inherit from many traits,//
scala supports multiple inheritance only thru trait,                                                                         //
                                                                                                                             // 
traits can be defined using trait keywordd                                                                                   //
                                                                                                                             // 
trait Greeter{                                                                                                               //
  def greet(name: String): Unit                                                                                              //
}                                                                                                                            // 
you cann also implement code in the metjod of the trait                                                                      //
                                                                                                                             // 
trait Greeter{                                                                                                               // 
  def greet(name: String)=                                                                                                   // 
    println("Hello"+name)                                                                                                    //
}                                                                                                                            // 
                                                                                                                             // 
extending a trait using class, also a demonstration of 'override' keyword to override a method                               // 
                                                                                                                             // 
class CustomGreeter(prefix: String, postfix: String) extends Greeter{                                                        //
  override def greet(name: String): Unit=                                                                                    //  
    println(prefix+name+postfix)                                                                                             //
}                                                                                                                            //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



The main method is the entry point of a Scala program. The Java Virtual Machine requires a main method, named main, that takes one argument: an array of strings.

In Scala 2 you must define a main method manually. Using an object, you can define the main method as follows:

object Main {
  def main(args: Array[String]): Unit =
    println("Hello, Scala developer!")
}
