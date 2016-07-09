import java.rmi.activation.UnknownObjectException

/**
  * Created by Samy on 06/07/2016.
  */
object Chapitre3 {
  def main(args: Array[String]) {
    val big = BigInt(1234567890)
    val big2 = big*big
    println(big2)

    // on ne peut pas reassigner greetStrings, mais on peut modifier ses elements .
    val greetStrings = new Array[String](3)
    greetStrings(0) = "hey"
    greetStrings(1) = " "
    greetStrings(2) = "wtf\n"

    greetStrings.foreach(print)
    for(i <- greetStrings) print(i)
    for(i <- 0 to greetStrings.length-1) print(greetStrings(i))

    // les méthodes de classe sans parentheses pour un parametre
    Console println 10
    val unString = "un string"
    println(unString.charAt(1))
    println(unString charAt 1)

    // scala comprend que c'est un array de string. chaque fois qu'il y a une (), une methode apply() est appelée
    val numNames = Array("zero","one","two")
    val numNames2 = Array.apply("zero","one","two")

    // les arrays sont mutables mais pas les listes
    val uneListe = List(1,2,3)

    // concaténation de liste
    val unDeux = List(1,2)
    val troisQuatre = List(3,4)
    val unDeuxTroisQuatre = unDeux ::: troisQuatre
    // cons ( :: operator)
    val unDeuxTrois = 1 :: List(2,3)



  }


}
