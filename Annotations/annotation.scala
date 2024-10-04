@DEPRECATED:

@deprecated annotation is used to mark methods, classes, objects, or fields as deprecated. 
This serves as a warning to developers that the annotated code is outdated and might be removed in future versions

@deprecated("Use newFunction instead", "2.0")
def oldFunction() = {
  println("This function is deprecated.")
}

def newFunction() = {
  println("This is the new function.")
}

oldFunction()  // This will compile with a warning.
When you call oldFunction(), you'll see a compiler warning:

//op:warning: method oldFunction is deprecated: Use newFunction instead
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

@TAILREC:

@tailrec annotation in Scala is used to indicate that a method is a tail-recursive function. Tail recursion is a specific form of recursion where the recursive call is the last operation in the function, 
allowing the compiler to optimize the recursion by reusing the current function's stack frame instead of creating new ones. This avoids stack overflow issues

import scala.annotation.tailrec

@tailrec
def factorial(n: Int, acc: Int = 1): Int = {
  if (n <= 1) acc
  else factorial(n - 1, n * acc)  // Recursive call is the last action
}

println(factorial(5))  // Outputs 120
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

JAVA anotations in scala:

REFER DOCS
