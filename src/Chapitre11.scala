/**
  * Created by Samy on 07/08/2016.
  */
object Chapitre11 {
  def isEqual(x: Int, y: Int) = x==y
  def isEqual2(x: Any, y: Any) = x==y

  def error(msg: String): Nothing = throw new RuntimeException(msg)

  // Nothing est la sous classe de toutes classes, on peut donc l'utiliser de maniere flexible (Nothing est un sous type de Int)
  def divide(x: Int, y: Int): Int = if(y != 0) x/y else error("divide by zero")


  def main(args: Array[String]): Unit = {
    println(isEqual(10,10), isEqual2(10,10))

    val x = "abcd" substring 2
    val y = "abcd" substring 2
    println(x==y) // en java, cela n'aurait pas été true car l'egalite se fait par reference: il faudrait faire equals

    // en scala, egalite de reference se fait avec eq
    println(x eq y, x ne y)

    //println(divide(1,0))
  }
}
