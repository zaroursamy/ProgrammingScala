import scala.collection.immutable.TreeMap

/**
  * Created by Samy on 09/09/2016.
  */
object Chapitre17 {

  // initialisation d'une liste
  val liste = List("r","g","b")

  // d'un array
  val tableau = new Array[Int](5)
  val tableau2 = Array(5,4,3,2,1)
  tableau(0) = tableau2(0)

  // ListBuffer: objet mutable. bien contre le stack overflow
  import scala.collection.mutable.ListBuffer
  val buf = new ListBuffer[Int]
  buf +=1
  buf +=2
  10 +=: buf // ajoute au debut

  // ArrayBuffer
  import scala.collection.mutable.ArrayBuffer
  val aBuf = new ArrayBuffer[String]() // pas besoin de spécifier la taille
  aBuf += "salut"
  "hey" +=: aBuf

  def hasMajuscule(x: String): Boolean = x.exists(_.isUpper)

  // Set et Map. scala.collection.immutable.Map = Predef.Map = Map
  import scala.collection.mutable
  val mutaSet = mutable.Set(1,2,3)
  val immuSet = Set(1,2,3)

  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ !.,?]+")
  // methode empty de l'objet compagnon Set
  val words = mutable.Set.empty[String]

  for(mot <- wordsArray) words+=mot.toLowerCase



  // les Set
  val num = Set(1,2,3)
  val numM = mutable.Set(1,2,3)
  // ne modifie pas les Set
  num +4
  numM +4

  val music = mutable.Set.empty[String]
  music ++= List("do", "re", "mi")

  // les Map
  val map = mutable.Map.empty[String, Int]
  map("A") = 1
  map("C") = 3

  def countWords(text: String) = {
    val counts = mutable.Map.empty[String, Int]

    for(mot <- text.split("[ !?,.;]+")){
      val motMin = mot.toLowerCase
      val oldCount = if(counts.contains(motMin)) counts(motMin)  else 0
      counts += (motMin -> (oldCount+1)) // on aurait pas pu faire += si c'etait immutable
    }
    counts
  }

  // immutable Map
  val romain = Map("i" -> 1, "ii" -> 2)
  romain + ("iv" -> 6)

  // mutable Map
  val romainM = mutable.Map("i"->1, "ii"->2)
  romainM += ("iii" -> 3)


/*
une Map ou un Set avec plus de 5 elements appelles la factory HashMap ou HashSet. Sinon Map1 , Map2 ...: méthodes optimisées.
pour ces petites tailles, les immutable sont  + compacts que les mutable
 */

  // TreeSet et TreeMap : implémentent les traits SortedSet et SortedMap
  import scala.collection.immutable.TreeSet
  val ts = TreeSet(5,3,4,8,7,2,3,1,5,2,5,4,6,8,9,1)

  val ts2 = TreeSet("zied","samy","haj","zarour")

  import scala.collection.immutable.TreeMap
  var tm = TreeMap("b"->2, "e"->5, "a"->1)

  val people = Set("arnold","silvester")
  //people += "jason"
  var people2 = Set("arnold","silvester")
  people2 += "jason"

  // remplir une colection avec une autre
  val colL = List("red","blue")
  val colS = TreeSet[String]() ++ colL
  val colS_L = colS.toList
  val colS_A = colS.toArray


  val mutaCol = mutable.Set.empty[String] ++= colS

  def longestWord(words: Array[String]) = {

    var word = words(0)
    var idx = 0

    for(i <- 1 until words.length){
      if(words(i).length > word.length){
        word = words(i)
        idx = i
      }
    }
    (word, idx)

  }




  def main(args: Array[String]): Unit = {

    tableau.foreach(a => print(a +" "))
    println(buf(1))
    println(aBuf, aBuf(0))
    println(hasMajuscule("Salut")) // conversion implicite de string vers stringops car string n'a pas de methode exists. traite le string en sequence de char
    println(wordsArray.mkString("-"))
    println(words)

    // methodes sur les Set
    println(num, numM)
    println(num+8, numM+10)
    println(num-1, numM-2)
    println(Set("a","b")+"c"-"b")
    println(num -- List(1,3), num ++ List(1,6))
    println(num & Set(1,3,8,10)) // prend l'intersection des deux Set
    println(num.size)
    println(num.contains(9), num.contains(1))
    println(music)
    music --= List("do","la","mi")
    println(music)
    music.clear()
    println(music)

    println(map, map("C"))

    println("Count words")
    println(countWords("hi my name is ... hi there is my name"))

    println(romain)
    romain - "i" // créé une nouvelle instance mais ne modifie pas la Map
    println(romain)
    println(romain-"ii")
    println(romain.contains("i"))
    println(romain.keys, romain.keySet, romain.values, romain.isEmpty)// .keys = Iterator[String], .keySet = Set[String] immutable

    println(romainM)
    romainM -= "i"
    println(romainM)
    romainM ++= List("v"->5, "x"->10)
    println(romainM)

    println(ts)
    println(ts2)
    println(tm)
    tm += ("z"->26)
    println(tm)

    println(people2)
    people2 ++= List("rocky","terminator")
    println(people2)

    println(colL, colS)
    println(colS_A.toString, colS_L)
    println(mutaCol)

    println(longestWord(Array("un","deux","troisieme"))._1)
    val  (lg,id) = longestWord(Array("un","deux","troisieme"))
    println(lg, id)
    val lg1, id1 = longestWord(Array("un","deux","troisieme"))
    println(lg1, id1)
  }
}
