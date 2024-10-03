////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
FUTURES:                                                                                                                                                                                                          //
                                                                                                                                                                                                                  //
val inverseFuture: Future[Matrix] = Future {                                                                                                                                                                      //
  fatMatrix.inverse() // non-blocking long lasting computation                                                                                                                                                    //
}(executionContext)                                                                                                                                                                                               //
///////////////////////////////////////////////////////////////                                                                                                                                                   //
                                                                                                                                                                                                                  //
implicit val ec: ExecutionContext = ...                                                                                                                                                                           //
val inverseFuture : Future[Matrix] = Future {                                                                                                                                                                     //
  fatMatrix.inverse()                                                                                                                                                                                             //
} // ec is implicitly passed                                                                                                                                                                                      //
/////////////////////////////////////////////////////////////we can also use the default execution context provided by scala(global execution context)                                                            //
                                                          //we can also pass this global context implicityly(without explicitly mentioning like we did in above code                                              //
lets make write a code which fetches a data from a hypothetical api(which requires user authentication and authorisation):                                                                                        //
                                                                                                                                                                                                                  //
import scala.concurrent._                                                                                                                                                                                         //
import ExecutionContext.Implicits.global//importing a package which passes global execution context implicitly                                                                                                    //
                                                                                                                                                                                                                  //
            //⬇️apiname⬇️                                                                                                                                                                                          //
val session=socialNetwork.creatSessionFor("username",credentials)                                                                                                                                                  //
val f: Future[List[Friend]]=Future{                                                                                                                                                                               //
  session.getFriends()                                                                                                                                                                                            //
}                                                                                                                                                                                                                 //
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                                                       //
                                                                                                                                                                                                                  //
another example where we search an pccurancae of the first keyword in a text file, this process may take a while so it make sense to not block the rest of the code and make this coe run concurrently            //
                                                                                                                                                                                                                  //
val firstOccurrence: Future[Int] = Future {                                                                                                                                                                       //
  val source = scala.io.Source.fromFile("myText.txt")                                                                                                                                                             //
  source.toSeq.indexOfSlice("myKeyword")                                                                                                                                                                          //
}                                                                                                                                                                                                                 //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////          //
                                                                                                                                                                                                                  //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

CALLBACKS:                                                                                                                                                                                                                              //
                                                                                                                                                                                                                                        //
so far we've seen how to asynchronously run a code where we fetch data, what about the part when we recreive the data? how to handle that?                                                                                              //
this is what callbacks are                                                                                                                                                                                                              //
                                                                                                                                                                                                                                        //
the callback is executed by some thread, at some time after the future object is completed. We say that the callback is executed eventually.                                                                                            //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
The most general form of registering a callback is by using the onComplete method, which takes a callback function of type Try[T] => U.                                                                                                 //
The callback is applied to the value of type Success[T] if the future completes successfully, or to a value of type Failure[T] otherwise.                                                                                               //
The Try[T] is similar to Option[T]:                                                                                                                                                                                                     //
    Where an Option[T] could either be a value (i.e. Some[T]) or no value at all (i.e. None), Try[T] is a Success[T] when it holds a value and otherwise Failure[T]                                                                     //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
Coming back to our social network example,                                                                                                                                                                                              //
let’s assume we want to fetch a list of our own recent posts and render them to the screen.                                                                                                                                             //
We do so by calling a method getRecentPosts which returns a List[String] – a list of recent textual posts                                                                                                                               //
                                                                                                                                                                                                                                        //
