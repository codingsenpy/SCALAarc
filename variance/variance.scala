/////////////////////////////////////////                                                                                                 
/class Foo[+A] // A covariant class    //        can only get                                                                                         
/class Bar[-A] // A contravariant class//                                can only put                                                                 
/class Baz[A]  // An invariant class   //                                                                                                 
/////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
INVARIANCE:                                                                                                                               // 
                                                                                                                                          //
class Box[A](var content: A)                                                                                                              //
//^this is primary constructor func                                                                                                       //
/////////////////////////////////                                                                                                         //
                                                                                                                                          //
abstract class Animal{                                                                                                                    //
  def name:String                                                                                                                         //
}                                                                                                                                         //
                                                                                                                                          //
case class Cat(name:String) extends Animal                                                                                                //
case class Dog(name:String) extends Animal                                                                                                //
                                                                                                                                          //
here cat and dog are subtypes of animal so we can ⬇️                                                                                     // 
                                                                                                                                          //
val kitttycat:Animal=Cat("HelloKitty")                                                                                                    //
//////////////////////////////////////////////////////                                                                                    //
                                                                                                                                          //
val kittyBoxed: Box[Cat]=new Box[Cat](Cat("byeKitty"))                                                                                    //
val myAnimalBox: Box[Animal] = myCatBox // this doesn't compile, Box[Cat] IS NOT a subtype of Box[Animal], like Cat is a subtype of Animal//
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
COVARIANCE:                                                                                                                                                                        //
                                                                                                                                                                                   //
class ImmutableBox[+A](val content: A)                                                     More formally, that gives us the following relationship: given some class Cov[+T],      //
val catbox: ImmutableBox[Cat] = new ImmutableBox[Cat](Cat("Felix"))                        then if A is a subtype of B, Cov[A] is a subtype of Cov[B].                             //
val animalBox: ImmutableBox[Animal] = catbox /* now this compiles */                       This allows us to make very useful and intuitive subtyping relationships using generics.//
                                                                                                                                                                                   //
                                                                                                                                                                                   //
                                                                                                                                                                                   //
                                                                                                                                                                                   //
In the following less contrived example,                                                                                                                                           //
the method printAnimalNames will accept a list of animals as an argument and print their names each on a new line.                                                                 //
If List[A] were not covariant, the last two method calls would not compile, which would severely limit the usefulness of the printAnimalNames method.                              //
                                                                                                                                                                                   //
def printAnimalNames(animals: List[Animal]): Unit =                                                                                                                                //
  animals.foreach {                                                                                                                                                                //
    animal => println(animal.name)                                                                                                                                                 //
  }                                                                                                                                                                                //
                                                                                                                                                                                   //
val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))                                                                                                                            //
val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))                                                                                                                                //
                                                                                                                                                                                   //
// prints: Whiskers, Tom                                                                                                                                                           //
printAnimalNames(cats)                                                                                                                                                             //
                                                                                                                                                                                   //
// prints: Fido, Rex                                                                                                                                                               //
printAnimalNames(dogs)                                                                                                                                                             //
                                                                                                                                                                                   //
                                                                                                                                                                                   //
The reason it works is due to the covariance of the List type in Scala,                                                                                                            //
denoted by the + symbol in its definition.                                                                                                                                         //
When a class is declared as covariant, it allows you to substitute a subtype for a supertype in a type-safe way.                                                                   //
                                                                                                                                                                                   //
Covariant Declaration: Since List is declared as List[+A], it tells the compiler that if B is a subtype of A, then List[B] can be treated as a subtype of List[A].                 //
Passing Lists: When you call printAnimalNames(cats) or printAnimalNames(dogs), the method expects a List[Animal].                                                                  //
Since both List[Cat] and List[Dog] are subtypes of List[Animal], the method accepts them without any compilation errors                                                            //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
CONTRAVARIANCE:                                                                                                                                      //
                                                                                                                                                     //
abstract class Serializer[-A]{                                                                                                                       //
  def serialize(a:A):String                                                                                                                          //
}                                                                                                                                                    //
val animalSerializer: Serializer[Animal] = new Serializer[Animal]{                                                                                   //
  def serialize(animals:Animal):String=s"""{ "name": "${animal.name}" }"""                                                                           //
}                                                                                                                                                    //
val catserializer: serializer[Cat]=animalSerializer                                                                                                  //
catSerializer.serialize(Cat("Felix"))                                                                                                                //
                                                                                                                                                     //
More formally, that gives us the reverse relationship: given some class Contra[-T], then if A is a subtype of B, Contra[B] is a subtype of Contra[A].//
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
