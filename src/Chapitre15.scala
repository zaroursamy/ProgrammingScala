/**
  * Created by Samy on 21/08/2016.
  */

/**
  * case class: ajoute une methode factory (Var("x") au lieu de new...) ET les parametres sont des val
  * supporte le pattern matching
  *
  * MAIS: classe et objets deviennent plus large a cause de l'ajout de methodes
  */
abstract class Expr
case class Var(name: String) extends Expr
case class Number(num: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

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

  }

}
