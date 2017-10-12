import scala.io.StdIn

object MatrixMultiplier extends App {

  println("Please enter the order of the n*n matrix")
  print("n=")
  
  val n = StdIn.readInt()
  println("Please enter the first matrix values")
  val first = Helper.populateMatrix(n)
  
  Helper.viewMatrix(first)
  println("Please enter the second matrix values")
  val second = Helper.populateMatrix(n)
  Helper.viewMatrix(second)
  println("now taking product of the matrices")
  Helper.viewMatrix(Helper.strassen(first, second))
}