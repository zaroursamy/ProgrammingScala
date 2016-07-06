/**
  * Created by Samy on 06/07/2016.
  */
object Chapitre3 {
  def main(args: Array[String]) {
    val big = BigInt(1234567890)
    val big2 = big*big
    println(big2)

    val greetStrings = new Array[String](3)
    greetStrings(0) = "hey"
    greetStrings(1) = " "
    greetStrings(2) = "wtf\n"

    greetStrings.foreach(print)
    for(i <- greetStrings) print(i)
    for(i <- 0 to greetStrings.length-1) print(greetStrings(i))
  }
}
