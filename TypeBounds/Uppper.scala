An upper type bound T <: A declares that type variable T refers to a subtype of type A

abstract class Animal {
 def name: String
}

abstract class Pet extends Animal {}

class Cat extends Pet {
  override def name: String = "Cat"
}

class Dog extends Pet {
  override def name: String = "Dog"
}

class Lion extends Animal {
  override def name: String = "Lion"
}

class PetContainer[P <: Pet](p: P) {    //here P is subtype of pet which could either be Cat or Dog
  def pet: P = p
}

val dogContainer = new PetContainer[Dog](new Dog)
val catContainer = new PetContainer[Cat](new Cat)

val lionContainer = new PetContainer[Lion](new Lion)// this would not compile
