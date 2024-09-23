abstract class A {
  val message: String
}
class B extends A {
  val message = "I'm an instance of class B"
}
trait C extends A {
  def loudMessage = message.toUpperCase()
}
class D extends B with C
//now class d code looks like:
// val message:String="I'm an instance of class B"
// def loudMessage=message.toUpperCase()

val d = new D
println(d.message)  // I'm an instance of class B
println(d.loudMessage)  // I'M AN INSTANCE OF CLASS B
///////////////////////////////////////////////////////


CUSTOM ITERATORS USING MIXIN:

abstract class abs{
  type T
  def hasNext:Boolean
  def next():T

class StringIterator(s:String) extends abs{
  T=Char
  private var i=0
  def hasNext=i<s.length
  def next()={
    val ch= s charAt i
    i+=1
    ch
  }
}

trait richIterator extends abs{
  def foreach(f:T => Unit): Unit= while hasNext() f(next())
}        //syntax for^ is like↓ 
        //val funcName: ArgumentType => ReturnType = (argument) => implementation


                    //accepts a string as argument
class RichStringIterator(s:String) extends StringIterator(s) with richIterator{
                                          //also passes the argument to superclass
  
val iterateString=new RichStringIterator("Zain")
iterateString.foreach(println)
