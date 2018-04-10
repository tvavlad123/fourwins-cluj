package de.mannheim.fourwins;

class MoveOutcome { // datatype

	  private MoveOutcome(){}
	    
	  static final MoveOutcome won     = new MoveOutcome();
	  static final MoveOutcome ok      = new MoveOutcome();
	  static final MoveOutcome illegal = new MoveOutcome();
	}