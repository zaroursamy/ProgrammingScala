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


//    def main(args: Array[String]) {
//      val width = args(0).toInt
//      for(x <- args.drop(1)) processFile(x, width)
//    }

  }


  def main(args: Array[String]): Unit = {

    println("ok")

    // function values (sont des objets)
    var increase = (x: Int) => x+1
    println(increase(3))
    // sur plusieurs lignes
    increase = (x:Int) => {
      var i = 2
      x+i

    }

    List(1,10,100,1000).foreach((x:Int) => println(x*2))
    (1 to 100).filter(_%2==0).foreach(println _)

    val addition = (_: Int)+(_: Int)
    val carre4 = addition(2,2)
    println(carre4)

    def somme (a: Int, b:Int, c:Int)= a+b+c
    println(somme(1,1,1))

    // function value
    val a = somme _
    println(a(10,10,10))

  }
}
