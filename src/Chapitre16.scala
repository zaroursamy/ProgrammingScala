/**
  * Created by Samy on 03/09/2016.
  */
object Chapitre16 {

  /**
    * les listes sont immutables et ont une sutrcture recursive (differences avec les array)
    * listes sont covariantes: S de type T alors List[S] de type List[T]
    *
    * fonction premier ordre : prend pas de fonction en parametre, contrairement aux fonctions d'ordre superieur
    */

  val l1: List[Int] = List(1,2,3)
  val l2: List[Nothing] = List() // sous type de List[T] pour tout T
  val l22: List[String] = List() // car Nothing est sous type de tout
  val l3: List[List[Int]] = List(List(1,0,0), List(0,1,0), List(0,0,1))

  val l11 = 1::(2::(3::Nil))
  val l33 = (1::(0::(0::Nil)))::
    (0::(1::(0::Nil)))::
    (0::(0::(1::Nil)))::Nil

  // trie les deux premiers  elements
  def insert(x: Int, xs: List[Int]): List[Int] = {
    if(xs.isEmpty || x <= xs.head) x::xs
    else xs.head::insert(x, xs.tail)
  }

  // tri par ordre croissant
  def isort(xs: List[Int]): List[Int] = {
    if(xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  }

  /*
  avec le pattern matching
   */
  def insertP(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => List(x)
    case y::ys => if(x <= y) x::xs else y::insertP(x, ys)
  }

  def isortP(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case x::xs1 => insert(x, isortP(xs1))
  }

  def rev[T](xs: List[T]): List[T] = xs match {
    case Nil => xs
    case x::xs1 => rev(xs1):::List(x)
  }

  // tri une liste en nlog(n) par split. Currification marche bien pour spécialisé des traitements
  def msort[T](less: (T,T) => Boolean)(xs: List[T]): List[T] = {

    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match{
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x::xs1, y::ys1) => if(less(x,y)) x::merge(xs1, ys) else y::merge(xs, ys1)
    }

    val n = xs.length/2

    if(n == 0) xs
    else{
      val (xs1, xs2) = xs splitAt n
      merge(msort(less)(xs1), msort(less)(xs2))
    }
  }

  val intSort = msort((x:Int, y:Int) => x<y)_
  val intSortDecrease = msort((x:Int, y:Int) => x>y)_

  def hasZeroRow(m: List[List[Int]]): Boolean = {
    m exists (row => row forall (_==0))
  }

  // fold left operation: (z /: xs) (op). z: début, xs: liste, operation binaire commence par z et fait sur les element de la liste
  //(z /: List(a, b, c)) (op) equals op(op(op(z, a), b), c)
  def sumList(x: List[Int]): Int = (0 /: x) (_+_)
  def sumListPlusOne(x: List[Int]): Int = (1 /: x)(_+_)
  def mulList(x: List[Int]): Int = (1 /: x)(_*_)
  def concatWord(x: List[String]): String = ("" /: x)(_+" "+_)
  def concatWord2(x : List[String]): String = (x.head /: x.tail)(_+"|"+_)

  // fold right. Idem que fold left pr operations commutative. Mais pas pr un produit matriciel par ex
  def rightC(x: List[String]): String = (x :\ "@")(_+"!"+_)
  def leftC(x: List[String]): String = ("@" /: x)(_+"!"+_)

  // on doit définit un type pour la liste vide quand meme, on peut pas List().
  // le left prend plus de temps : n-1 fois copie le premier element x.head
  def flattenL[T](x: List[List[T]]): List[T] = (List[T]()/:x)(_:::_)
  def flattenR[T](x: List[List[T]]): List[T] = (x :\ List[T]())(_:::_)

  def reverseL[T](x: List[T]) = (List[T]() /: x){(x1,x2) => x2::x1} // inverse en O(n) !!

  def tri[T](f:(T,T) => Boolean)(x: List[T]): List[T] = x sortWith f


