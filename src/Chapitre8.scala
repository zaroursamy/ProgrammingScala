/**
  * Created by Samy.Zarour on 22/07/2016.
  */
object Chapitre8 {

  // metohde = fonction membre d'un object
  import scala.io.Source
  object LongLines{

    def processFile(filename: String, width: Int) ={
      val source = Source.fromFile(filename)

      for(line <- source.getLines()) processLine(filename, width, line)
    }

    private def processLine(filename: String, width: Int, line: String) ={

      if(line.length > width) println(filename+" "+line.trim)
    }
  }

  def main(args: Array[String]) {

  }
}
