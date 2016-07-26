/**
  * Created by Samy on 24/07/2016.
  */
object Chapitre9 {

  private def filesHere = (new java.io.File("C:\\Users\\Samy\\IdeaProjects\\ProgrammingScala\\src")).listFiles

  def filesMatching (matcher: (String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName)) yield file
  }

  def filesEnding(query: String) = filesMatching( _.endsWith(query)) // closure car query est exterieur

  def filesContaining(query: String) = filesMatching(_.contains(query))

  def filesRegex(query: String) = filesMatching(_.matches(query))

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
  }
}
