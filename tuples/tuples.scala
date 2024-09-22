//creating a tuple which has 2elements
val person=("zain",21)
///This creates a tuple containing a String element and an Int element.
// infered type of tuple is (String,Int)
u can make tuple also like
val person: (String, Int) = ("Zain", 22)
///////////////////////////////////////////////////////////////////////

ACCESSING ELEMENTS:

elements are accesed using indexes like "_1","_2"etc

println(person(_1))//Zain
println(person(_2))//21

USING TUPLE DESTRUCTURING:

val (name,age)=person
println(name)// Zain
println(age)  // 21
//////////////////////////////////////////////////////


CASES AND  PATTERN MATCHING:

val planets =
  List(("Mercury", 57), ("Venus", 108), ("Earth", 149),
       ("Mars", 227), ("Jupiter", 778))
planets.foreach {
  case ("Earth", distance) =>
    println(s"Our planet is $distance million kilometers from the sun")
  case _ =>
}
////////////////////////////////////////////////////////////////////////

FOR COMPREHENSION:

val numPairs = List((2, 5), (3, -7), (20, 56))
for ((a, b) <- numPairs) {  //similar operation to val (name,age)=person
  println(a * b)
}
