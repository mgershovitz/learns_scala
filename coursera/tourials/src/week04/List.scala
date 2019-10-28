package week04
//
//trait List[+T] {
//  def isEmpty: Boolean
//
//  def head: T
//
//  def tail: List[T]
//
//  override def toString: String = if (isEmpty) "." else head + " " + tail.toString()
//}
//
//class Cons[T](val head: T, val tail: List[T]) extends List[T] {
//  def isEmpty = false
//}
//
//object Nil extends List[Nothing] {
//  def isEmpty: Boolean = true
//
//  def head: Nothing = throw new NoSuchElementException("Nil.head")
//
//  def tail: Nothing = throw new NoSuchElementException("Nil.tail")
//
//}
//
//object test {
//  def x: List[String] = Nil
//}
//
//object List {
//  def apply[T](): List[T] = new Nil.type
//  def apply[T](x: T): List[T] = new Cons(x, new Nil.type )
//  def apply[T](x: T, y: T): List[T] = new Cons(x, new Cons(y, new Nil.type ))
//}
//
//

