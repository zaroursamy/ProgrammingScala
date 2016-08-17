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

object Chapitre13 {


  def main(args: Array[String]): Unit = {

  }
}
