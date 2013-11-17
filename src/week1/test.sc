package week1

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val f: String => String = { case "ping" => "pong" }
                                                  //> f  : String => String = <function1>
  
  // function is evaluate and the pattern above is matched
  f("ping")                                       //> res0: String = pong
  // no case matching, so an error is thrown
  f("abc")                                        //> scala.MatchError: abc (of class java.lang.String)
                                                  //| 	at week1.test$$anonfun$main$1$$anonfun$1.apply(week1.test.scala:6)
                                                  //| 	at week1.test$$anonfun$main$1$$anonfun$1.apply(week1.test.scala:6)
                                                  //| 	at week1.test$$anonfun$main$1.apply$mcV$sp(week1.test.scala:11)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week1.test$.main(week1.test.scala:3)
                                                  //| 	at week1.test.main(week1.test.scala)
}