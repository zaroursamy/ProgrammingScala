import java.io.PrintWriter

/**
  * Created by Samy on 24/07/2016.
  */
object Chapitre9 {
  // control abstraction


  //private def filesHere = (new java.io.File("C:\\Users\\Samy\\IdeaProjects\\ProgrammingScala\\src")).listFiles
  private def filesHere = (new java.io.File("D:\\Users\\samy.zarour\\Documents\\ProgrammingScala\\src")).listFiles
  def filesMatching (matcher: (String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName)) yield file
  }

  def filesEnding(query: String) = filesMatching( _.endsWith(query)) // closure car query est exterieur

  def filesContaining(query: String) = filesMatching(_.contains(query))

  def filesRegex(query: String) = filesMatching(_.matches(query))

  def addCurryied(x: Int)(y: Int) = x+y
  def addNoCurryied(x:Int, y:Int) = x+y

  def twice(op: Double => Double, x: Int) = op(op(x))

  import java.io.File
  // assure que le fichier est fermé a la fin: loan pattern
  def withWriterPrinter(file: File, op: PrintWriter => Unit )= {

    val writer = new PrintWriter(file)
    try{
      op(writer)
    }
    finally {
      writer.close()
    }
  }

  def withWriterPrinterCurry(file: File)( op: PrintWriter => Unit )= {

    val writer = new PrintWriter(file)
    try{
      op(writer)
    }
    finally {
      writer.close()
    }
  }


  // on souhaite généraliser maintenant, en passant nimporte quelle methode en parametre


  def main(args: Array[String]): Unit = {

    // higher order function : fonction qui prend en parametre des fonctions. Permet d'eviter la duplication de code

    filesEnding(".scala").foreach(println(_))
    println("containing")
    filesContaining("9").foreach(println(_))
    println("regex")
    filesRegex(".*scala*").foreach(println(_))
    println()
    //println("matching")
    //filesMatching("3", (x,y) => x.contains(y)).foreach(println)
    // filesMatching("Chapitre9.scala", _.endsWith(_)).foreach(println)
    println(List(1,2,3,-2,4,8,-6).exists(_<0))
    println("Curryfication-------------------------")
    println(addCurryied(3)(4))
    println(addNoCurryied(3,4))

    val onePlus = addCurryied(1)_
    println(onePlus)
    println(onePlus(1))

    withWriterPrinter(new File("D:\\Users\\samy.zarour\\Documents\\RTE\\reunion 2107"), writer => println(writer.println(new java.util.Date)))


    // pour une fonction a un seul argument, on peut mettre des {} au lieu de ()
    val file = new File("D:\\Users\\samy.zarour\\Documents\\RTE\\reunion 2107")

    withWriterPrinterCurry(file){
      writer => writer.println(new java.util.Date)
    }

    // by-name parameters
    println("by name parameters")
    var assertionEnabled = true

    def myAssert(predicate: () => Boolean) = if(assertionEnabled && !predicate()) throw new AssertionError

    // predicate: =>Boolean, 5>3 n'est pas évalué avant l'appel de bynameAssert. Une fonction value est appelée, avec apply, pour évaluer 5>3
    def bynameAssert(predicate: => Boolean) = if(assertionEnabled && !predicate) throw new AssertionError

    // predicate:Boolean donc 5>3 est évalué avant l'appel de boolAssert
    def boolAssert(predicate: Boolean) = if(assertionEnabled && !predicate) throw new AssertionError

    println(myAssert(() => 5>3))
    println(bynameAssert(5>3))
    println(boolAssert(5>3))

    assertionEnabled = false
//    println("boolAssert:")
//    boolAssert(3/0 == 0) // arithmetiexception
    println("bynameAssert:")
    bynameAssert(3/0 == 0) // pas d'exception

  }
}
