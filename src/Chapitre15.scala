/**
  * Created by Samy on 21/08/2016.
  */

/**
  * case class: ajoute une methode factory (Var("x") au lieu de new...) ET les parametres sont des val
  * supporte le pattern matching
  *
  * MAIS: classe et objets deviennent plus large a cause de l'ajout de methodes
  *
  * une classe selée peut etre heritee seulement par les classes du meme fichier
  * utile pour le pattern matching: force a etre exhaustif
  */


sealed abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {
  import Chapitre10.Element
  import Chapitre10.Element._
  // Contains operators in groups of increasing precedence
  private val opGroups =
  Array(
    Set("|", "||"),
    Set("&", "&&"),
    Set("ˆ"),
    Set("==", "!="),
    Set("<", "<=", ">", ">="),
    Set("+", "-"),
    Set("*", "%")
  )
  // A mapping from operators to their precedence
  private val precedence = {
    val assocs =
      for {
        i <- 0 until opGroups.length
        op <- opGroups(i)
      } yield op -> i
    assocs.toMap
  }
  private val unaryPrecedence = opGroups.length
  private val fractionPrecedence = -1
  private def format(e: Expr, enclPrec: Int): Element =
    e match {
      case Var(name) =>
        elem(name)
      case Number(num) =>
        def stripDot(s: String) = // supprime le .0 a la fin
          if (s endsWith ".0") s.substring(0, s.length - 2)
          else s
        elem(stripDot(num.toString))
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrecedence) // operation + un display entre () et que arg=binop sans /
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrecedence)
        val bot = format(right, fractionPrecedence)
        val line = elem('-', top.width max bot.width, 1)
        val frac = top above line above bot
        if (enclPrec != fractionPrecedence) frac
        else elem(" ") beside frac beside elem(" ")
      case BinOp(op, left, right) => // ici ca ne peut pas etre une division car elle est traité avant
        val opPrec = precedence(op)
        val l = format(left, opPrec)
        val r = format(right, opPrec + 1)
        val oper = l beside elem(" "+ op +" ") beside r
        if (enclPrec <= opPrec) oper
        else elem("(") beside oper beside elem(")")
    }
  def format(e: Expr): Element = format(e, 0)
}

object Chapitre15 {

