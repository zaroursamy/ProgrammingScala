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
      val map = new StarMap
    }
    class StarMap
  }
  class Ship {
    // No need to say bobsrockets.navigation.Navigator
    val nav = new navigation.Navigator
  }
  package fleets {
    class Fleet {
      // No need to say bobsrockets.Ship
      def addShip() { new Ship }
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
    }
    class MissionControl {
      val booster1 = new launch.Booster1
      val booster2 = new bobsrockets.launch.Booster2
      val booster3 = new _root_.launch.Booster3
    }
  }
  package launch {
    class Booster2
  }
}
object Chapitre13 {


  def main(args: Array[String]): Unit = {

  }
}
