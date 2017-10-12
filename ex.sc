object ex {
  val x = Vector(Vector(1,2), Vector(3,4))        //> x  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Int]
                                                  //| ] = Vector(Vector(1, 2), Vector(3, 4))
	val y = Vector(Vector(1,2), Vector(3,4))  //> y  : scala.collection.immutable.Vector[scala.collection.immutable.Vector[Int]
                                                  //| ] = Vector(Vector(1, 2), Vector(3, 4))
  Helper.subMatrix(x,y)                           //> res0: Vector[Vector[Int]] = Vector(Vector(0, 0), Vector(0, 0))
  
  Helper.strassen(x,y)                            //> res1: Vector[Vector[Int]] = Vector(Vector(7, 10), Vector(15, 22))
  
	//Helper.strassen(x,y)
}