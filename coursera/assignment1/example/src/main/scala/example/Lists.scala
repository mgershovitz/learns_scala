package example


object Lists {

  /**
   * This method computes the sum of all elements in the list xs. There are
   * multiple techniques that can be used for implementing this method, and
   * you will learn during the class.
   *
   * For this example assignment you can use the following methods in class
   * `List`:
   *
   *  - `xs.isEmpty: Boolean` returns `true` if the list `xs` is empty
   *  - `xs.head: Int` returns the head element of the list `xs`. If the list
   * is empty an exception is thrown
   *  - `xs.tail: List[Int]` returns the tail of the list `xs`, i.e. the the
   * list `xs` without its `head` element
   *
   * ''Hint:'' instead of writing a `for` or `while` loop, think of a recursive
   * solution.
   *
   * @param xs A list of natural numbers
   * @return The sum of all elements in `xs`
   */

  def sum(xs: List[Int]): Int = {
    if (xs.isEmpty) {
      0
    } else {
      xs.head + sum(xs.tail)
    }
  }

  @scala.annotation.tailrec
  def recur_tail_sum(current_total: Int, xs: List[Int]): Int = {
    if (xs.isEmpty)
      current_total
    else
      recur_tail_sum(current_total + xs.head, xs.tail)
  }

  def sum_recur(xs: List[Int]): Int = {
    recur_tail_sum(0, xs)
  }

  def sum_for(xs: List[Int]): Int = {
    var total = 0
    for (i <- xs) {
      total += i
    }
    total
  }

  def sum_while(xs: List[Int]): Int = {
    var total = 0
    var index = 0
    while (index < xs.length) {
      total += xs(index)
      index += 1
    }
    total
  }

  def sum_list(xs: List[Int]): Int =
    xs.sum

  def sum_fold(xs: List[Int]): Int =
    xs.fold(0)(_+_)
//    xs.fold(0)((a, b) => a + b)

  def sum_fold_left(xs: List[Int]): String =
    xs.foldLeft[String]("")((a: String, b: Int) => a + b.toString)


  /**
   * This method returns the largest element in a list of integers. If the
   * list `xs` is empty it throws a `java.util.NoSuchElementException`.
   *
   * You can use the same methods of the class `List` as mentioned above.
   *
   * ''Hint:'' Again, think of a recursive solution instead of using looping
   * constructs. You might need to define an auxiliary method.
   *
   * @param xs A list of natural numbers
   * @return The largest element in `xs`
   * @throws java.util.NoSuchElementException if `xs` is an empty list
   */

  def max_recur(max_elem: Int, xs: List[Int]): Int = {
    if (xs.isEmpty) {
      max_elem
    } else {
      math.max(max_elem, max_recur(xs.head, xs.tail))
    }

  }

  def max(xs: List[Int]): Int = {
    if (xs.isEmpty) {
      throw new java.util.NoSuchElementException()
    } else {
      max_recur(xs.head, xs.tail)
    }
  }
}

object Main extends App {
  println(Lists.sum_recur(List(1, 3, 2)))
  println(Lists.sum_recur(List(1, 3, 2, 1)))
}
