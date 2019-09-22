package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || r == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    @scala.annotation.tailrec
    def balanced_recur(chars_remainder: List[Char], parenthesis_balance: Int): Boolean = {

      def get_parenthesis_balance(i: Int): Int = {
        if (chars_remainder.head == '(') i + 1
        else if (chars_remainder.head == ')') i - 1
        else i
      }


      if (parenthesis_balance < 0) false
      else if (chars_remainder.isEmpty) parenthesis_balance == 0
      else balanced_recur(chars_remainder.tail, get_parenthesis_balance(parenthesis_balance))
    }

    balanced_recur(chars, 0)

  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {

    def useFirstCoin(money: Int): Int = money - coins.head

    def removeFirstCoin(coins: List[Int]): List[Int] = coins.tail

    if (money == 0) 1
    else if (money < 0 || coins.isEmpty) 0
    else countChange(money, removeFirstCoin(coins)) + countChange(useFirstCoin(money), coins)
  }
}
