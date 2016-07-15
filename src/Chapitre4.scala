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
    def add(byte: Byte): Unit ={
      // byte = 1 ne compile pas car byte est une val
      sum += byte
    }

    def checksum(): Int ={
      return ~(sum & 0xFF) + 1
    }
  }


  def main(args: Array[String]) {
    val csa = new ChecksumAccumulator
    val acc = new ChecksumAccumulator
    acc.sum2 = 3
    // acc.sum = 0 ne compile pas ! car sum2 est private

    println(acc.checksum())
  }
 


}
