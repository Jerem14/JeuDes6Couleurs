import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

class Controleur {
	private JFrame mainFrame;
	private JLabel header;
	private JLabel rule;
	private JPanel selection;
	private Board game;
	private JLabel progress;
	private JLabel result;
	private Player Player1;
	private Player Player2;


	public Controleur(){
		
		this.game = new Board(this);
		this.Player1 = new Player();
		this.Player2 = new Player();
		nomPlayer();
		addElement();
		showSelection();
		game.init(4);
		}

	private void addElement(){
		//main Frame
		mainFrame = new JFrame("Le jeu des 6 couleurs");
		mainFrame.setSize(600,600);
		mainFrame.getContentPane().setBackground(Color.white);
		mainFrame.setLayout(new GridBagLayout());
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
				System.exit(0);
			}        
		});



		//header
		header = new JLabel("Le jeu des 6 couleurs", JLabel.CENTER);
		header.setFont(header.getFont().deriveFont(30f));

		//rule
		rule = new JLabel("Jouez !", JLabel.CENTER);

		//selection
		selection = new JPanel();
		selection.setLayout(new FlowLayout());

		//progress
		progress = new JLabel("", JLabel.CENTER);    

		//result
		result = new JLabel("", JLabel.CENTER);   

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.ipadx = 130;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		mainFrame.add(header, gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainFrame.add(rule,gbc);

		gbc.fill = GridBagConstraints.HORIZONTAL;  
		gbc.gridx = 0;
		gbc.gridy = 2;
		mainFrame.add(selection,gbc); 

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.ipady = 370;
		mainFrame.add(game, gbc);  

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 0;
		gbc.gridx = 0;
		gbc.gridy = 4;      
		mainFrame.add(progress, gbc);  

		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 5;
		mainFrame.add(result, gbc); 

		mainFrame.setVisible(true);  
	}
	
	public void nomPlayer(){
		JFrame frame = new JFrame("InputDialog Example #1");
		JFrame frame1 = new JFrame("InputDialog Example #2");
		Player1.name = JOptionPane.showInputDialog(frame, "Nom du premier Joueur","Entrez votre nom");
	    Player2.name = JOptionPane.showInputDialog(frame1, "Nom du deuxieme Joueur", "Entrez votre nom");
	    	
	}

	private void showSelection(){   
		// prompt the user to enter their name
		
		//color string 
		JLabel color = new JLabel("couleurs:");

		//color picker ComboBox
		final DefaultComboBoxModel colors = new DefaultComboBoxModel();
		final int [] choices = {4,5,6,7,8};
		for (int item : choices) colors.addElement(item);


		final JComboBox colorCombo = new JComboBox(colors);    
		colorCombo.setSelectedIndex(0); // 4 couleurs s�lectionn�es parmi les choix

		// "colorchoice" button
		JButton colorChoice = new JButton("Valider");

		colorChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				int index = colorCombo.getSelectedIndex();
				game.init(choices[index]);
			}
		}); 

		selection.add(color);   
		selection.add(colorCombo);       
		selection.add(colorChoice);  
		selection.setBackground(Color.WHITE);
		mainFrame.setVisible(true);             
	}

	void update(int moves, int limit, int win) {
		String str = null;
	
			if (moves%2==0){ progress.setText("C'est le tour de " + Player1.getName() + ". (Haut- Gauche)");
			}
			else progress.setText( "C'est le tour de "+ Player2.getName() + ". (Bas-Droite)");
		
			switch (win){
			case 1 : 
					str = Player1.getName() + " a gagné !";
					break;
			case 0 :
					str = Player2.getName() + " a gagné !";
					break;
					
			default : str ="";
			}
			
			
			if (win== 0 || win== 1){
				JOptionPane.showMessageDialog(null, str);
				System.exit(0);
				
			}
			
				
		}
	}
