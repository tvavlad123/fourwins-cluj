package de.mannheim.fourwins;

class Position { // datatype
	// Position beinhaltet, wer am Zug ist bzw. wer gewonnen hat.
	  static final byte empty = 0; 
	  static final byte white = 1;
	  static final byte black = 2;
	  static final byte whiteNext = 3;
	  static final byte blackNext = 4;
	  static final byte whiteWon  = 5;
	  static final byte blackWon  = 6;

	  private byte[] board;
	  private byte moveState;

	  Position( byte[] b, byte next ) {
	    // (b,next) has to be a correct position
	    board = new byte[42];
	    System.arraycopy(b,0,board,0,42);
	    moveState =  next;
	  }
	  
	  byte get(int c, int r) {
	    return board[6*(c-1)+r-1];
	  }
	  byte next() {
	    return moveState;
	  }
	}
