/**
  * Created by Samy on 04/07/2016.
  */
object Chapitre2 {
  def main(args: Array[String]) {

    val msg = "c'est bientôt la fin du ramadan"
    val msg2: String = "il faut tenir bon"
    val msg3: java.lang.String = "c'est l'heure d'aller manger"

    def max(x: Int, y: Int): Int = if (x > y) x else y

    println(max(1, 2))

    println("Programmation impérative")
    var i = 0
    while (i < 10){
      println(i)
      i += 1
    }
    println("-----------------------------------------")
    println("Programmation fonctionnelle")
    var liste = (0 to 9)
    liste.foreach(println)
    liste.foreach(arg => println(arg))

    msg.foreach(println)

    for(x <- liste) println(x)


  }
}
