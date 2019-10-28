package week04

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Var(s: String) extends Expr
case class Product(e1: Expr, e2: Expr) extends Expr