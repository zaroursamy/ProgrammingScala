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
  }

  // ce trait ne pourra etre mix in que par une classe qui extends IntQueue également
  // abstract override car pour dire au compiler qu'on empile les modif sur la methode. le trait doit étendre une classe qui définit la methode
  trait Doubling extends IntQueue{
    abstract override def put(x: Int) = super.put(2*x)
  }

  class MyQueue extends BasicIntQueue with Doubling

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x + 1) }
  }

  trait Filtering extends IntQueue {
    abstract override def put(x: Int) {
      if (x >= 0) super.put(x)
    }
  }

  def main(args: Array[String]): Unit = {
    println("La marque de Bouddha: ")
    val bouddha = new Bouddhiste
    bouddha.philosophize
    println(bouddha)

    println("La premiere marque de Bouddha: ")
    val philo_bouddha: Philosophical = bouddha // un trait definit aussi un type: on aurait pu l'instancier avec n'importe quel objet qui mixe Philosophical
    philo_bouddha.philosophize
    println(philo_bouddha)

    println("La marque de bouddha froggée: ")
    val frogPhilosopher: Philosophical = new Frog
    println(frogPhilosopher)
    frogPhilosopher.philosophize
    println("La marque des jambes: ")
    val frog_leg: HasLeg = new Frog
    frog_leg.legging
    println(frog_leg)

    val rect = new Rectangle(new Point(0,1), new Point(1,0))
    println(rect.left, rect.right, rect.width)

    val half = new Rationnal(1,2)
    val third = new Rationnal(1,3)
    println(half > third)
    println(half == third)

    val queue = new BasicIntQueue
    queue.put(10)
    queue.put(20)
    queue.put(40)
    println(queue)
    val i = queue.get()
    println(i, queue)

    val myq = new MyQueue

    println("avec le trait Doubling: ")
    myq.put(10)
    myq.put(20)
    myq.put(40)
    println(myq)
    val j = myq.get()
    println(j, myq)

    val myq2 = new BasicIntQueue with Doubling
    myq2.put(10)
    myq2.put(20)
    myq2.put(40)
    val k = myq2.get()
    println(k, myq2)

    // la methode du trait le plus a droite est la premiere appelee ; Ici, d'abord Filtering, puis Incrementing
    val myq3 = (new BasicIntQueue with Incrementing with Filtering)
    myq3.put(-1) // filtre: -1<0 donc rien ne se passe
    myq3.put(0) // filtre: 0>=0 donc incremente: ajoute 1
    myq3.put(1) // filtre: 1>=0 donc incremente: ajoute 2
    println(myq3.get(), myq3.get())

    println("inversement")
    val myq4 = (new BasicIntQueue with Filtering with Incrementing)
    myq4.put(-1) // incremente: 0, filtre: ajoute 0
    myq4.put(0) // incremente: 1, filtre: 1>0 donc ajoute 1
    myq4.put(1) // incremente: 2, filtre: 2>0 donc ajoute 2
    println(myq4.get(), myq4.get(), myq4.get())

    println("freestyle")
    val mq = (new BasicIntQueue with Incrementing with Filtering)
    mq.put(-4)
    mq.put(0)
    mq.put(-10)
    mq.put(-8)
    mq.put(7)

    println(mq.get(), mq.get())
    println("----- with incrementing with doubling")
    val mq2 = (new BasicIntQueue with Incrementing with Doubling)
    mq2.put(1) // ajoute 3
    mq2.put(10) // ajoute 21
    println(mq2.get(), mq2.get())

  }
}
