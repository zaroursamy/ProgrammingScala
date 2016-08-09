/**
  * Created by Samy on 17/07/2016.
  */
object Chapitre7 {

  val filesHere2 = (new java.io.File("D:\\Data de C\\Documents de C")).listFiles()
  val filesHere = (new java.io.File("C:\\Users\\Samy\\IdeaProjects\\ProgrammingScala\\src")).listFiles()

  def gdcLoop(x: Long, y:Long): Long ={
    var a = x
    var b = y

    while(a != 0){
      val temp = a
      a = b % a
      b = temp
    }
    b
  }

  def gcd(x: Long, y: Long): Long = if(y==0) x else gcd(y, x%y)

  def greet(){println("unit")}

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def grep(pattern: String) =
    for (
      file <- filesHere
      if file.getName.endsWith(".scala");
      line <- fileLines(file)
      if line.trim.matches(pattern)
    )println(file +": "+ line.trim)

  def scalaFiles = for{
    file <- filesHere
    if(file.getName.endsWith(".scala"))
  } yield file

  def main(args: Array[String]): Unit = {
    println(gdcLoop(3l, 2l))
    println(greet() == ())

    //var line = ""
    //while((line=readLine()) != "") println("read"+line) // warning: comparaison de deux types différentes (unit et string)

    filesHere.foreach(println)
    for(file <- filesHere) println(file)

    // inclut le dernier element
    for(i <- 0 to 9) println(i)
    println("-----")
    // n'inclut pas le dernier element
    for(i <- 0 until 9) println(i)


    // filter
    for(file <- filesHere2) if(file.getName.endsWith("pdf")) println("c'est un pdf") else println("ce n'est pas un pdf")
    println("")
    for(
      file <- filesHere
      if file.isFile
      if file.getName.endsWith("pdf")
    ) println("c'est un fichier pdf")


    // nested loop
    for(num <- 0 to 5
        if num%2==0;
        let <- List("a","b","c","d")
        if let == "a" )println(num, let)
    println("-------------")
    grep(".*gcd.*")
    println("___________")
    scalaFiles.foreach(println)

    println()
    println("exceptions :")
    def half(n: Int) = if(n%2==0) n/2 else throw new RuntimeException("n n'est pas divisible par deux")

    println(half(4))


    // catch
    import java.io.FileReader
    import java.io.FileNotFoundException
    import java.io.IOException
    try {
      val f = new FileReader("C:\\Users\\Samy\\datasciencegame\\code\\chargement.py")

    } catch {
      case ex: FileNotFoundException => println("fichier manquant")
      case ex: IOException => println("autre erreur i/o")
    }

    val f = new FileReader("C:\\Users\\Samy\\datasciencegame\\code\\chargement.py")
    try{
      f.read()
    }finally f.close()

    val muscle = "dos"

    val training = muscle match{
      case "dos" => "rowing"
      case "jambes" =>"squat"
      case _ => "repos"
    }

    println(training)

    val com = List("voyage agreable", "super !", "à refaire !","bon conducteur", "impressionant!")

    var i = 0
    var foundIt = false
    var comExclamation: List[String] = List()

    while(i < com.length){
      if(com(i).endsWith("!")){
        comExclamation =  com(i)::comExclamation
        foundIt = true
      }
      i += 1
    }

    comExclamation.foreach(println)
    println("ou encore")
    com.filter(_.endsWith("!")).foreach(println)
    println("mdr")


    // tout ce qui est définit dans la loop reste dans la loop; inutilisable ailleurs
    i = 0
    while(i < 10){
      var j = 0
      if(j % 2 == 0) j = 1 else j = 1
    }

    println("----")
    val testScope = 1

    {
      val testScope = 0
      println(testScope)
    }
    println(testScope)


    println("okkkkk")

    // yield: permet de creer une collection a partir d'une boucle
    def makeRowSeq(row: Int) =
      for (col <- 1 to 10) yield{
        val prod = (row*col).toString
        val padding = " " * (4-prod.length)
        padding + prod
      }

    makeRowSeq(2).foreach(println)

    def multiTable() = {
      val tableSeq = for(row <- 1 to 10) yield {
        makeRowSeq(row)
      }
      tableSeq.mkString("\n")
    }

    multiTable()



  }
}
