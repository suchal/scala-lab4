import scala.io.StdIn

object Helper {
  def populateMatrix(n: Int) = {
    def getUserInput():Int = {
      println("Please enter the next value: ")
      return StdIn.readInt()
    }
    def loop(acc: Vector[Vector[Int]], i: Int): Vector[Vector[Int]] = {
      if(i == n)
        return acc
      val v1 = for(j <- Vector.range(0, n)) 
        yield(getUserInput())
      return loop( acc :+ v1, i+1) 
    }    
    loop(Vector(), 0)    
  }
  
  def viewMatrix(x: Vector[Vector[Int]]) = {
    for(y <- x){
      for(z <- y){
        print(z.toString + "\t")
      }
      print("\n")
    }   
  }
  
  def addMatrix(first:Vector[Vector[Int]], second: Vector[Vector[Int]]):Vector[Vector[Int]] = {
    val n = first(0).length;
    def loop(acc: Vector[Vector[Int]], i: Int): Vector[Vector[Int]] = {
      if(i == n)
    	  return acc
      val v1 = for(j <- Vector.range(0, n)) 
        yield(first(i)(j) + second(i)(j))
      return loop( acc :+ v1, i+1) 
    }    
    loop(Vector(), 0) 
  }
  
 def subMatrix(first:Vector[Vector[Int]], second: Vector[Vector[Int]]):Vector[Vector[Int]] = {
    val n = first(0).length;
    def loop(acc: Vector[Vector[Int]], i: Int): Vector[Vector[Int]] = {
      if(i == n)
    	  return acc
      val v1 = for(j <- Vector.range(0, n)) 
        yield(first(i)(j) - second(i)(j))
      return loop( acc :+ v1, i+1) 
    }    
    loop(Vector(), 0) 
  }
  
  def splitMatrix(m:Vector[Vector[Int]], i:Int, j:Int): Vector[Vector[Int]] = {
    val n = m.length;
    val a11 = m.slice(i, i+n/2)

    val a110 = for (k <- a11) yield( k.slice(j, j+n/2) )
    a110
  }
  
    def joinMatrix(a11:Vector[Vector[Int]], a12:Vector[Vector[Int]], a21:Vector[Vector[Int]], a22:Vector[Vector[Int]]): Vector[Vector[Int]] = {
      val n = a11.length
      val upper = for(i <- Vector.range(0, n)) yield(a11(i) ++ a12(i))
      val lower = for(i <- Vector.range(0, n)) yield(a21(i) ++ a22(i))
      
      upper ++ lower
    }
  
  
  def strassen(first:Vector[Vector[Int]], second: Vector[Vector[Int]]):Vector[Vector[Int]] = {
    val n = first.length
    if(n == 1)
      return Vector(Vector(first(0)(0) * second(0)(0)))
    else{
      val a11 = Helper.splitMatrix(first, 0, 0)           
      val a21 = Helper.splitMatrix(first, n/2, 0)          
      val a12 = Helper.splitMatrix(first, 0, n/2)          
      val a22 = Helper.splitMatrix(first, n/2, n/2)  
      
      
      val b11 = Helper.splitMatrix(second, 0, 0)           
      val b21 = Helper.splitMatrix(second, n/2, 0)          
      val b12 = Helper.splitMatrix(second, 0, n/2)          
      val b22 = Helper.splitMatrix(second, n/2, n/2)   
      
      
      val m1 = strassen(addMatrix(a11, a22), addMatrix(b11,b22))
      val m2 = strassen(addMatrix(a21,a22), b11)
      val m3 = strassen(a11, subMatrix(b12, b22))
      val m4 = strassen(a22, subMatrix(b21, b11))
      val m5 = strassen(addMatrix(a11, a12), b22)
      val m6 = strassen(subMatrix(a21,a11), addMatrix(b11, b12))
      val m7 = strassen(subMatrix(a12, a22), addMatrix(b21, b22))
      
      val c1 = addMatrix(subMatrix(addMatrix(m1, m4), m5), m7)
      val c2 = addMatrix(m3, m5)
      val c3 = addMatrix(m2,m4)
      val c4 = addMatrix(subMatrix(m1,m2), addMatrix(m3, m6))
      joinMatrix(c1, c2, c3, c4)
    }
  }
}