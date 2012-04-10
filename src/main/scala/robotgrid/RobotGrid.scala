package scala.robotgrid
import scala.collection.mutable.Stack


object RobotGrid {
  def main(args:Array[String]) : Unit = {
    n = Integer.parseInt(args.first) 
    findPaths();
  }
  var n:Int = 8;
  
  def findPaths(): Unit = {
    val start = System.currentTimeMillis();
    var stack: Stack[List[(Int,Int)]] = new Stack[List[(Int,Int)]]
    // initialize the stack with the starting position
    stack = stack.push(List((0,0)));
    
    var count:BigInt = 0;
    var bit:Int = 0;
    while (stack.size > 0) {
      // depth first tree search... pop off the top position list
      val cur: List[(Int,Int)] = stack.pop

      getAdjacent(cur.first).foreach({ pos:(Int,Int) => 
        // don't repeat positions
        if(!cur.contains(pos)) {
          var next: List[(Int,Int)] = cur;
          next ::= pos;
          if (pos._1==n-1 && pos._2==n-1) {
            count += 1;
            if(count.testBit(bit)) {
              val end = System.currentTimeMillis(); 
              println("path count: "+count.toString(10) + "; stack size: "+stack.size+"; elapsed time: "+(end-start) +"ms");
              println(printPath(next));
              bit+=1;
            }
          } else {
            stack = stack.push(next);
          }
        }
      });
    }
    
    println("found %s solutions".format(count.toString(10)))
  }
  
  def getAdjacent(pos:(Int,Int)): List[(Int,Int)] = {
    var retList:List[(Int,Int)] = List();
    if(pos._1 > 0) {
      retList ::= (pos._1-1,pos._2);
    }
    if(pos._1 < n - 1) {
      retList ::= (pos._1+1,pos._2);
    }    
    if(pos._2 > 0) {
      retList ::= (pos._1,pos._2-1);
    }    
    if(pos._2 < n - 1) {
      retList ::= (pos._1,pos._2+1);
    }    
    retList;
  }

  def printPath(path:List[(Int,Int)]):String = {
    val arr = new Array[Array[Int]](n);
    for(i <- 0 until n) {
      arr(i) = new Array[Int](n);
      for(j <- 0 until n) {
        arr(i)(j) = 0;
      }
    }
    
    var count:Int = 1
    path.reverse.foreach({pos => 
      arr(pos._1)(pos._2) = count
      count += 1
    });
    val str = new StringBuilder
    for(i <- 0 until n) {
      for(j <- 0 until n) {
        if(arr(i)(j)!=0) str.append("%02d".format(arr(i)(j)));
        else str.append("..");
        str.append(" ");
      }
      str.append("\n");
    }
    str.toString;
  }
}
