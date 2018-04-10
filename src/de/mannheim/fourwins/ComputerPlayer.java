package de.mannheim.fourwins;

class ComputerPlayer 
implements Player
{
private ObservableBoard theBoard;
private int last = 3;

void putTheBoard( ObservableBoard b ) { theBoard = b; }

public MoveDescr draw() {
  int count = 0;
  last = (last+5)%7 +1;
  Position curpos = theBoard.currentPosition();
  try{ Thread.sleep( 2000 ); } catch( InterruptedException ie ) {}
  while( curpos.get(last,6) != Position.empty && count < 7 ) {
    last = (last+5)%7 +1;
    count ++;
  }
  switch( last ) {
  case 1: return MoveDescr.one;
  case 2: return MoveDescr.two;
  case 3: return MoveDescr.thr;
  case 4: return MoveDescr.fou;
  case 5: return MoveDescr.fiv;
  case 6: return MoveDescr.six;
  case 7: return MoveDescr.sev;
  default: return MoveDescr.dummy;
  }
}
}
