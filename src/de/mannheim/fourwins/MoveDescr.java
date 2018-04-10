package de.mannheim.fourwins;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

class MoveDescr { // datatype
  private byte col; // 1-7

  private MoveDescr( int c ){ col = (byte)c; }
    
  static final MoveDescr one = new MoveDescr(1);
  static final MoveDescr two = new MoveDescr(2);
  static final MoveDescr thr = new MoveDescr(3);
  static final MoveDescr fou = new MoveDescr(4);
  static final MoveDescr fiv = new MoveDescr(5);
  static final MoveDescr six = new MoveDescr(6);
  static final MoveDescr sev = new MoveDescr(7);
  static final MoveDescr dummy = new MoveDescr(0);

  byte getCol() { return col; }
}