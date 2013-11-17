package week1

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val f: PartialFunction[String, String] = { case "ping" => "pong" }
                                                  //> f  : PartialFunction[String,String] = <function1>
  
  // function is evaluate and the pattern above is matched
  f("ping")                                       //> res0: String = pong
  // no case matching, can check through partial function
  f.isDefinedAt("abc")                            //> res1: Boolean = false
}