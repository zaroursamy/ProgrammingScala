/**
  * Created by Samy.Zarour on 29/07/2016.
  */
object Chapitre10 {

  // abstract class: signifie que la classe peut avoir des méthodes non implémentées.
  // on ne peut pas l'instantcier

  import Element.elem
  abstract class Element{

    // pas de liste de parametre ! ( ie def height():Int ) : parameterless method. il faut qu'il n'y ai pas de param et qu'elle ne change pas d'etat des mutables
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if(height != 0) contents(0).length else 0

    // entoure de blanc horizontalement
    def widen(w: Int): Element =
      if (w <= width) this
      else {
        val left = elem(' ', (w - width) / 2, height)
        var right = elem(' ', w - width - left.width, height)
        left beside this beside right
      }

    // entoure de blanc verticalement
    def heighten(h: Int): Element =
      if (h <= height) this
      else {
        val top = elem(' ', width, (h - height) / 2)
        var bot = elem(' ', width, h - height - top.height)
        top above this above bot
      }

    def above(that: Element): Element = {
      val this1 = this widen that.width
      val that1 = that widen this.width
      elem(this1.contents ++ that1.contents)
    }

//    def besideImperative(e: Element): Element ={
//      val c = new Array[String](e.contents.length)
//
//      for(i <- 0 until c.length) c(i) = e.contents(i) + this.contents(i)
//
//      elem(c)
//    }

//    def beside1(e: Element): Element = elem(
//      for(
//        (line1, line2) <- this.contents zip e.contents // définit deux vals, une pour chaque element
//      ) yield line1+line2
//    )
    def beside(that: Element): Element = {
      val this1 = this heighten that.height
      val that1 = that heighten this.height
      elem(
        for ((line1, line2) <- this1.contents zip that1.contents)
          yield line1 + line2)
    }

    override def toString: String = contents mkString "\n"

    // mais on peut les mettre sous forme de var: plus rapide , car pré calculé à l'initialisation de la classe,
    // au lieu detre calcule a chaque fois que la classe est appelée. MAIS les fields prennent plus de place en mémoire

    //val height = contents.length
    //val width = if(height != 0) contents(0).length else 0

  }//end abstract class Element

  // grace a la factory method, on peut cacher les sous classes
  object Element{

    def elem(contents: Array[String]): Element = new ArrayElement(contents)

    def elem(c: Char, width: Int, height: Int): Element = new UniformElement(c, width, height)

    def elem(s: String): Element = new LineElement(s)


    private class LineElement(s: String) extends Element{
      val contents: Array[String] = Array(s)
      override def width = s.length
      override def height = 1
    }

    private class UniformElement(ch: Char, override val width: Int, override val height: Int) extends Element {
      private val line = ch.toString * width
      def contents = Array.fill(height)(line) // array à height element remplit de la valeur line
    }

    private class ArrayElement(val contents: Array[String]) extends Element // definit un parametre et un attribut (field)


  }
  // hérite des membres non privés, et fait du type ArrayElement un sous type de Element
//  class ArrayElement(conts: Array[String]) extends Element{
//    def contents: Array[String] = conts // implements/override
//
//    // on peut egalement definir une val du meme nom. on peut overrider une methode parameterless avec une val
//    //val contents = conts
//  }

  // on peut réduire la classe du dessus et simplifier le code ainsi:

  /* equivalent a class ArrayElement(x123: Array[String]) extends Element{
   val contents: Array[String] = x123
   }*/


  class Cat(
           val dangerous: Boolean = false

           )

  class Tiger(
             override val dangerous: Boolean = true,
             private var age: Int

             ) extends Cat

  /*
  class Tiger(p1: Boolean, p2: Int) extends Cat {
    override val dangerous = p1
    private var age = p2
  }
   */

  /*
  class LineElement(s: String) extends ArrayElement(Array(s)) { // superclass constructor
    override def width = s.length
    override def height  = 1
  }*/



  abstract class Un{
    def demo(){println("Un")}
  }

  class Deux
    extends Un{
    override def demo(){println("Un+Un")}
    final def demoDeux(){println("demo2")} // on ne peut pâs overrider un final
  }

  class Dos extends Deux

  class Uno extends Un

  def main(args: Array[String]): Unit = {
//    val a = new ArrayElement(Array("salut", "ca va ?"))
//    println("a: "+ a+" "+a.width)
//
//    // toujours du type ArrayElement: polymorphisme
//    val e: Element = new ArrayElement(Array("Element d\'arrayelement"))
//    println("e: "+ e+" "+e.width)
//
//    val e1: Element = new ArrayElement(Array("hello", "world"))
//    val ae: Element = new LineElement("hello")
//    val e2: Element = ae
//    val e3: Element = new UniformElement('x', 2, 3)
//    println(e1, ae, e2, e3)
//    println()

    def invocationDemo(e: Un): Unit ={
      e.demo()
    }

    // invoque l'implementation de Deux
    invocationDemo(new Deux)
    // appelle celle de Un car Uno n'override pas la methode demo
    invocationDemo(new Uno)

//    println()
//    println("------------------------beside above 10.14---------------------")
//    println("beside :\n",new ArrayElement(Array("hello")) above new ArrayElement(Array("world!")))
//    println("above :\n", new ArrayElement(Array("hello ", "wonderful ", " !!! ")) beside new ArrayElement(Array("my ","world ")))
//

   println("---------")

    println("Element")
    val uniform = elem('p',2,3)
    println(uniform)
    val array = elem(Array("hello","world"))
    println(array)
    val line = elem("que pasa")
    println(line)

    println(uniform.widen(4))
    println("hiden widen----------------")
    println
    println("///////")
    println(uniform.widen(1))
    println("///////")
    println(uniform.widen(20))
    println("******")
    println(uniform.heighten(5))
    println("******")
    println(uniform.heighten(10))
    println(array.heighten(2))
    println("uuuuuuuuuuuuuuuuuuuu")
    println(array.heighten(10))
    println("yyyyyyyyyyyyyyyyyyyyyyy")
    println(line.widen(20))
  }
}
