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
    numNames.foreach(println); numNames2.foreach(println)

    // les arrays sont mutables mais pas les listes
    val uneListe = List(1,2,3)

    // concaténation de liste
    val unDeux = List(1,2)
    val troisQuatre = List(3,4)
    val unDeuxTroisQuatre = unDeux ::: troisQuatre

    // cons ( :: operator). List(2,3).::(1) (invoqué sur l'opérande de droite)
    val unDeuxTrois = 1 :: List(2,3)
    // Nil = List()
    val uneListe2 = "premierElement"::"deuxiemeElement"::"troisiemeElement"::Nil
    uneListe2.foreach(println)
    // supprimer le premier element
    uneListe2.drop(1).foreach(println)
    println("-------")
    // supprimer les deux derniers
    uneListe2.dropRight(2).foreach(println(_))
    // existence d'un element
    println(uneListe2.exists(_=="premierElement"))
    // renvoie true si tous les elements satisfont le prédicat
    println(uneListe2.forall(_.endsWith("t")))
    println(List("un","deux","trois").forall(_.endsWith("x")))
    println(uneListe2.mkString("///////"))

    println("__________________________tuples__________________________")
    // un tuple est comme une liste (immutable) mais peut contenir differents types d'elements
    // ce typle est de type Tuple(Int, String, Int, String)
    // pâs de méthode apply car apply retourne toujours le meme type
    val unTuple = (1, "un", 2, "deux")
    println(unTuple._1, unTuple._2)

    println("__________________________ set__________________________")

    // java: implemente interface. Scala: extend trait
    var jetSet = Set("Boeing","Airbus")
    jetSet += "Lear"
    println(jetSet.contains("Lear"))//true

    import scala.collection.mutable.Set
    val movieSet = Set("Hitch","Heat")
    // equivalent a movieSet.+=("Scream")
    movieSet += "Scream"
    println(movieSet) // scream est ajouté

    import scala.collection.immutable.HashSet
    val hashSet = HashSet("tomtate","poivron")
    println(hashSet +"oignon")

    // Map: immutable & mutable
    import scala.collection.mutable.Map
    val instructions = Map[Int, String]()
    // equivalent a (1).->("prendre des glucides")
    instructions += (1 -> "prendre des glucides")
    instructions += (2 -> "prendre des proteines")
    instructions += (3 -> "aller a la salle de sport")
    println(instructions)
    println(instructions(3))

    // immutable est la map par defaut; pâs besoin d'import
    val instructions2 = Map(1->"entrainement", 2->"repos", 3->"diete")
    instructions2 += (4 -> "impôssible")
    println(instructions2)
    // ne comprend pas  : censé etre immutable mais on a pu rajouter le 4 ?

    // fonction avec et sans effet de bord
    def avecEffet(args: Array[String]): Unit = args.foreach(println)
    def sansEffet(args: Array[String]): String = args.mkString("\n")
    // -------------------------------------------

    // imports
    import scala.io.Source

    val lines = Source.fromFile(args(0)).getLines().toList
    def widthOfLength(s: String) = s.length.toString.length
    var maxWidth = 0
    for(line <- lines) maxWidth = maxWidth.max(widthOfLength(line))
    println(maxWidth)




  }


}
