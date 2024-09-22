an Any type list

val list: List[Any]= List(
  true,  //Boolean
  'c',  //Charater
  42,  //Integer
  "zain",  //String
  ()=>"anonymous function returning a string"
  )
list.foreach(ele=>println(ele))
//O/P:  true
//      c
//      42
//      zain
//      <function>
////////////////////////////////////////////////

TYPE CASTING:

val x: Long = 987654321
val y: Float = x.toFloat  // 9.8765434E8 (note that some precision is lost in this case)

val face: Char = '☺'
val number: Int = face  // 9786

val x: Long = 987654321
val y: Float = x.toFloat  // 9.8765434E8
val z: Long = y  // Does not work
///////////////////////////////////////////////////////////////////////////////////////////////



