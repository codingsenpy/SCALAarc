def factorial(x:Int):Int={
  def fact(x:Int,accumulator:Int):Tnt={
    if(x<=1) accumulator
    else fact(x-1,x*accumulator)
  }
  fact(x,1)
}

println("Factorial of 2: " + factorial(2))  //Factorial of 2: 2
println("Factorial of 3: " + factorial(3))  //Factorial of 3: 6
