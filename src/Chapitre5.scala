/**
  * Created by Samy on 15/07/2016.
  */
object Chapitre5 {
  // byte < short < int < long < float < double

  def main(args: Array[String]): Unit = {

    // hexadecimal: commencer par 0x
    val hex = 0x5
    println(hex)
    val hex2 = 0xAAFF
    println(hex2)

    // long: termine par L ou l
    val long = 35L
    val long2 = 0x5l
    println(long + "\n" + long2)

    // short et long
    val little: Short = 8
    val littler: Byte = 8
    println(little + "\n" + littler)

    // double et float
    val double = 1.2
    val float = 1.2f
    println(double+" "+float)

    // caractere unicode (octal number entre 0 et 377 après le \
    val A1 = '\101'
    println(A1)

    // ou 4 hex digits apres u:
    val A2 = '\u0041'
    val D = '\u0044'
    println(A2+" "+D)

    val backslash = '\\'
    println(backslash)
    println(
      """salut gro
        |ca va bien ?
        |"okok"
      """)
    println(
      """salut gro
        |ca va bien ?
        |"okok"
      """.stripMargin)
  }

}
