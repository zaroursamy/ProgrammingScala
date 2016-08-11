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

    val rect = new Rectangle(new Point(0,1), new Point(1,0))
    println(rect.left, rect.right, rect.width)




  }
}
