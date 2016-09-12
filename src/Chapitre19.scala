/**
  * Created by Samy on 12/09/2016.
  */
object Chapitre19 {

  import scala.collection.immutable.Queue
  val q = Queue(1,2,3)
  val q1 = q enqueue 4

  def main(args: Array[String]): Unit = {
    println(q, q1)
  }
}
