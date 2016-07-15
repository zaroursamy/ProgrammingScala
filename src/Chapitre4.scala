/**
  * Created by Samy on 14/07/2016.
  */
object Chapitre4 {


  class ChecksumAccumulator{
    // sum est un field
    private var sum = 0
    // sum2 est privé : pas accessible en dehors de la classe
    var sum2 = 0

    // les parametres d'une methodes sont des vals, par des vars
    // méthode a effet de bord: on reassigne sum
    // le compiler scala peut transformer tout type en Unit. si la méthod renvoie un string, alors il convertit le string en unit (si la methode est declaree unit)
    def add_ex1(byte: Byte): Unit = sum+= byte
      // byte = 1 ne compile pas car byte est une val

    // autre maniere pour definir une methode a effet de bord:
    // de type Unit egalement
    def add_ex2(byte: Byte){sum+=byte}

    // en mettant le signe = , le type est retourné (Int ici)
    def add(byte: Byte) = sum+=byte

    def checksum(): Int = ~(sum & 0xFF) + 1

    // SINGLETON: objet compagnon (meme nom que la classe). une classe et son compagnon peuvent accéderx aux attributs privés
    // un singleton ne peut pâs prendre de parametres comme les classes (par de new possible)
    import scala.collection.mutable.Map
    object ChecksumAccumulator{

      private val cache = Map[String, Int]()

      def calculate(s: String): Int = {
        if(cache.contains(s)) cache(s)

        // calcul du checksum
        else{
          val acc = new ChecksumAccumulator
          for(c <- s) acc.add(c.toByte)
          val cs = acc.checksum()
          cache += (s -> cs)
          cs
        }
      }
    }

  }

  def main(args: Array[String]) {
    val csa = new ChecksumAccumulator
    val acc = new ChecksumAccumulator
    acc.sum2 = 3
    // acc.sum = 0 ne compile pas ! car sum est private

    println(acc.checksum())
  }
 


}