  def main(args: Array[String]): Unit = {
    println(l1 == l11, l2 == l22, l3 == l33)

    println(l1.head, l3.head)

    println("insert")
    println(insert(99, List()))
    println(insert(99, List(100,101)))
    println(insert(99, List(100,98)))
    println(insert(99, List(98,100,101)))
    println(insert(99,List(98,101,100)))
    println(insert(99, List(98,92,90,93)))

    println("isort")
    println(isort(List(1,2,3)))
    println(isort(List(3,2,1)))
    println(isort(List(3,2,4,1,5,8)))

    // list pattern
    val List(a,b,c) = List(1,2,3)
    println(a,b,c)
    val aa::bb::_ = List(1,2,3) // a::b est traité comme ::(a,b). scala.:: est une classe. la methode :: produit une instance de la classe ::
    val aaa::bbb::ccc = List(1,2,3,4,5,6,7)
    println(aa,bb)
    println(aaa,bbb,ccc)

    // concatenation de listes
    println(List(1,2):::List(3,4,5):::List(10)) // equivalent a x:::(y:::z)

    // operations sur les listes. tout comme tail et head , exception sur Nil.
    // ont besoin de parcourir toute la liste
    println("last ", List(1,2,3,4).last) // le dernier
    println("init ", List(1,2,3,4).init) // tout sauf le dernier

    println("reverse ", List(1,2,3,4).reverse) // ne modifie pas la liste d'origine mais cree une nouvelle instance
    println("take: ", List(1,2,3,4,5) take 2) // prend les 2 premiers elements
    println("drop: ", List(1,2,3,4) drop 3) // tous sauf les 3 premiers
    println("splitat: ", List(1,2,3,4,5) splitAt 3) // split les trois premiers et le reste en deux listes
    println("apply: ", List(1,2,3,4) apply 0, List(1,2,3,4)(0), List(1,2,3,4) apply 2 equals (List(1,2,3,4) drop 2).head) // retourne le 0eme element. le temps est proportionnel a l'indice....
    println("indices: ", List(1,2,3).indices) // retourne les indices
    // flatten ne sapplique qu'aux listes dont les elements sont des listes, sinon erreur ex List(1,2).flatten
    println("flatten: ", List(List("a","b","c"), List("x","y"), List("z")).flatten, List("hello","world").map(_.toCharArray),List("hello","world").map(_.toCharArray).flatten)

    val abcde = List('a','b','c','d','e')
    val l123456789 = (1 to 9).toList
    println(abcde zip l123456789)

    println(abcde zipWithIndex)
    println(abcde zip l123456789 unzip)
    println(List(('a',1), ('b',2)).unzip)// unzip: prend des liste de tuples de taille 2 et les dezip
    println(abcde toString)

    println("mkString: ", abcde mkString, abcde.mkString("!", "-----", "?")) // mkString , addString: hérite du trait Traversable
    // conversion array
    println(List(1,2).toArray, Array(1,2).toList)

    val buf = new StringBuilder
    println(abcde addString (buf, "{",";","}"))

    // lsit vers array
    val arr = new Array[Int](10)
    println(arr.mkString(" "))
    List(1,1,1) copyToArray (arr, 2)
    println("copytoarray: ", arr.mkString(" ")) // met la liste dans l'array a l'indice 2 (troisieme element)

    val it = abcde.iterator
    println(it, it.next(), it.next(), it)

    // tri msort.
    println(msort((x: Int, y: Int) => x<y)(List(-1,2,-5,-6,70,54,-840)), intSort(List(1,0,-1)), intSortDecrease(List(-1,0,1)))

    println(intSort(List(-1,0,1)) == intSortDecrease(List(-1,0,1)).reverse)

    /*
    fonctions map etc
     */
    println("\n-------- map etc -----------")
    val li = List(-1,0,4,-8,-10,80)
    val ls = List("orange","yellow","blue","red","brown","grey")

    println(li map (_ +2), ls map  (_.length))
    println(ls.map(_.reverse))

    val ls_list = ls map (_.toList)
    println("ls list: ", ls_list)
    val ls_flatmap = ls.flatMap(_.toList)
    println("ls flatmap: ", ls_flatmap)

    val l = for(i <- List.range(1,5); j <- List.range(1,i)) yield (i,j)
    println(l)
    val ll = List.range(1,5).flatMap(i => List.range(1,i).map(j => (i,j)))
    println(ll)

    // foreach: prend une procédure (return Unit)
    var sum = 0
    List(1,2,3).foreach(sum+=_)
    println(sum)

    println("filter: ", List(-1,0,564,-81,987).filter(_>0), List(1,2,3).find(_<0))

    //partition: premier element respecte le predicat, les autres non
    println("partition: ", List(-1,0,564,-81,987).partition(_>0))

    // find: retourne le premier element satisfaisant le predicat
    println("find: ", List(-1,0,1,5).find(_<0), List(-1,0,1,9).find(_>0))

    // takewhile et drop while: garde et supprime le plus long prefixe verifiant le predicat
    println("takeWhile: ", List(-5,1,2,3,-5,-7,80).takeWhile(_>0))
    println("drophile: ", List(-5,1,2,3,-5,-7,80).dropWhile(_>0))
    println("dropWhile: ", List("ah","tah","tih","ahh","at","ta").dropWhile(_.startsWith("t")), List("tah","tih","ahh","at","ta").dropWhile(_.startsWith("t")))
    //xs span p equals (xs takeWhile p, xs dropWhile p)
    println("span: ", List(-5,1,2,3,-5,-7,80).span(_>0)) // combine take et drop while
    println("span: ", List(-5,1,2,3,-5,-7,80).span(_<0))

    // forall p: true si tous les elements satisfont p
    println("forall : ", List(1,2).forall(_>0), List(1,-2).forall(_>0))

    // exist: true si un element verifie p
    println("exists: ", List(1,2,5,0,1) exists (_==0))

    println("haszerorow", hasZeroRow( List(List(1,1,1), List(0,0,0))), hasZeroRow(List(List(1,0,1), List(2,3,1))))

    println(concatWord(List("mot1","mot2")), concatWord2(List("hey","hoy")), mulList(List(1,2,3,4)), sumList(List(1,2)), sumListPlusOne(List(1,2)))
    println("left: ", leftC(List("salut","mec")), "right: ", rightC(List("salut","mec")))

    val List(from, until) = List(1,10)
    println("range ", List.range(from, until), List.range(from, until, 4))
    println("fill: ", List.fill(3)("samy"), List.fill(2,4)("samy"))
    println("tabulate: " ,List.tabulate(5)(n => n+1), List.tabulate(2,5)( (x,y) => x+y), List.tabulate(5,5)(_*_))
    println("concat ", List.concat(List("ok1","ok2")), List.concat(List(), List('a')))

  }

}
