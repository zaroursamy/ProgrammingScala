/**
  * Created by Samy on 09/09/2016.
  */
object Chapitre17 {

  // initialisation d'une liste
  val liste = List("r","g","b")

  // d'un array
  val tableau = new Array[Int](5)
  val tableau2 = Array(5,4,3,2,1)
  tableau(0) = tableau2(0)

  // ListBuffer: objet mutable
  import scala.collection.mutable.ListBuffer
  val buf = new ListBuffer[Int]
  buf +=1
  buf +=2

  def main(args: Array[String]): Unit = {

    tableau.foreach(println)
    println(buf)

  }
}
