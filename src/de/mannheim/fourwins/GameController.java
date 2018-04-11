package de.mannheim.fourwins;

class GameControler extends Thread {
	  private Board theBoard;
	  private Player white;
	  private Player black;
	  private boolean whiteTurn = true;
	  private boolean running = false;
	  private boolean swapReqest = false;

	  void putTheBoard( Board b ) { theBoard = b; }

	  void putPlayer( Player p, boolean asWhite ) {
	    if( asWhite ) {
	      white = p;
	    } else {
	      black = p;
	    }
	  }
	  
	  boolean whiteToMove() { return whiteTurn; }

	  public void run() {
	    MoveDescr mvd;
	    while( true ) {
	      synchronized( this ) {
	        if( swapReqest ) {
	          Player tmp = white;
	          white = black;
	          black = tmp;
	          System.out.println("Players switched");
	          swapReqest = false;
	        }
	      }
	      if( whiteTurn ) {
	        mvd = white.draw();
	      } else {
	        mvd = black.draw();
	      }
	      synchronized( this ) {
	        if( swapReqest ) { // move will not be executed
	          Player tmp = white;
		  white = black; 
	          black = tmp;
	          System.out.println("Players swapped");
	          swapReqest = false;
	        } else { // execute move
		  MoveOutcome moc = theBoard.move(mvd);
	          if( moc == MoveOutcome.ok ) {
		     whiteTurn = !whiteTurn;
	          } else if( moc == MoveOutcome.won ) {
		    break;
	          } else {
		    System.out.println("Illegal move!");
	          }
	        }
	      }
	    }
	  }

	  synchronized void  startGame() {
	    if( !running ) { // and initialized
	      running = true;
	      start();
	    }
	  }
	  
	  synchronized void swapSides() {
	    swapReqest = true;
	  }
	}