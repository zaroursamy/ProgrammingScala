/**
  * Created by Samy on 16/07/2016.
  */
object Chapitre6 {

  class Rationnal(n: Int, d: Int){
    require(d != 0)// declence une exception

    val n1 = n
    val d1 = d

    override def toString: String = n+"/"+d

    def add(x: Rationnal): Rationnal = new Rationnal(n*x.d1+d*x.n1, d*x.d1)
  }

  def main(args: Array[String]): Unit = {
    val r = new Rationnal(1,2)
    var r2 = r add new Rationnal(1,2)
    println(r2.n1, r2.d1)
    println(r2)
  }


}
