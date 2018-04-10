package de.mannheim.fourwins;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

class GameGUI
extends  Frame
implements BoardObserver           
{
private GameControler gc;
private AdapterPlayer thePlayer;

private Label[] fields;
private Button column1;
private Button column2;
private Button column3;
private Button column4;
private Button column5;
private Button column6;
private Button column7;
private Button startGame;
private Button swapSides;
private Label  whosNext;

GameGUI( GameControler g, AdapterPlayer pl ) {
  gc = g;
  thePlayer = pl;

  addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit ( 0 );
      }
    } );
    
  setLayout( new BorderLayout() );
  Panel p = new Panel();
  p.setLayout( new GridLayout(7,7) );
  fields = new Label[42];
  for( int r = 6; r>=1; r-- ) {
    for( int c = 1; c<=7; c++ ) {
      Label lb = new Label("O",Label.CENTER);
      lb.setBackground( Color.gray );
      fields[6*(c-1)+r-1] = lb;
      p.add(lb);
    }
  }
  column1 = new GUIButton("1") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.one );
      } };
  column2 = new GUIButton("2") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.two );
      } };
  column3 = new GUIButton("3") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.thr );
      } };
  column4 = new GUIButton("4") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.fou );
      } };
  column5 = new GUIButton("5") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.fiv );
      } };
  column6 = new GUIButton("6") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.six );
      } };
  column7 = new GUIButton("7") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        thePlayer.notifyMove( MoveDescr.sev );
      } };
  setEnabledMoveButtons( false );
  p.add( column1 );
  p.add( column2 );
  p.add( column3 );
  p.add( column4 );
  p.add( column5 );
  p.add( column6 );
  p.add( column7 );
  add( p, "Center" );
  
  Panel bp = new Panel();
  startGame = new GUIButton("start") {
      public void actionPerformed( ActionEvent e ) {
        setEnabled( false );
        gc.startGame();
      } };
  swapSides = new GUIButton("swap") {
      public void actionPerformed( ActionEvent e ) {
        GameGUI.this.setEnabledMoveButtons( false );
        gc.swapSides();
        thePlayer.notifyMove( MoveDescr.dummy );
      } };
  bp.add( startGame );
  bp.add( swapSides );
  whosNext = new Label("white to play");
  bp.add( whosNext );
  add( bp, "South" );

  setSize(400,300);
  setLocation(100,200);
  setVisible( true );
}

void enableMove() {
  setEnabledMoveButtons( true );
}

public void stateChanged( ObservableBoard b ) {
  Position pos = b.currentPosition();
  for( int c = 1; c<=7; c++ ) {
    for( int r = 1; r<=6; r++ ) {
      switch( pos.get(c,r) ) {
      case Position.empty:
        fields[6*(c-1)+r-1].setBackground( Color.gray );
        break;
      case Position.white:
        fields[6*(c-1)+r-1].setBackground( Color.white );
        break;
      case Position.black:
        fields[6*(c-1)+r-1].setBackground( Color.blue );
        break;
      }
    }
  }
  switch( pos.next() ) {
  case Position.whiteNext:
    whosNext.setText("white to play");
    break;
  case Position.blackNext:
    whosNext.setText("blue to play");
    break;
  case Position.whiteWon:
    whosNext.setText("white won");
    break;
  case Position.blackWon:
    whosNext.setText("blue won");
  }
  repaint();
}

private void setEnabledMoveButtons( boolean b ) {
  column1.setEnabled( b );
  column2.setEnabled( b );
  column3.setEnabled( b );
  column4.setEnabled( b );
  column5.setEnabled( b );
  column6.setEnabled( b );
  column7.setEnabled( b );
  repaint();
}  

}