import scala.util.{Success, Failure}                                                                                                                                                                                                    //
val f: Future[List[String]] = Future {                                                                                                                                                                                                  //
  session.getRecentPosts()                                                                                                                                                                                                              //
}                                                                                                                                                                                                                                       //
f.onComplete {                                                                                                                                                                                                                          //
  case Success(posts) => for (post <- posts) println(post)                                                                                                                                                                              //
  case Failure(t) => println("An error has occurred: " + t.getMessage)                                                                                                                                                                  //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
or                                                                                                                                                                                                                                      //
                                                                                                                                                                                                                                        //
The onComplete method is general in the sense that it allows the client to handle the result of both failed and successful future computations.                                                                                         //
In the case where only successful results need to be handled, the foreach callback can be used:                                                                                                                                         //
                                                                                                                                                                                                                                        //
val f: Future[List[String]] = Future {                                                                                                                                                                                                  //
  session.getRecentPosts()                                                                                                                                                                                                              //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
for {                                                                                                                                                                                                                                   //
  posts <- f                                                                                                                                                                                                                            //
  post <- posts                                                                                                                                                                                                                         //
} println(post)                                                                                                                                                                                                                         //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
Coming back to the previous example with searching for the first occurrence of a keyword, you might want to print the position of the keyword to the screen:                                                                            //
                                                                                                                                                                                                                                        //
val firstOccurrence: Future[Int] = Future {                                                                                                                                                                                             //
  val source = scala.io.Source.fromFile("myText.txt")                                                                                                                                                                                   //
  source.toSeq.indexOfSlice("myKeyword")                                                                                                                                                                                                //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
firstOccurrence.onComplete {                                                                                                                                                                                                            //
  case Success(idx) => println("The keyword first appears at position: " + idx)                                                                                                                                                         //
  case Failure(t) => println("Could not process file: " + t.getMessage)                                                                                                                                                                 //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
RACECONDIONS in callbacks:                                                                                                                                                                                                              //
                                                                                                                                                                                                                                        //
@volatile var totalA = 0  //'@volatile' ensures that totalA is always visible to all threads immediately when updated, preventing them from using outdated (cached) values                                                              //
                                                                                                                                                                                                                                        //
val text = Future {                                                                                                                                                                                                                     //
  "na" * 16 + "BATMAN!!!"                                                                                                                                                                                                               //
}                                                                                                                                                                                                                                       //
text.foreach { txt =>                                                                                                                                                                                                                   //
  totalA += txt.count(_ == 'a')                                                                                                                                                                                                         //
}                                                                                                                                                                                                                                       //
text.foreach { txt =>                                                                                                                                                                                                                   //
  totalA += txt.count(_ == 'A')                                                                                                                                                                                                         //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
here value of totalA can either be 2 or 16                                                                                                                                                                                              //
                                                                                                                                                                                                                                        //
The possible values of totalA given the race condition:                                                                                                                                                                                 //
        •16: If only the lowercase 'a' count (16) is added and the uppercase 'A' count is missed.                                                                                                                                       //
        •2: If only the uppercase 'A' count (2) is added and the lowercase 'a' count is missed.                                                                                                                                         //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
Suppose we want to buy US dollars, but only when it’s profitable. We first show how this could be done using callbacks:                                                                                                                 //
val rateQuote = Future {                                                                                                                                                                                                                //
  connection.getCurrentValue(USD)                                                                                                                                                                                                       //
}                                                                                                                                                                                                                                       //
                                                            //This works, but is inconvenient for two reasons. First, we have to use foreach and nest the second purchase future within it.                                             //
for (quote <- rateQuote) {                                  //Imagine that after the purchase is completed we want to sell some other currency. We would have to repeat this pattern within the foreach callback.                       //
  val purchase = Future {                                                                                                                                                                                                               //
    if (isProfitable(quote)) connection.buy(amount, quote)  ///Second, the purchase future is not in the scope with the rest of the code – it can only be acted upon from within the foreach callback.                                  //
    else throw new Exception("not profitable")              // This means that other parts of the application do not see the purchase future and cannot register another foreach callback to it for example to sell some other currency.//
  }                                                                                                                                                                                                                                     //
                                                                                                                                                                                                                                        //
  for (amount <- purchase)                                                                                                                                                                                                              //
    println("Purchased " + amount + " USD")                                                                                                                                                                                             //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
                                                                                                                                                                                                                                        //
previous example using the map combinator:    //map: Transforms the result of the Future and returns a new Future.                                                                                                                      //
val rateQuote = Future {                                    // It allows you to apply a function to the value inside the Future and return the result wrapped in another Future                                                         //
                                                                                                                                                                                                                                        //
  connection.getCurrentValue(USD)                                                                                                                                                                                                       //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
val purchase = rateQuote.map { quote =>                                                                                                                                                                                                 //
  if (isProfitable(quote)) connection.buy(amount, quote)                                                                                                                                                                                //
  else throw new Exception("not profitable")                                                                                                                                                                                            //
}                                                                                                                                                                                                                                       //
                                                                                                                                                                                                                                        //
purchase.foreach { amount =>                                                                                                                                                                                                            //
  println("Purchased " + amount + " USD")                                                                                                                                                                                               //
}                                                                                                                                                                                                                                       //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
FOR COMPREHENSION:                                                                                                                                         //
                                                                                                                                                           //
IF U WANT TO ACCESS MULTIPLE FUTURES USING 1BLOCK OF CODE THIS CAN BE DONE USING FOR COMPREHENSION                                                         //
AND YES THIS IS LIKE THE REGULAR 'FOR COMPREHENSION':                                                                                                      //
                                                                                                                                                           //
 ex: we want to exchange US dollars for Swiss francs (CHF). We have to fetch quotes for both currencies, and then decide on buying based on both quotes.   //
                                                                                                                                                           //
val usdQuote = Future { connection.getCurrentValue(USD) }                                                                                                  //
val chfQuote = Future { connection.getCurrentValue(CHF) }                                                                                                  //
                                                                                                                                                           //
val purchase = for {                                                                                                                                       //
  usd <- usdQuote                                                                                                                                          //
  chf <- chfQuote                                                                                                                                          //
  if isProfitable(usd, chf)                                                                                                                                //
} yield connection.buy(amount, chf)                                                                                                                        //
                                                                                                                                                           //
purchase foreach { amount =>                                                                                                                               //
  println("Purchased " + amount + " CHF")                                                                                                                  //
}                                                                                                                                                          //
                                                                                                                                                           //
                                                                                                                                                           //
assume that based on the rateQuote we decide to buy a certain amount. The connection.buy method takes an amount to buy and the expected quote.             //
It returns the amount bought. If the quote has changed in the meanwhile, it will throw a QuoteChangedException and it will not buy anything.               //
If we want our future to contain 0 instead of the exception, we use the recover combinator:                                                                //
                                                                                                                                                           //
val purchase: Future[Int] = rateQuote.map {                                                                                                                //
  quote => connection.buy(amount, quote)                                                                                                                   //
}.recover {                                                                                                                                                //
  case QuoteChangedException() => 0    //QuoteChangedException(): This is a custom exception class, extending Exception or another exception type in Scala //
}                                                                                                                                                          //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
.MAP:                                                                                                                              //
.map is used to modify the result of the Future when it completes, but it doesn’t block or wait—it’s non-blocking and asynchronous.//
so it becomes like part of the future execution in the sense that its execution tails behindd the future                           //
                                                                                                                                   //
val usdQuote = Future { connection.getCurrentValue(USD) }                                                                          //
val usdString = usdQuote.map { usd =>                                                                                              //
  "Value: " + usd + "$"                                                                                                            //
}                                                                                                                                  //
                                                                                                                                   //
or                                                                                                                                 //
                                                                                                                                   //
val usdQuote = Future { connection.getCurrentValue(USD) }.map { usd =>                                                             //
  "Value: " + usd + "$"                                                                                                            //
}                                                                                                                                  //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
FALLBACKS:                                                                                                      //
                                                                                                                //
fallback is like exception handling for futures. if a future fails then it switches to another future specified //
ex:                                                                                                             //
//val future1: Future[T] = ...                                                                                  //
//val future2: Future[T] = ...                                                                                  //
//val result: Future[T] = future1.fallbackTo(future2)                                                           //
                                                                                                                //
                                                                                                                //
val usdQuote = Future {                                                                                         //
  connection.getCurrentValue(USD)                                                                               //
}.map {                                                                                                         //
  usd => "Value: " + usd + "$"                                                                                  //
}                                                                                                               //
val chfQuote = Future {                                                                                         //
  connection.getCurrentValue(CHF)                                                                               //
}.map {                                                                                                         //
  chf => "Value: " + chf + "CHF"                                                                                //
}                                                                                                               //
                                                                                                                //
val anyQuote = usdQuote.fallbackTo(chfQuote)                                                                    //
anyQuote.foreach { println(_) }                                                                                 //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
ANDTHEN:                                                                                                                                                      //
                                                                                                                                                              //
andThen in Scala is a method used to attach a side-effect to a Future that runs after the Future completes, regardless of whether it was successful or failed.// 
It allows you to execute additional code without modifying the result of the Future.                                                                          //
                                                                                                                                                              //
ex:                                                                                                                                                           //
//future.andThen {                                                                                                                                            //
  //case Success(result) => // Code to run if the Future succeeds                                                                                             //
  //case Failure(exception) => // Code to run if the Future fails                                                                                             //
//}                                                                                                                                                           //
                                                                                                                                                              //
                                                                                                                                                              //
Future {                                                                                                                                                      //
  session.getRecentPosts()                                                                                                                                    //
}.andThen {                                                                                                                                                   //
  case Success(posts) => allPosts ++= posts                                                                                                                   //
}.andThen {                                                                                                                                                   //
  case _ =>                                                                                                                                                   //
    clearAll()                                                                                                                                                //
    for (post <- allPosts) render(post)                                                                                                                       //
}                                                                                                                                                             //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
BLOCKING:                                                                                                                                                                                   //
Futures are generally asynchronous and do not block the underlying execution threads. However, in certain cases, it is necessary to block                                                   //
two forms of blocking the execution thread:                                                                                                                                                 //
                              First Type: Blocking Within a Future - The Future itself blocks its own thread while executing some long-running code (like Thread.sleep).                    //
                              Second Type: Blocking Outside a Future - The code outside the Future blocks execution (using something like Await.result) until the Future finishes executing.//
inside future:                                                                                                                                                                              //
1.  val blockingFuture = Future {                                                                                                                                                           //
    Thread.sleep(1000) // Blocks for 1 second                                                                                                                                         //
    "Result"                                                                                                                                                                                //
  }                                                                                                                                                                                         //
                                                                                                                                                                                            //
blocking on a future is strongly discouraged for the sake of performance and for the prevention of deadlocks.                                                                               //
                                                                                                                                                                                            //
                                                                                                                                                                                            //
outside future:                                                                                                                                                                             //
2.  val rateQuote = Future {                                                                                                                                                                //
      connection.getCurrentValue(USD)                                                                                                                                                       //
    }                                                                                                                                                                                       //
                                                                                                                                                                                            //
    val purchase = rateQuote.map { quote =>                                                                                                                                                 //
      if (isProfitable(quote)) connection.buy(amount, quote)                                                                                                                                //
      else throw new Exception("not profitable")                                                                                                                                            //
    }                                                                                                                                                                                       //
                                                                                                                                                                                            //
   Await.result(purchase, 5.seconds)  //blocks for 5secs. if future completes before 5secs it gets unblocked if future doesnt complete until 5secs timer then it unblocks anyways           //
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



