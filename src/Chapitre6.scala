/**
  * Created by Samy on 16/07/2016.
  */
object Chapitre6 {

  // seul le constructeur primaire peut invoquer un constructeur d'une superclasse
  class Rationnal(n: Int, d: Int){
    // declenche une exception
    require(d != 0)


    // attributs
    val n1 = n / gcd(n.abs, d.abs)
    val d1 = d / gcd(n.abs, d.abs)


    // constructeur auxiliaire
    def this(n: Int) = this(n, 1)

    private def gcd(a:Int, b:Int): Int ={
      if(b == 0) a else gcd(b, a%b)
    }

    override def toString: String = n+"/"+d


    def add(x: Rationnal): Rationnal = new Rationnal(n*x.d1+d*x.n1, d*x.d1)

    def lessThan(x: Rationnal) = this.n1*x.d1 < this.d1*x.n1

    def max(x: Rationnal) = if(lessThan(x)) x else this
  }

  def main(args: Array[String]): Unit = {
    val r = new Rationnal(1,2)
    var r2 = r add new Rationnal(1,3)
    println(r2.n1, r2.d1)
    println(r2)

    println(r.lessThan(new Rationnal(3,4)))
    println(r.lessThan(new Rationnal(1,3)))
    println(r.max(r2))
  }


}
