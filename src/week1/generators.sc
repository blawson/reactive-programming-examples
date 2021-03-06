package week1

object generators {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  
  trait Generator[+T] {
  	self =>
  	
  	def generate: T
  	
  	def map[S](f: T => S): Generator[S] = new Generator[S] {
  		def generate = f(self.generate)
  	}
  	
  	def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
  		def generate = f(self.generate).generate
  	}
  }
  
  val integers = new Generator[Int] {
  	def generate = scala.util.Random.nextInt()
  }                                               //> integers  : week1.generators.Generator[Int] = week1.generators$$anonfun$main
                                                  //| $1$$anon$3@4b0ab323
  
  def single[T](x: T) = new Generator[T] {
  	def generate = x
  }                                               //> single: [T](x: T)week1.generators.Generator[T]
  
  val booleans = integers.map(_ >= 0)             //> booleans  : week1.generators.Generator[Boolean] = week1.generators$$anonfun$
                                                  //| main$1$Generator$1$$anon$1@54281d4b
  
  def pairs[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] = for {
  	x <- t
  	y <- u
  } yield (x, y)                                  //> pairs: [T, U](t: week1.generators.Generator[T], u: week1.generators.Generato
                                                  //| r[U])week1.generators.Generator[(T, U)]
  
  def emptyLists = single(Nil)                    //> emptyLists: => week1.generators.Generator[scala.collection.immutable.Nil.typ
                                                  //| e]
  
	def nonEmptyLists = for {
		head <- integers
		tail <- lists
	} yield head :: tail                      //> nonEmptyLists: => week1.generators.Generator[List[Int]]
	
	def lists: Generator[List[Int]] = for {
		cutoff <- booleans
		list <- if (cutoff) emptyLists else nonEmptyLists
	} yield list                              //> lists: => week1.generators.Generator[List[Int]]
	
	def test[T](r: Generator[T], noTimes: Int = 100)(test: T => Boolean) {
		for (_ <- 0 until noTimes) {
			val value = r.generate
			assert(test(value), "Test failed for: "+value)
		}
		println("Test passed "+noTimes+" times")
	}                                         //> test: [T](r: week1.generators.Generator[T], noTimes: Int)(test: T => Boolea
                                                  //| n)Unit
	test(pairs(lists, lists)) {
		case (xs, ys) => (xs ++ ys).length > xs.length
	}                                         //> java.lang.AssertionError: assertion failed: Test failed for: (List(),List()
                                                  //| )
                                                  //| 	at scala.Predef$.assert(Predef.scala:179)
                                                  //| 	at week1.generators$$anonfun$main$1$$anonfun$test$1$1.apply$mcVI$sp(week
                                                  //| 1.generators.scala:50)
                                                  //| 	at scala.collection.immutable.Range.foreach$mVc$sp(Range.scala:141)
                                                  //| 	at week1.generators$$anonfun$main$1.test$1(week1.generators.scala:48)
                                                  //| 	at week1.generators$$anonfun$main$1.apply$mcV$sp(week1.generators.scala:
                                                  //| 54)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at week1.generators$.main(week1.generators.scala:3)
                                                  //| 	at week1.generators.main(week1.generators.scala)
}