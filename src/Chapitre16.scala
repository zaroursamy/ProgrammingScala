/**
  * Created by Samy on 03/09/2016.
  */
object Chapitre16 {

  /**
    * les listes sont immutables et ont une sutrcture recursive (differences avec les array)
    * listes sont covariantes: S de type T alors List[S] de type List[T]
    */

  val l1: List[Int] = List(1,2,3)
  val l2: List[Nothing] = List() // sous type de List[T] pour tout T
  val l22: List[String] = List() // car Nothing est sous type de tout
  val l3: List[List[Int]] = List(List(1,0,0), List(0,1,0), List(0,0,1))

  val l11 = 1::(2::(3::Nil))
  val l33 = (1::(0::(0::Nil)))::
    (0::(1::(0::Nil)))::
    (0::(0::(1::Nil)))::Nil

  // trie les deux premiers  elements
  def insert(x: Int, xs: List[Int]): List[Int] = {
    if(xs.isEmpty || x <= xs.head) x::xs
    else xs.head::insert(x, xs.tail)
  }

  // tri par ordre croissant
  def isort(xs: List[Int]): List[Int] = {
    if(xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  }

  /*
  avec le pattern matching
   */
  def insertP(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y::ys => if(x <= y) x::xs else y::insertP(x, ys)
  }

  def isortP(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case x::xs1 => insert(x, isortP(xs1))
  }

  def rev[T](xs: List[T]): List[T] = xs match {
    case Nil => xs
    case x::xs1 => rev(xs1):::List(x)
  }

  def main(args: Array[String]): Unit = {
    println(l1 == l11, l2 == l22, l3 == l33)

    println(l1.head, l3.head)

    println("insert")
    println(insert(99, List()))
    println(insert(99, List(100,101)))
    println(insert(99, List(100,98)))
    println(insert(99, List(98,100,101)))
    println(insert(99,List(98,101,100)))
    println(insert(99, List(98,92,90,93)))

    println("isort")
    println(isort(List(1,2,3)))
    println(isort(List(3,2,1)))
    println(isort(List(3,2,4,1,5,8)))

    // list pattern
    val List(a,b,c) = List(1,2,3)
    println(a,b,c)
    val aa::bb::_ = List(1,2,3) // a::b est traité comme ::(a,b). scala.:: est une classe. la methode :: produit une instance de la classe ::
    val aaa::bbb::ccc = List(1,2,3,4,5,6,7)
    println(aa,bb)
    println(aaa,bbb,ccc)

    // concatenation de listes
    println(List(1,2):::List(3,4,5):::List(10)) // equivalent a x:::(y:::z)

    // operations sur les listes. tout comme tail et head , exception sur Nil.
    // ont besoin de parcourir toute la liste
    println("last ", List(1,2,3,4).last) // le dernier
    println("init ", List(1,2,3,4).init) // tout sauf le dernier

    println("reverse ", List(1,2,3,4).reverse) // ne modifie pas la liste d'origine mais cree une nouvelle instance
    println("take: ", List(1,2,3,4,5) take 2) // prend les 2 premiers elements
    println("drop: ", List(1,2,3,4) drop 3) // tous sauf les 3 premiers
    println("splitat: ", List(1,2,3,4,5) splitAt 3) // split les trois premiers et le reste en deux listes
    println("apply: ", List(1,2,3,4) apply 0, List(1,2,3,4)(0), List(1,2,3,4) apply 2 equals (List(1,2,3,4) drop 2).head) // retourne le 0eme element. le temps est proportionnel a l'indice....
    println("indices: ", List(1,2,3).indices) // retourne les indices
    // flatten ne sapplique qu'aux listes dont les elements sont des listes, sinon erreur ex List(1,2).flatten
    println("flatten: ", List(List("a","b","c"), List("x","y"), List("z")).flatten, List("hello","world").map(_.toCharArray),List("hello","world").map(_.toCharArray).flatten)

    val abcde = List('a','b','c','d','e')
    val l123456789 = 1 to 9
    println(abcde zip l123456789)

    println(abcde zipWithIndex)
    println(abcde zip l123456789 unzip)
    println(List(('a',1), ('b',2)).unzip)// unzip: prend des liste de tuples de taille 2 et les dezip
    println(abcde toString)

    println("mkString: ", abcde mkString, abcde.mkString("!", "-----", "?"))


  }

}
