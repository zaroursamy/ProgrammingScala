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

import java.util.regex
class AstarB{
  val pat = regex.Pattern.compile("a*b")
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

    import java.sql.{Date => D}
    println(D.valueOf("2000-11-01"))

    import java.{sql => S}
    println(S.Date.valueOf("2000-11-01"))
  }
}
