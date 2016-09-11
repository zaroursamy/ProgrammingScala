/**
  * Created by Samy on 11/09/2016.
  */

/**
  * ce n'edt pas parcequ'il n'y a pas de var que c'est immutable: elle peut avoir des methode qui appelle des objets qui ont un etat mutable
  * .une class peut etre aussi purement fonctionnelle en ayant des vars
  */
class BankAccount{
  private var bal: Int = 0

  def balance: Int = bal

  def deposit(amount: Int): Unit = {
    require(amount > 0)
    bal += amount
  }

  def withDraw(amount: Int): Boolean = {
    if(amount > bal) false
    else{
      bal -= amount
      true
    }
  }

  override def toString: String = "Solde: "+bal
}//end bank

class Keyed{
  def computeKey: Int = math.exp(1568796854651.0).toInt
}

// plus rapide: si on appelle une 2de fois computeKey, on a deja la valeur en cache. On a une var, mais si Keyed est purement fonctionnel alors MemoKeyed aussi
class MemoKeyed extends Keyed{
  private var keyCache: Option[Int] = None

  override def computeKey: Int = {
    if(!keyCache.isDefined) keyCache = Some(super.computeKey)
    keyCache.get
  }
}// end MemoKeyed

// private[this]: accessible seulement depuis l'objet qui contient la variable
// si la var est protected, ses getter/setter le sont aussi, si elle est public eux aussi etc
class Time{
  var h = 12
  var m = 0
}

// equivalent a Time
class Time2{
  private[this] var h = 12
  private[this] var m = 0

  def hour: Int = h
  def hour_=(x: Int){h=x}

  def minute: Int = m
  def minute_=(x: Int){m=x}
}

class Time3{

  private[this] var h = 12
  private[this] var m = 0

   def hour_=(x: Int): Unit = {
    require(0 <= x && x < 24)
    h = x
  }

   def minute_=(x: Int): Unit = {
    require(0 <= x && x < 60)
    m = x
  }
}

// definir getter et setter sans un field associé
class Thermo{
  var celsius: Float = _ // assign a zero value (0 pour Int, false pour Boolan, null pr des reference de type) equivalent de var celsiues:FLoat: abstract variable

  def farhenheit = celsius*9/5+32
  def farhenheit_=(f: Float): Unit ={
    celsius = (f-32)*35/9
  }

  override def toString: String = farhenheit+"F/"+celsius+"C"
}



object Chapitre18 {

  /*
  immutable: une operation sur un immutable retournera toujours la meme chose (.head, .length ...)
   */

  val account = new BankAccount
  account deposit 100
  account withDraw 80
  account withDraw 21 // retourne false contrairement a la ligne precedente! etat mutable

  val th =  new Thermo
  th.farhenheit_=(150)


  def main(args: Array[String]): Unit = {
    println(account)
    println(th)
    th.celsius = 1
    println(th)
  }


}
