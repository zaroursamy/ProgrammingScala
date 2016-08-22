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

    val mutation = BinOp("mutate", Number(2.0), v)// meilleure visibilite sans les new

    val l = mutation.left

    println(v, mutation, l, mutation.right==v, mutation.right==Var("v"))

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
    def pm1(expr: Expr) = expr match{
      case BinOp(_,_,_) => println("binop")
      case _ => println("autre")
    }
    pm1(mutation)
    def pm2(expr: Expr) = expr match{
      case BinOp(operateur, gauche, droite) => println("binop")
      case _ => println("autre")
    }
    pm2(mutation)

    // constant patterns
    def pm3(x: Any) = x match{
      case true => "vrai"
      case "ok" => "ok !"
      case 5 => "cinq"
      case Nil => "liste vide"
      case _ => "other"
    }
    println(pm3(5), pm3(6), pm3(Nil))

    // variable pattern
    def pm4(x: Any) = x match{
      case 0 => "0"
      case autre => "autre: "+autre
    }

    println(pm4(0) , pm4(List(1,2)))


  }



}
