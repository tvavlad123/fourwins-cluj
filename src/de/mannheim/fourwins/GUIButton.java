package de.mannheim.fourwins;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GUIButton
extends Button
implements ActionListener
{
// void klick() from extern

GUIButton( String s ) {
  super( s );
  addActionListener( this );
}
public void actionPerformed( ActionEvent e ) {}
}