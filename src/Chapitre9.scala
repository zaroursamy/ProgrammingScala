/**
  * Created by Samy on 24/07/2016.
  */
object Chapitre9 {

  private def filesHere = (new java.io.File("C:\\Users\\Samy\\IdeaProjects\\ProgrammingScala\\src")).listFiles

  def filesEnding(query: String) = {
    for(files <- filesHere; if files.getName.endsWith(query)) yield files
  }

  def filesContaining(query: String) = {
    for(files <- filesHere; if files.getName.contains(query)) yield files
  }

  def filesRegex(query: String) = {
    for(files <- filesHere; if files.getName.matches(query)) yield files
  }

  // on souhaite généraliser maintenant, en passant nimporte quelle methode en parametre
  def filesMatching (query: String, matcher: (String, String) => Boolean) = {
    for (file <- filesHere; if matcher(file.getName, query)) yield file
  }

  def main(args: Array[String]): Unit = {

    // higher order function : fonction qui prend en parametre des fonctions

    filesEnding(".scala").foreach(println(_))
    println("containing")
    filesContaining("9").foreach(println(_))
    println("regex")
    filesRegex(".*scala*").foreach(println(_))
    println("matching")
    filesMatching("3", (x,y) => x.contains(y)).foreach(println)
    filesMatching("Chapitre9.scala", _.endsWith(_)).foreach(println)
  }
}
