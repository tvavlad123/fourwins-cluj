package de.mannheim.fourwins;

interface ObservableBoard
{
  void register( BoardObserver obs );
  Position currentPosition();
} // moves are not allowed
