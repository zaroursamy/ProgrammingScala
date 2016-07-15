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
    def add_ex(byte: Byte): Unit = sum+= byte
      // byte = 1 ne compile pas car byte est une val

    // autre maniere pour definir une methode a effet de bord:
    // de type Unit egalement
    def add_ex2(byte: Byte){sum+=byte}

    // en mettant le signe = , le type est retourné (Int ici)
    def add_ex3(byte: Byte) = sum+=byte

    def checksum(): Int = ~(sum & 0xFF) + 1

  }


  def main(args: Array[String]) {
    val csa = new ChecksumAccumulator
    val acc = new ChecksumAccumulator
    acc.sum2 = 3
    // acc.sum = 0 ne compile pas ! car sum2 est private

    println(acc.checksum())
  }
 


}
