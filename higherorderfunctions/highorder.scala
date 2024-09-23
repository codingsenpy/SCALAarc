high order functions are those functions which take a function as a parameter or return a function
one good example is map()

var salary=Seq(200,300,400,100)
val doubleit=(a:Int)=>a*2
println(salary.map(doubleit)) //400,600,800,200

or

println(salary.map(x=>x*2))  //Notice how x is not declared as an Int in the above example. That’s because the compiler can infer the type based on the type of function map expects (see Currying).

or

println(salary.map(_ *2))  //Since the Scala compiler already knows the type of the parameters (a single Int), you just need to provide the right side of the function. The only caveat is that you need to use _ in place of a parameter name (it was x in the previous example).
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

u can also pass methods as arguments to higher order function as the scala compiler will turn it into a function

case class WeatherForecast(temperatures: Seq[double]){
  private def ctof(temp:Double)=temp * 1.8 + 32
  def forecastinF:Seq[Double]=temperatures.map(ctof)
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



FUNCTIONS THAT RETURN FUNCTIONS:
                              //return type of method is a func which takes 2string parameters
                                           //  ⬇️          ⬇️
def urlBuilder(ssl:Boolean, domainName:String):(String,String)=>String={
  val schema:String=if(ssl) "https://" else "http://"
  (endpoint:String,query: String)=>s"$schema$domainName/$endpoint?$query"
}//this func is the return statement

val geturl=urlBuilder(true,"youtube.com")
val endpoint="myaccount"
val query="settings"
println(geturl(endpoint,query))
