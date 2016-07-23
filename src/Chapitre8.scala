/**
  * Created by Samy.Zarour on 22/07/2016.
  */
object Chapitre8 {

  // metohde = fonction membre d'un object
  import scala.io.Source
  class FindLongLines{

    def processFile(filename: String, width: Int) ={

      // on implémente une fnction dans une fonction, au lieu d'en faire une méthode privée
      // elle a accès aux parametres de la fonction globale
       def processLine(line: String) ={
        if(line.length > width) println(filename+" "+line.trim)
      }

      val source = Source.fromFile(filename)

      for(line <- source.getLines()) processLine(line)
    }


    def main(args: Array[String]) {
      val width = args(0).toInt
      for(x <- args.drop(1)) FindLongLines.processFile(x, width)
    }

  }


  def main(args: Array[String]): Unit = {
    // function litteral
    (x: Int) => x+1

      // function values (sont des objets)
      var increase = (x: Int) => x+1
      println(increase(3))



  }
}