  def main(args: Array[String]): Unit = {
    val v = Var("v") // au lieu de new Var("v")

    val mutation = BinOp("mutate", Number(2.0), v) // meilleure visibilite sans les new

    val l = mutation.left

    println(v, mutation, l, mutation.right == v, mutation.right == Var("v"))

    // on peut creer une modification de ce qu'on veut grace a copy()
    val mutation2 = mutation.copy(operator = "mutation2")

    def simplifyNeutre(expr: Expr) = expr match {
      case UnOp("-", UnOp("-", e)) => e // match ttes les valeurs de type UnOp ayant pr premier param "-" et pr second param  ttes les valeurs de type UnOp(...).."-" et e
      case BinOp("+", Number(0), e) => e //* tous les arguments du constructeur sont des patterns
      case BinOp("-", Number(0), e) => e
      case BinOp("*", Number(1), e) => e
      case _ => expr
    }


    // wildcard patterns
    def pm1(expr: Expr) = expr match {
      case BinOp(_, _, _) => println("binop")
      case _ => println("autre")
    }
    pm1(mutation)
    def pm2(expr: Expr) = expr match {
      case BinOp(operateur, gauche, droite) => println("binop")
      case _ => println("autre")
    }
    pm2(mutation)

    // constant patterns
    def pm3(x: Any) = x match {
      case true => "vrai"
      case "ok" => "ok !"
      case 5 => "cinq"
      case Nil => "liste vide"
      case _ => "other"
    }
    println(pm3(5), pm3(6), pm3(Nil))

    // variable pattern
    def pm4(x: Any) = x match {
      case 0 => "0"
      case autre => "autre: " + autre
    }

    println(pm4(0), pm4(List(1, 2)))

    import math.{E, Pi}
    println(E match {
      case Pi => "strange, Pi = " + Pi
      case _ => "E not equal Pi"
    })

    val pi = Pi
    println(E match {
      case pi => "strange, Pi = " + pi
      //case _ => "E not equal Pi"
    })

    // unreachable code.
    // ecriture minuscule ! pattern matchable
    //    println(E match{
    //      case pi => "strange, Pi = "+pi
    //      case _ => "E not equal pi"
    //    })

    // traiter un identifer minuscule comme une constante: tout identifier commencant par une minuscule fait reference a une variable matchable
    // si on veut utiliser une variable en minuscule il faut ``
    println(E match {
      case `pi` => "strange math? Pi = " + pi
      case _ => "E not equal pi"
    })

    println(BinOp("+", Var("x"), Number(0)) match {
      case BinOp("+", e, Number(0)) => e
      case autre => autre
    })

    println(BinOp("+", Var("x"), Number(0)) match {
      case BinOp(_, e, Number(0)) => e
      case autre => autre
    })

    // sequence pattern

    // match les liste de taille 3 commencant par 1
    println(List(1, 2, 3) match {
      case List(1, _, _) => "liste debut 1"
      case _ => "autre"
    })

    // match les liste de n'importe quelle taille commencant par 1
    println(List(2, 3, 54, 654, 654, 654, 654, 651, 321, 987, 321) match {
      case List(1, _*) => "liste debut 1"
      case _ => "autre"
    })

    // tuple patterns
    def tupleDemo(t: Any) = t match {
      case (x, y, z) => println("tuple: " + x + "+" + y + "+" + z)
      case _ => println("autre tuple")
    }


    tupleDemo((1, "okokokok", List(true, false)))
    tupleDemo(List(1, 2, 3))
    tupleDemo((1, 2, 3, 4))

    // type patterns
    def taillePattern(x: Any): Int = x match {
      case s: String => s.length
      case l: List[_] => l.length
      case _ => -1
    }

    def taillePattern2(x: Any): Int = x match {
      case s: String => s.length
      case l: List[a] => l.length
      case _ => -1
    }

    println("taille pattern: ") // regarder la diff entre List[_] et List[a] !
    println(taillePattern("hello"), taillePattern(List(1, 2)), taillePattern(List("a", "b")), taillePattern(Map(1 -> 1)))
    println(taillePattern2("hello"), taillePattern2(List(1, 2)), taillePattern2(List("a", "b")), taillePattern2(Map(1 -> 1)))

    println
    println("cast: ")
    // cast
    val s: String = "hey"
    println(s.isInstanceOf[String])

    // erasure
    def isIntList(x: Any) = x match {
      case m: List[Int] => true
      case _ => false
    }

    // on ne peut pas definit le type de la liste (ou de la map), on sait juste que c'est une lsite de qqch
    println(isIntList(List(1, 21)))
    println(isIntList(List("a", "adf"))) // renvoie true egalement !

    // mais pour un array ca marche
    def typeArray(x: Any) = x match {
      case a: Array[String] => "array string"
      case b: Array[Int] => "array int"
      case _ => "autre chose"
    }

    println(typeArray(Array(1, 2)), typeArray(Array("a")), typeArray(List(1)))

    // variable binding
    println(BinOp("+", UnOp("-", Number(0)), Number(1)) match {
      case (BinOp(_, e@UnOp("-", Number(0)), _)) => e
      case _ => "autre"
    })

    // pattern guard
    /* def simplifyAdd(op: Expr) = op match{
      case BinOp("+", x, x) => BinOp("*", x, Number(2)) // ne marche pas: x doit apparaitre qu'une fois
      case _ => op
    }*/

    def simplifyAdd(op: Expr) = op match {
      case BinOp("+", x, y) if x == y => BinOp("*", x, Number(2)) // match si c'est true
      case _ => op
    }

    def unGuardBizarre(t: Any) = t match {
      case (x, y) if true => "on rentre toujours"
      case _ => "on rentre jamais si c'est un tuple"
    }

    println(unGuardBizarre(("a", 4)), unGuardBizarre(1))

    // pattern overlaps
    // ordre important : du plus au moins specifique

    def simplifyAll(expr: Expr): Expr = expr match {
      case UnOp("-", UnOp("-", e)) =>
        simplifyAll(e) // ‘-’ is its own inverse
      case BinOp("+", e, Number(0)) =>
        simplifyAll(e) // ‘0’ is a neutral element for ‘+’
      case BinOp("*", e, Number(1)) =>
        simplifyAll(e) // ‘1’ is a neutral element for ‘*’
      case UnOp(op, e) =>
        UnOp(op, simplifyAll(e))
      case BinOp(op, l, r) =>
        BinOp(op, simplifyAll(l), simplifyAll(r))
      case _ => expr
    }

    println("simplifyAll: \n")
    println(simplifyAll(UnOp("-", UnOp("-", Number(10)))))

    println
    println("sealed classes: \n")
    // sealed classes

    // compiler warning non exhaustif
    def describeBad(x: Expr) = x match{
      case Number(_) => "number"
      case Var(_) => "var"
    }
    println(describeBad(Number(0)))
    println(UnOp("-", Var("1")))

    // deuxieme solution mais arete le compilo
    def describeBad2(x: Expr) = x match{
      case Number(_) => "number"
      case Var(_) => "var"
      case _ => throw new RuntimeException
    }

    // annotation
    def describe(e: Expr): String = (e: @unchecked) match {
      case Number(_) => "a number"
      case Var(_) => "a variable"
    }

    // type Option
    println
    println("Option\n")
    val capital = Map("france"->"paris", "italie"->"rome")
    println(capital get "france", capital get "japon")

    // on definit une methode pour la Map
    def show(x: Option[String]) = x match {
      case Some(a) => "capitale: "+a
      case None => "capitale inconnue"
    }

    println(capital get "france")
    println(show(capital get "france"), show(capital get "japon"))

    println

    // pattern en dehors de match
    val myT = (10, "dix")
    val (myTN, myTS) = myT
    println(myTN, myTS)

    // deconstruire une case class via un pattern
    val binop: BinOp = BinOp("+",Number(1), Number(2))
    val BinOp(operateur, gauche, droite) = binop

    // la fonction value suivante va de Option[Int] vers Int
    val withDefault: Option[Int] => Int = {
      case Some(qqch) => qqch
      case None => 0
    }

    println(withDefault(Some(1)), withDefault(None))

    val divide: (Option[Int], Option[Int]) => String ={
      case (Some(x), Some(y)) if y != 0 => "division bonne"
      case _ => "division par 0"
    }

    println(divide(Some(1), Some(0)), divide(Some(1), Some(2)))

    // une fonction partielle est une fonction qui n'est pas definie sur tout l'ensemble (ex: Int => Double = x/y pas definit en 0)
    val secondeElem: PartialFunction[List[Int], Int] = {
      case a::b::_ => b
    }

    println(secondeElem isDefinedAt List(), secondeElem isDefinedAt List(1), secondeElem isDefinedAt List(1,2,3))

    // pattern dans un for
    for((pays, ville) <- capital) print(pays, ville)

    println
    // si ca ne match pas le pattern alors c'est pas pris en compte:
    for(Some(fruit) <- List(Some("banane "), Some("orange "), None, Some("framboise "))) print(fruit)

    val f = new ExprFormatter
    val e1 = BinOp("*", BinOp("/", Number(1), Number(2)),
      BinOp("+", Var("x"), Number(1)))
    val e2 = BinOp("+", BinOp("/", Var("x"), Number(2)),
      BinOp("/", Number(1.5), Var("x")))
    val e3 = BinOp("/", e1, e2)
    def showEx(e: Expr) = println(f.format(e)+ "\n\n")
    for (e <- Array(e1, e2, e3)) showEx(e)
  }





}


