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

    // function value, instance d'une classe générée automatiquement par le compiler scala depuis sum _
    // partially applied fonction
    val a = somme _
    println(a(10,10,10))
    println(a.apply(10,10,10)) // identique ligne dessus

    val b = somme(10,_:Int,10)
    println(b(10)) // invoque somme(10,10,10)

    val more = 10
    def addOne (x: Int) = x+1 // closed term. ce n'est pas une closure
    def addMore(x: Int) = x+more // open term. more est une free variable. le resultat de cette function value est une closure

    println("______")
    val liste0 = List(-10, -5, 0, 5, 10,1)
    var sum = 0
    liste0.foreach(sum+=_)
    println("somme: "+sum)
  }
}
