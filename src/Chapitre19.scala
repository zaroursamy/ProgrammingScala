/**
  * Created by Samy on 12/09/2016.
  */

// Queue purement fonctionnel (comme les listes, c'est fully persistent)
// de ces deux fonctions suivantes, on peut pas avoir toutes les methodes en O(1)
class SlowAppendQueue[T](elems: List[T]){
  def head = elems.head // O(1)
  def tail = new SlowAppendQueue(elems.tail) // O(1)
  def enqueue(x: T) = new SlowAppendQueue(elems ::: List(x)) // O(n)

  override def toString: String = elems.mkString(" ")
}

class SlowHeadQueue[T](elemsReversed: List[T]){
  def head = elemsReversed.last // O(n)
  def tail = new SlowHeadQueue(elemsReversed.init) // O(n)
  def enqueue(x: T) = new SlowHeadQueue(x::elemsReversed) // O(1)

  override def toString: String = elemsReversed.mkString(" ")
}

// combinaison des deux classes grâce a deux listes
class Queue2[T](private val leading: List[T], private val trailing: List[T]){
  private def mirror = if(leading.isEmpty) new Queue2(trailing.reverse, Nil) else this

  def head = mirror.leading.head

  def tail = {
    val q = mirror
    new Queue2(q.leading.tail, q.trailing)
  }

  override def toString: String = leading.mkString(" ")+" | "+trailing.mkString(" ")
}



object Chapitre19 {

  import scala.collection.immutable.Queue
  val q = Queue(1,2,3)
  val q1 = q enqueue 4 // si on avait importer mutable QUeue, ca aurait changé q
  val saq = new SlowAppendQueue[Int](List(10,20,55))

  val q2 = new Queue2[Int](Nil, List(1,2,3))

  def main(args: Array[String]): Unit = {
    println(q, q1)
    println(saq)
    val saq1 = saq enqueue 33
    println("saq: ",saq, "saq1: ",saq1)
    println(q2.tail)
  }
}
