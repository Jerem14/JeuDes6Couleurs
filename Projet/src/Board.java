import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JComponent;

class Board extends JComponent {
	private static final int n = 18 ; //grille 18 par 18
	static final int side = 20;
	private static final int[] limit = {21, 26, 32, 37, 42};
	private static final int mininum_couleur = 4;
	private static final Color[] fill_couleurs = {
			Color.RED, 
			Color.YELLOW, 
			Color.BLUE,
			Color.GREEN, 
			Color.MAGENTA,
			Color.ORANGE,
			Color.PINK,
			Color.CYAN
			};
	
	
	
	private Cellule[][] laGrille;  
	private int num_couleurs;
	private int[] couleurs;
	private int moves;
	private Controleur c;
	private int win;
	
	Board(Controleur c) { 
		this.c = c;
		addMouseListener(new CustomMouseListener(this));
		laGrille = new Cellule[n][n];     
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) { 
				laGrille[i][j] = new Cellule(this, i, j);
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {  
				if (i-1 >= 0) laGrille[i][j].addVoisin(laGrille[i-1][j]);   
				if (i+1 < n) laGrille[i][j].addVoisin(laGrille[i+1][j]);
				if (j-1 >= 0) laGrille[i][j].addVoisin(laGrille[i][j-1]);
				if (j+1 < n) laGrille[i][j].addVoisin(laGrille[i][j+1]);
			}
		}
	}

	void init(int num_couleurs) {
		this.num_couleurs = num_couleurs;
		couleurs = new int[num_couleurs];  
		for (int i = 0; i < num_couleurs; i++) couleurs[i] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {  
				Random rand = new Random(); 
				int value = rand.nextInt(num_couleurs); 
				laGrille[i][j].init(value);
				couleurs[value]++;
			}
		}

		win = 2;
		moves = 0;
		showProgress();
	}

	int getState(int r, int c) {
		return laGrille[r][c].getState();
	}
	
	void notify(int r, int c, int oldState, int newState) {
		couleurs[oldState]--;
		couleurs[newState]++;
	}

	void change(int c) {
		if (moves%2 == 0)
		{
			if (c != laGrille[0][0].getState()) laGrille[0][0].notify(c);
		}
		else
		{
			if (c != laGrille[17][17].getState()) laGrille[17][17].notify(c);
		}
		moves++;
		showProgress();
	}
	
	private void showProgress() {
		repaint();
		isWon();
		c.update(moves, limit[num_couleurs - mininum_couleur], win);
	}

	private void isWon() {
		if (win == 2) {
			for (int i = 0; i < num_couleurs; i++) 
				if (couleurs[i] >= (n*n/2) && (moves%2==0)) {
					win = 0;
					return;
				}
				else if (couleurs[i] >= (n*n/2) && (moves%2!=0)) {
					win = 1;
					return;
					}
		}
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) { 
				int state = laGrille[i][j].getState();
				g.setColor(fill_couleurs[state]);
				g.fillRect(i*side, j*side, side, side);
			}
		}
	}
}
