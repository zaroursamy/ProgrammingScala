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

  // ListBuffer: objet mutable. bien contre le stack overflow
  import scala.collection.mutable.ListBuffer
  val buf = new ListBuffer[Int]
  buf +=1
  buf +=2
  10 +=: buf // ajoute au debut

  // ArrayBuffer
  import scala.collection.mutable.ArrayBuffer
  val aBuf = new ArrayBuffer[String]() // pas besoin de spécifier la taille
  aBuf += "salut"
  "hey" +=: aBuf

  def hasMajuscule(x: String): Boolean = x.exists(_.isUpper)

  def main(args: Array[String]): Unit = {

    tableau.foreach(a => print(a +" "))
    println(buf(1))
    println(aBuf, aBuf(0))
    println(hasMajuscule("Salut")) // conversion implicite de string vers stringops car string n'a pas de methode exists. traite le string en sequence de char

  }
}
