/**
  * Created by Samy on 24/07/2016.
  */
object Chapitre9 {

  private def filesHere = (new java.io.File("C:\\Users\\Samy\\IdeaProjects\\ProgrammingScala\\src")).listFiles

  def filesEnding(query: String) = {
    for(files <- filesHere; if files.getName.endsWith(query)) yield files
  }


  def main(args: Array[String]): Unit = {

    // higher order function : fonction qui prend en parametre des fonctions

    filesEnding(".scala").foreach(println(_))
  }
}
