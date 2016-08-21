import bobsdelights.Fruit
import bobsdelights.Fruits.Banana


/**
  * Created by Samy on 17/08/2016.
  */


/**
  * exemple 2
  */
package bobsrockets {
  package navigation {
    class Navigator {
      // No need to say bobsrockets.navigation.StarMap
      val map = new StarMap // une classe peut etre accessible dans le meme package: on est dans bobsrockets et navigation
    }
    class StarMap
  }//end navigation

  class Ship {
    // No need to say bobsrockets.navigation.Navigator
    val nav = new navigation.Navigator // on est dans bobsrockets mais plus dans navigation
  }
  package fleets {
    class Fleet {
      // No need to say bobsrockets.Ship
      def addShip() { new Ship } // on est deja dans bobsrockets, et Ship est dans bobsrockets
    }
  }
}


/**
  * exemple 3
 */
package launch {
  class Booster3
}
// In file bobsrockets.scala
package bobsrockets {
  package navigation {
    package launch {
      class Booster1
    }// end bobsrockets.navigation.launch
    class MissionControl {// on est dans bobsrockets.navigation
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.launch.Booster3// root car au top niveau: _root_ est le package au dessus de tous les packages qu'on puisse ecrire
    }
  }// end navigation
  package launch {
    class Booster2
  }// end bobsrockets.launch
}// end bobsrockets


package bobsdelights{

  abstract class Fruit(
    val name: String,
    val color: String
  )

  object Fruits{
    object Apple extends Fruit("apple","green")
    object Strawberry extends Fruit("strawberry","red")
    object Banana extends Fruit("banana","yellow")

    val menu = List(Apple, Strawberry, Banana)
  }
}

package Notebook{
  object Apple
}

import java.util.regex
class AstarB{
  val pat = regex.Pattern.compile("a*b")
}

class Outer{
  class Inner{
    private def f(s: String) = s // inaccessible en dehors de Inner
    class OuterInner{
      val f1: String = f("oi")
    }
  }
  //val g = (new Inner).f("g")
}

package Comics{

  class SuperHero{
    protected def f() = println("protecteur") // accessible seulement par les classe héritieres
  }

  class Hero extends SuperHero{
    val g = f()
  }

  class Mechant{
    //val m = (new SuperHero).f()
    // en java ca aurait marché car dans le meme package
  }
}

package bobsrockets{
  package navigation {
    private[bobsrockets] class Navigatorr { // la classe est visible pour tous les objets/classes contenus dans bobsrockets. tout code en dehors de bobsrockets ne peut pas y accéder
      protected[navigation] def useStarChart() {}
      class LegOfJourney {
        private[Navigatorr] val distance = 100
      }
      // garantie que speed n'est pas visible dans tout autre objet de la meme classe (Navigator)
      private[this] var speed = 200 // seulement au sein de Navigator, ie l'objet qui contient la définition de speed




      val n = new Navigatorr
      //val n1 = n.speed
      // on neut peut pas faire n1.speed car private[this] => seulement accessible par this

      val n2 = this.speed
    }
  }
  package launch {
    import navigation._
    object Vehicle {
      private[launch] val guide = new Navigatorr // Vehicle est dans bobsrockets , et Navigator a un qualifier bobsrockets, donc accessible
    }
  }
}

/**
  * une classe et son compagnon ont accès a leurs membres: un objet compagnon peut acceder a des membres privates de la classe et vice versa
  * un singleton n'a pas de sous classes
  */
class Rocket{
  import Rocket.fuel
  private def canGoHomeAgain = fuel > 20 // fuel est privé dans le compagnon mais accessible dans la classe !
}

object Rocket{

  private def fuel = 10

  def chooseStrategy(r: Rocket) = {
    if(fuel > 20) goHome() else findStation()
  }

  def goHome() = {}
  def findStation() = {}

}

/**
  * les packages object sont compilés dans des fichiers .class ds le repertoire du package
  */

// on met les definitions dans le package objet. ces definitions seront membres du package lui meme
package object bobsdelights {
  def showFruit(fruit: Fruit) {
    import fruit._
    println(name +"s are "+ color)
  }
}

// dans un autre fichier que le package object
package printmenu{
  import bobsdelights.Fruits
  import bobsdelights.showFruit
  object PrintMenu {
    def main(args: Array[String]) {
      for (fruit <- Fruits.menu) {
        showFruit(fruit) // la méthode est dans le package objet
      }
    }
  }
}


object Chapitre13 {


  def main(args: Array[String]): Unit = {

    def showFruit1(fruit: Fruit): Unit ={
      println("a "+fruit.name+" is "+fruit.color)
    }
    def showFruit2(fruit: Fruit): Unit ={
      import fruit._
      println("a "+name+" is "+color)
    }

    val apple: Fruit = new Fruit("apple","green") {}
    showFruit1(apple)

    import bobsdelights.Fruits.{Banana}
    val banane = Banana

    import bobsdelights.Fruits.{Apple=>McIntosh}
    val pomme = McIntosh

    // on peut accéder à Date via D
    import java.sql.{Date => D}
    println(D.valueOf("2000-11-01"))

    // on accede à sql via S
    import java.{sql => S}
    println(S.Date.valueOf("2000-11-01"))
  }

  // on accede a tous les membres, mais rename Apple
  import bobsdelights.Fruits.{Apple => MacIntosh, _}
  val mac = MacIntosh
  val banane = Banana

  // tout sauf Apple
  import bobsdelights.Fruits.{Apple => _, _}

  import bobsrockets.navigation.Navigator


}
