package week01

object week01 {
  def abs(x: Double): Double = if (x < 0) -x else x

  def sqrt(x: Double): Double = {
    @scala.annotation.tailrec
    def sqrtIter(guess: Double): Double =
      if (isGoodEnough(guess)) guess
      else sqrtIter(improve(guess))

    def isGoodEnough(guess: Double): Boolean = abs(x - guess * guess) / x < 0.001

    def improve(guess: Double): Double = (guess + x / guess) / 2

    sqrtIter(1.0)
  }

  println(sqrt(4))

}
