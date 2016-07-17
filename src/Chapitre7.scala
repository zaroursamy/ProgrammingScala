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

    scalaFiles






  }
}
