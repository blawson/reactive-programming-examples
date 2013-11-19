package week1

object test {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  val f: PartialFunction[String, String] = { case "ping" => "pong" }
                                                  //> f  : PartialFunction[String,String] = <function1>
  
  // function is evaluate and the pattern above is matched
  f("ping")                                       //> res0: String = pong
  // no case matching, can check through partial function
  f.isDefinedAt("abc")                            //> res1: Boolean = false

	val f2: PartialFunction[List[Int], String] = {
		case Nil => "one"
		case x :: y :: rest => "two"
	}                                         //> f2  : PartialFunction[List[Int],String] = <function1>
	
	f2.isDefinedAt(List(1, 2, 3))             //> res2: Boolean = true

	val g: PartialFunction[List[Int], String] = {
		case Nil => "one"
		case x :: rest =>
			rest match {
				case Nil => "two"
			}
	}                                         //> g  : PartialFunction[List[Int],String] = <function1>
	
	// the function is defined, but the nested case won't work
	// you will get a match error due to the nested case
	g.isDefinedAt(List(1, 2, 3))              //> res3: Boolean = true
}