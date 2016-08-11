/**
  * Created by Samy on 09/08/2016.
  */
object Chapitre12 {



  // un trait n'a pas de parametre (pour le constructeur)
  trait Philosophical{
    def philosophize = println("That's my pihlo")
  }

  trait HasLeg {
    def legging = println("I've legs for walking dead")
  }

  class Animal {
    override def toString: String = "Animals can think"
  }

  class Bouddhiste extends Philosophical{
    override def toString: String = "Zen attitude"
  }

  class Frog extends Animal with Philosophical with HasLeg{
    override def toString: String = "Croa croa i'm a philosopher animal with legs"
    override def legging: Unit = println("Croaaaaa I've two legs to jump")
  }


  // rectangles et points
  class Point(val x: Int, val y: Int)

  trait Rectangular{
    def topLeft: Point
    def bottomRight: Point

    def left = topLeft.x
    def right = bottomRight.x
    def width = right-left
  }

  abstract class Component extends Rectangular{

  }


  class Rectangle(val topLeft: Point, val bottomRight: Point) extends Rectangular{

  }

  // le trait Ordered
  class Rationnal(n: Int, d: Int) extends Ordered[Rationnal]{

    // declenche une exception
    require(d != 0)

    // attributs
    val n1 = n / gcd(n.abs, d.abs)
    val d1 = d / gcd(n.abs, d.abs)

    // constructeur auxiliaire
    def this(n: Int) = this(n, 1)

    def * (x: Rationnal): Rationnal = new Rationnal(this.n1*x.n1, this.d1*x.d1)

    def * (n: Int): Rationnal = new Rationnal(this.n1*n, this.d1)

    private def gcd(a:Int, b:Int): Int ={
      if(b == 0) a else gcd(b, a%b)
    }

    override def toString: String = n+"/"+d

    def add(x: Rationnal): Rationnal = new Rationnal(n*x.d1+d*x.n1, d*x.d1)

    def lessThan(x: Rationnal) = this.n1*x.d1 < this.d1*x.n1

    def max(x: Rationnal) = if(lessThan(x)) x else this

    // la methode doit retourner un Integer, 0 si idem, positif si le receiver est plus grand que le parametre, negatif sinon. Ordered n'implemente pas equals
    def compare(that: Rationnal): Int = this.n1*that.d1 - this.d1*that.n1

  }

  abstract class IntQueue{
    def get(): Int
    def put(x: Int)
  }

  import scala.collection.mutable.ArrayBuffer
  class BasicIntQueue extends IntQueue{
    val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) = buf+=x

    override def toString: String = 
  }

  def main(args: Array[String]): Unit = {
//    println("La marque de Bouddha: ")
//    val bouddha = new Bouddhiste
//    bouddha.philosophize
//    println(bouddha)
//
//    println("La premiere marque de Bouddha: ")
//    val philo_bouddha: Philosophical = bouddha // un trait definit aussi un type: on aurait pu l'instancier avec n'importe quel objet qui mixe Philosophical
//    philo_bouddha.philosophize
//    println(philo_bouddha)
//
//    println("La marque de bouddha froggée: ")
//    val frogPhilosopher: Philosophical = new Frog
//    println(frogPhilosopher)
//    frogPhilosopher.philosophize
//    println("La marque des jambes: ")
//    val frog_leg: HasLeg = new Frog
//    frog_leg.legging
//    println(frog_leg)
//
//    val rect = new Rectangle(new Point(0,1), new Point(1,0))
//    println(rect.left, rect.right, rect.width)

//    val half = new Rationnal(1,2)
//    val third = new Rationnal(1,3)
//    println(half > third)
//    println(half == third)

    val queue = new BasicIntQueue
    queue.put(10)
    queue.put(20)
    queue.put(40)
    println(queue)
    val i = queue.get()
    println(i, queue)


  }
}
