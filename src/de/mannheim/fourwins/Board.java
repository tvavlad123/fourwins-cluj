package de.mannheim.fourwins;

import java.util.ArrayList;
import java.util.Iterator;

class Board
implements ObservableBoard
{
private byte[] board = new byte[42];
private byte[] topincol = new byte[8];
private byte moveState = Position.whiteNext;
private ArrayList observers = new ArrayList();

private byte get(int c, int r) {
  return board[6*(c-1)+r-1];
}

public Position currentPosition() {
  return new Position(board,moveState);
}

MoveOutcome move( MoveDescr md ) {
  byte c = md.getCol();
  if(   moveState == Position.whiteWon
     || moveState == Position.blackWon
     || topincol[c] == 6 ) {
    return MoveOutcome.illegal;
  } else {
    topincol[c]++;
    byte colour = ( moveState == Position.whiteNext) ?
                    Position.white
                  : Position.black;
    board[ 6*(c-1) + topincol[c]-1 ] = colour;
    if( checkwin(colour) ) {
      if( colour == Position.white ) {
        moveState = Position.whiteWon;
      } else {
        moveState = Position.blackWon;
      }
      notifyObservers();
      return MoveOutcome.won;
    } else {
      if( colour == Position.white ) {
        moveState = Position.blackNext;
      } else {
        moveState = Position.whiteNext;
      }
      notifyObservers();
      return MoveOutcome.ok;
    }
  }
}

private void notifyObservers() {
  Iterator it = observers.iterator();
  while( it.hasNext() ) {
    BoardObserver obs = (BoardObserver)it.next();
    obs.stateChanged( this );
  }
}

public void register( BoardObserver obs ){
  observers.add(obs);
}

private boolean checkwin( byte colour ) {
  for( int ic = 1; ic <= 4; ic++ ) {
    for( int ir = 1; ir <= 3; ir++ ) {
      if(  (  get(ic,ir) == colour 
           && get(ic+1,ir) == colour 
           && get(ic+2,ir) == colour 
           && get(ic+3,ir) == colour )
        || (  get(ic,ir) == colour
           && get(ic,ir+1) == colour 
           && get(ic,ir+2) == colour 
           && get(ic,ir+3) == colour )
        || (  get(ic,ir) == colour
           && get(ic+1,ir+1) == colour 
           && get(ic+2,ir+2) == colour 
           && get(ic+3,ir+3) == colour )
           ) return true;
    }
  }
  for( int ic = 1; ic <= 4; ic++ ) {
    for( int ir = 4; ir <= 6; ir++ ) {
      if(  (  get(ic,ir) == colour 
           && get(ic+1,ir) == colour 
           && get(ic+2,ir) == colour 
           && get(ic+3,ir) == colour )
           ) return true;
    }
  }
  for( int ic = 5; ic <= 7; ic++ ) {
    for( int ir = 1; ir <= 3; ir++ ) {
      if(  (  get(ic,ir) == colour
           && get(ic,ir+1) == colour 
           && get(ic,ir+2) == colour 
           && get(ic,ir+3) == colour )
           ) return true;
    }
  }
  return false;
}

}
