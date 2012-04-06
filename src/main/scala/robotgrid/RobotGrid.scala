package scala.eightqueens
import scala.collection.mutable.Stack

object EightQueens {
  def main(args:Array[String]) : Unit = {
    n = Integer.parseInt(args.first) 
    findQueens();
  }
  var n:Int = 8;
  
  def findQueens(): Unit = {
    var stack: Stack[List[Int]] = new Stack[List[Int]]
    for (row <- 1 to n) {
      stack = stack.push(List(row));
    }
    var found:List[List[Int]] = List()
    var unique:List[List[Int]] = List()
    var count:Long = 0;
    while (stack.size > 0) {
      // depth first tree search... pop off the top list
      val cur: List[Int] = stack.pop
      // if we get to size 8, we found a solution
      if (cur.size == n) {
        count+=1;
        /*
        found ::= cur
        if(!findRotate(unique, cur) && !unique.contains(cur.reverse)) {
          unique ::= cur
        }*/
      }
      for (row <- 1 to n) {
        var next: List[Int] = cur;
        // checks verticals and diagonals before pushing
        if (!next.contains(row) && !checkDiagonal(next, row)) {
          next ::= row;
          stack = stack.push(next);   
        }
      }
    }
    unique.foreach(b => {
        println(b)
        println(printBoard(b))
      }
    )
    println("found %d distinct solutions".format(count))
    //println("found %d unique solutions".format(unique.size))
  }
  
  // prints the board to a string
  def printBoard(board:List[Int]):String = {
    var ret:String = ""
    for(col <- 1 to n) {
      for(row <- 1 to n) { 
        def mid:String = {
           if(board(col-1) == row) {
             "Q"
           } else {
             "."
           }
        }
        ret += " " + mid + " "
      }
      ret += "\n"
    }
    ret
  }

  // checks that the current row doesn't conflict on any diagonals
  def checkDiagonal(next:List[Int], row:Int): Boolean = {
    var found:Boolean = false;
    var r:Int = row
    for(col <- 0 to next.length-1) {
      if(next(col) == r-1) {
        found = true
      }       
      r -= 1
    }
    r = row;
    for(col <- 0 to next.length-1) {    
      if(next(col) == r+1) {
        found = true
      }
      r += 1
    }
    found
  }
  
  // somewhat cheesy way to find rotational equality
  // compare by printing out rotated boards
  def findRotate(unique:List[List[Int]], board:List[Int]):Boolean = {
    var found:Boolean = false
    unique.foreach(b => {
        if(printBoard(board).equals(printBoardRotate(b))) {
          found = true
        }
        if(printBoard(board).equals(printBoardRotate(b.reverse))) {
          found = true
        }
        if(printBoard(board.reverse).equals(printBoardRotate(b))) {
          found = true
        }
        if(printBoard(board.reverse).equals(printBoardRotate(b.reverse))) {
          found = true
        }
        if(printBoard(board).equals(printBoard180(b))) {
          found = true
        }
        if(printBoard(board).equals(printBoard180(b.reverse))) {
          found = true
        }
        if(printBoard(board.reverse).equals(printBoard180(b))) {
          found = true
        }
        if(printBoard(board.reverse).equals(printBoard180(b.reverse))) {
          found = true
        }
      }
    )
    found
  }
    
  // print the board rotated 90 degrees
  def printBoardRotate(board:List[Int]):String = {
    var ret:String = ""
    for(row <- 1 to n) {
      for(col <- 1 to n) { 
        def mid:String = {
           if(board(col-1) == row) {
             "Q"
           } else {
             "."
           }
        }
        ret += " " + mid + " "
      }
      ret += "\n"
    }
    ret
  }
  
  // print the board rotated 180 degrees
  def printBoard180(board:List[Int]):String = {
    var ret:String = ""
    for(col <- n to 1 by -1) {
      for(row <- n to 1 by -1) { 
        def mid:String = {
           if(board(col-1) == row) {
             "Q"
           } else {
             "."
           }
        }
        ret += " " + mid + " "
      }
      ret += "\n"
    }
    ret
  }
}