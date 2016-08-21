/**
  * Created by Samy on 20/08/2016.
  */

class Chapitre14 {

  // assert(cond) throw une exception si cond est F
  def main(args: Array[String]): Unit = {

    def compare(x: Int, y: Int) = {
      assert(x>0 && y>0)
      x>y
    }

    //compare(-1,0)
    compare(10,10)

    // renvoie le resultat si ce resultat est >= x, sinon throw exception AssertionError
    def exempleEnsuring(x: Int): Int = {
      scala.util.Random.nextInt(10)
    } ensuring(x <= _)

    println(exempleEnsuring(11))
    println(exempleEnsuring(1))


  }
}
