package de.mannheim.fourwins;

public class FourWins
{
  public static void main(String argv[]) {
    
    Board theBoard = new Board();
    
    ComputerPlayer cp = new ComputerPlayer();
    cp.putTheBoard( theBoard );
    
    AdapterPlayer ap = new AdapterPlayer();
    
    GameControler gc = new GameControler();
    gc.putTheBoard( theBoard );
    gc.putPlayer( cp, false );
    gc.putPlayer( ap, true );
    
    GameGUI theGUI = new GameGUI(gc,ap);
    theBoard.register( theGUI );
    
    ap.putTheGUI(theGUI);
  }
}