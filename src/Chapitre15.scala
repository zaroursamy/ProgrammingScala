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
  }



}
