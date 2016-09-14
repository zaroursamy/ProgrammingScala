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
// asymptotiquement, les queues fonctionnelles sont aussi efficaces que les queues mutables
// class private: cache le primary constructeur (accessible dans la classe et dans un compagnon)
class Queue2[T] (private val leading: List[T], private val trailing: List[T]){

  // O(n) si leading est empty, sinon O(1)
  private def mirror = if(leading.isEmpty) new Queue2(trailing.reverse, Nil) else this

  def head = mirror.leading.head

  // si leading est vide, le tail inverse la fin
  def tail = {
    val q = mirror
    new Queue2(q.leading.tail, q.trailing)
  }

  // le x n'est pas vraiment à la fin ...
  def enqueue(x: T) = new Queue2(leading, x::trailing) // O(1)

  // le x est a la fin mais on perd la complexité constante
  def enqueue2(x: T) = new Queue2(leading, trailing:::List[T](x)) // O(n) ...

  override def toString: String = leading.mkString(" ")+" | "+trailing.mkString(" ")

}

class Queue3[T] private (private val leading: List[T], private val trailing: List[T]){

  // O(n) si leading est empty, sinon O(1)
  private def mirror = if(leading.isEmpty) new Queue3(trailing.reverse, Nil) else this

  def head = mirror.leading.head

  // si leading est vide, le tail inverse la fin
  def tail = {
    val q = mirror
    new Queue3(q.leading.tail, q.trailing)
  }

  // le x n'est pas vraiment à la fin ...
  def enqueue(x: T) = new Queue2(leading, x::trailing) // O(1)

  // le x est a la fin mais on perd la complexité constante
  def enqueue2(x: T) = new Queue2(leading, trailing:::List[T](x)) // O(n) ...

  override def toString: String = leading.mkString(" ")+" | "+trailing.mkString(" ")

  // T*: notation pour parametres répétés
  def this(elems: T*) = this(elems.toList, Nil)
}

// présente au public l'interface
trait Queue4[T]{
  def head: T
  def tail: List[T]
  def enqueue(x: T): Queue4[T]
}

// on cache l'implémentation meme des méthodes, au lieu de cacher seulement le constructeur
object Queue4{
  def apply[T](x: T*): Queue4[T] = new Queue4Impl[T](x.toList, Nil)

  private class Queue4Impl[T](private val leading: List[T], private val trailing: List[T]) extends Queue4[T]{
    def mirror = if(leading.isEmpty) new Queue4Impl(trailing.reverse, Nil) else this

    def head: T = mirror.leading.head

    def tail: Queue4Impl[T] = {
      val q = mirror
      new Queue4Impl(q.leading.tail, q.trailing)
    }

    def enqueue(x: T) = new Queue4Impl(leading, x::trailing)
  }
}


object Chapitre19 {

  import scala.collection.immutable.Queue
  val q = Queue(1,2,3)
  val q1 = q enqueue 4 // si on avait importer mutable QUeue, ca aurait changé q
  val saq = new SlowAppendQueue[Int](List(10,20,55))

  val q2 = new Queue2[Int](Nil, List(1,2,3))
  val q3 = new Queue2[Int](List(1,2,3), List(7,8,9))

  // new Queue3(List(1,2), List(3)) impossible car private

  // la méthode apply du compagnon  est appelée (object compagnon dans le meme fichier que la classe)
  val queue3 = new Queue3(List(4,5))

  def main(args: Array[String]): Unit = {
    println(q, q1)
    println(saq)
    val saq1 = saq enqueue 33
    println("saq: ",saq, "saq1: ",saq1)
    println(q2.tail)
    val q3bis = q3 enqueue 22
    val q3bisbis = q3 enqueue2 22
    println("q3bis: ", q3bis, "      ", "q3: ", q3, "       ", "q3bisbis: ", q3bisbis)
  }
}
