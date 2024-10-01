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

CALLBACKS:

so far we've seen how to asynchronously run a code where we fetch data, what about the part when we recreive the data? how to handle that?
this is what callbacks are

the callback is executed by some thread, at some time after the future object is completed. We say that the callback is executed eventually.


The most general form of registering a callback is by using the onComplete method, which takes a callback function of type Try[T] => U. 
The callback is applied to the value of type Success[T] if the future completes successfully, or to a value of type Failure[T] otherwise.
The Try[T] is similar to Option[T]:
    Where an Option[T] could either be a value (i.e. Some[T]) or no value at all (i.e. None), Try[T] is a Success[T] when it holds a value and otherwise Failure[T]


Coming back to our social network example, 
let’s assume we want to fetch a list of our own recent posts and render them to the screen.
We do so by calling a method getRecentPosts which returns a List[String] – a list of recent textual posts

import scala.util.{Success, Failure}
val f: Future[List[String]] = Future {
  session.getRecentPosts()
}
f.onComplete {
  case Success(posts) => for (post <- posts) println(post)
  case Failure(t) => println("An error has occurred: " + t.getMessage)
}

or

The onComplete method is general in the sense that it allows the client to handle the result of both failed and successful future computations. 
In the case where only successful results need to be handled, the foreach callback can be used:

val f: Future[List[String]] = Future {
  session.getRecentPosts()
}

for {
  posts <- f
  post <- posts
} println(post)


Coming back to the previous example with searching for the first occurrence of a keyword, you might want to print the position of the keyword to the screen:

val firstOccurrence: Future[Int] = Future {
  val source = scala.io.Source.fromFile("myText.txt")
  source.toSeq.indexOfSlice("myKeyword")
}

firstOccurrence.onComplete {
  case Success(idx) => println("The keyword first appears at position: " + idx)
  case Failure(t) => println("Could not process file: " + t.getMessage)
}


RACECONDIONS in callbacks:

@volatile var totalA = 0  //'@volatile' ensures that totalA is always visible to all threads immediately when updated, preventing them from using outdated (cached) values

val text = Future {
  "na" * 16 + "BATMAN!!!"
}
text.foreach { txt =>
  totalA += txt.count(_ == 'a')
}
text.foreach { txt =>
  totalA += txt.count(_ == 'A')
}

here value of totalA can either be 2 or 16

The possible values of totalA given the race condition:
        •16: If only the lowercase 'a' count (16) is added and the uppercase 'A' count is missed.
        •2: If only the uppercase 'A' count (2) is added and the lowercase 'a' count is missed.
