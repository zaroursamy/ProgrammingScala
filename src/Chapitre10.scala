/**
  * Created by Samy.Zarour on 29/07/2016.
  */
object Chapitre10 {

  // abstract class: signifie que la classe peut avoir des méthodes non implémentées.
  // on ne peut pas l'instantcier
  abstract class Element{

    // pas de liste de parametre ! ( ie def height():Int ) : parameterless method. il faut qu'il n'y ai pas de param et qu'elle ne change pas d'etat des mutables
    def contents: Array[String]
    def height: Int = contents.length
    def width: Int = if(height != 0) contents(0).length else 0

    // mais on peut les mettre sous forme de var: plus rapide , car pré calculé à l'initialisation de la classe,
    // au lieu detre calcule a chaque fois que la classe est appelée. MAIS les fields prennent plus de place en mémoire

    //val height = contents.length
    //val width = if(height != 0) contents(0).length else 0

  }//end abstract class Element

  // hérite des membres non privés, et fait du type ArrayElement un sous type de Element
//  class ArrayElement(conts: Array[String]) extends Element{
//    def contents: Array[String] = conts // implements/override
//
//    // on peut egalement definir une val du meme nom. on peut overrider une methode parameterless avec une val
//    //val contents = conts
//  }

  // on peut réduire la classe du dessus et simplifier le code ainsi:
  class ArrayElement(
                    val contents: Array[String] // definit un parametre et un attribut (field)
                    ) extends Element
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

  class LineElement(s: String) extends ArrayElement(Array(s)) { // superclass constructor
    override def width = s.length
    override def height  = 1
  }

  class UniformElement(
                        ch: Char,
                        override val width: Int,
                        override val height: Int
                      ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  abstract class Un{
    def demo(){println("Un")}
  }

  class Deux extends Un{
    override def demo(){println("Un+Un")}
  }

  class Uno extends Un

  def main(args: Array[String]): Unit = {
    val a = new ArrayElement(Array("salut", "ca va ?"))
    println("a: "+ a+" "+a.width)

    // toujours du type ArrayElement: polymorphisme
    val e: Element = new ArrayElement(Array("Element d\'arrayelement"))
    println("e: "+ e+" "+e.width)

    val e1: Element = new ArrayElement(Array("hello", "world"))
    val ae: ArrayElement = new LineElement("hello")
    val e2: Element = ae
    val e3: Element = new UniformElement('x', 2, 3)
    println(e1, ae, e2, e3)
    println()

    def invocationDemo(e: Un): Unit ={
      e.demo()
    }

    // invoque l'implementation de Deux
    invocationDemo(new Deux)
    // appelle celle de Un car Uno n'override pas la methode demo
    invocationDemo(new Uno)

  }
}
