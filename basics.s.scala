Expressions are computable statements: //
                                       //
1 + 1                                  //
/////////////////////////////////////////


///////////////////////////////////////////
println(1)//O/P: 1                       //
println(1+1)//O/P:2                      //
println("hello world")//O/P:hello world  //
println("hello"+"world")//O/P:hello world  //
///////////////////////////////////////////

////////////////////////////////////////////
to name values of expressions we use val: //
                                          //
val x=1+1                                 //  x is called a value
println(x) //2                              //
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


FUNCTIONS:
//defining an anaonymous function(func with no name):
(x: int)=>x+1  //this function accepts a value x and returns x+!

//naming functions:
val addone=(x:Int)=>x+1
println(addone(1)) //O/P: 2

//function with multiple parameters
val multiply=(x: Int, y: Int)=>x*y
println(multiply(2,3))

//func with no parameters
val ans=()=>42
println(ans()) //O/P: 42

