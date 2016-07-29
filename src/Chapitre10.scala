/**
  * Created by Samy.Zarour on 29/07/2016.
  */
class Chapitre10 {

  // abstract class: signifie que la classe peut avoir des méthodes non implémentées.
  // on ne peut pas l'instantcier
  abstract class Element{

    // pas de liste de parametre ! ( ie def height():Int ) : parameterless method. il faut qu'il n'y ai pas de param et qu'elle ne change pas d'etat des mutables


    def contents: Array[String]

   // def height: Int = contents.length

   // def weight: Int = if(height != 0) contents(0).length else 0


    // mais on peut les mettre sous forme de var: plus rapide , car pré calculé à l'initialisation de la classe,
    // au lieu detre calcule a chaque fois que la classe est appelée. MAIS les fields prennent plus de place en mémoire

    val height = contents.length
    val weight = if(height != 0) contents(0).length else 0
  }
}
