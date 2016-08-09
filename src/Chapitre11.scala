/**
  * Created by Samy on 07/08/2016.
  */
object Chapitre11 {

  def main(args: Array[String]): Unit = {

    def isEqual(x: Int, y: Int) = x==y
    def isEqual2(x: Any, y: Any) = x==y

    println(isEqual(10,10), isEqual2(10,10))

    val x = "abcd" substring 2
    val y = "abcd" substring 2
    println(x==y) // en java, cela n'aurait pas été true car l'egalite se fait par reference: il faudrait faire equals

    // en scala, egalite de reference se fait avec eq
    println(x eq y, x ne y)
  }
}
