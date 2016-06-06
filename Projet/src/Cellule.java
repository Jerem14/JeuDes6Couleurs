import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

class Cellule{
	private static final int maxVoisins = 4;
	private int state;       
	private int prevState;    
	private int numVoisins; 
	private Cellule[] voisins = new Cellule[4];   
	private int r, c;               
	private Board game;
	
	Cellule(Board game, int r, int c) {
		prevState = state = 0;
		this.game = game;
		this.r = r;
		this.c = c;
		numVoisins = 0;
	    for (int i = 0; i < maxVoisins; i++) voisins[i] = null;
	}
	
	void addVoisin(Cellule voisin) {
	    voisins[numVoisins] = voisin;
	    numVoisins++;
	}
	
	void init(int value) {
		state = value;
	}
	
	int getState() {
	    return state;
	}
	
	int getPrevState() {
	    return prevState;
	}
	
	void notify(int change) {
	    notify(change, state);
	}

	private void notify(int current, int previous) {
	    setState(current);
	    notifyVoisins();
	}
	
	private void notifyVoisins() {
	    for (int i = 0; i < numVoisins; i++) {
	        if (voisins[i].getState() == prevState) {
	            voisins[i].notify(state, prevState);
	        }
	    }
	}
	
	private void setState(int change) {
	    prevState = state;
	    state = change;
		notifyGame();
	}
	
	private void notifyGame() {
	    game.notify(r,c,prevState,state);
	}
}
