/**
  * Created by Samy on 02/07/2016.
  */
object Chapitre1 {

  def main(args: Array[String]) {
    var capital = Map("US"->"Washington", "France"->"Paris")
    capital += ("Japan"->"Tokyo")
    println(capital("France"))

    def factorial(x: BigInt):BigInt =
      if(x==0) 1 else x*factorial(x-1)

    println(factorial(30))

    import java.math.BigInteger

    def factorial2(x: BigInteger): BigInteger=
      if(x== BigInteger.ZERO) BigInteger.ONE else x.multiply(factorial2(x.subtract(BigInteger.ONE)))

    println(factorial2(BigInteger.TEN))

    // immutabilité
    var s = "osdfKLKSQDlkqsdmlkOksmdlfksmldfkooooPppsdfkjlhQ"
    s.replace("o","88") // référenciellement transparente : une unique entrée => une unique sortie
    println(s)

    var a = s.exists(_.isUpper)
    println(a)

  }

  class MyClass(x: Int, s: String)

}
