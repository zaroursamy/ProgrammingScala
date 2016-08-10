/**
  * Created by Samy on 09/08/2016.
  */
object Chapitre12 {



  trait Philosophical{
    def philosophize = println("That's my pihlo")
  }

  trait HasLeg {
    def legging = println("I've legs for walking dead")
  }

  class Animal

  class Bouddhiste extends Philosophical{
    override def toString: String = "Zen attitude"
  }

  class Frog extends Animal with Philosophical with HasLeg{
    override def toString: String = "Croa croa i'm a philosopher animal with legs"
    override def legging: String = "Croaaaaa I've two legs to jump"
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


  }
}
