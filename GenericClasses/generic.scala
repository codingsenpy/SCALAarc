class Stack[A]{
  private var stack: List[A]=Nil  // Nil means you're starting with an empty list
  def push(x:A): Unit={
    stack=x :: stack       // The :: operator prepends an element to the list
  }
  def peek: A=
    stack.head

  def pop:A={
    val currentTop=peek
    stack= stack.tail
    currentTop
  }
}

val stack = new Stack[Int]
stack.push(1)
println(stack.peek)
stack.push(2)
println(stack.pop())  // prints 2
println(stack.pop())  // prints 1
/////////////////////////////////

The instance stack can only take Ints. However, if the type argument had subtypes, those could be passed in:


class Fruit
class Apple extends Fruit
class Banana extends Fruit

val stack = new Stack[Fruit]
val apple = new Apple
val banana = new Banana

stack.push(apple)
stack.push(banana)
