package week04

object exp {
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(l, r) => show(l) + " + " + show(r)
    case Var(s) => s
    case Product(Sum(x, y), r) => "(" + show(Sum(x, y)) + ")" + " * " + show(r)
    case Product(l, r) => show(l) + " * " + show(r)
  }

}


object HelloWorld {
  def main(args: Array[String]): Unit = {
    println(exp.show(Sum(Product(Number(2), Var("x")), Var("y"))))
    println(exp.show(Product(Sum(Number(2), Var("x")), Var("y"))))
  }
}
