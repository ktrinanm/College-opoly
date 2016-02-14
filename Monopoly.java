/*
*@author: Chris Wolf && Katrina Mehring
*
*The majority of the coding here was done by Chris, but I helped debug it at 3 in the morning.. Code Camp
*/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

public class Monopoly extends JFrame {
	
	final int possibleCards = 30; // 28 possible cards and 2 get out of jail free cards
	
	JFrame mainFrame; //holds everything
	
	JPanel mainPanel = new JPanel(); //mainPanel which we will put all the other panels on.
		JPanel cardPan = new JPanel(); // will store all the cards and who has them
			JLabel [] cardHolder = new JLabel[30]; // shows each card at the bottom
			Cards [] card = new Cards[30]; // All of the cards on the bottom
			JButton [] ownerHolder = new JButton[30]; // shows who owns the card above the card.
	JPanel boardPan; // will hold the game board
	JPanel userPan = new JPanel(); // will hold all the user accounts. Roll and Trade button?
	
	
	JLabel [] gameSpots = new JLabel[121];
	double startingMoney = 75; //1500 / 20
	int numPlayers; // how many people are in the game total
	int curPlayer = 1; // whos turn is it right now?
	
	//possible players
	Player player1;
	Player player2;
	Player player3;
	Player player4;
	
	Scanner input = new Scanner(System.in);
	ImageIcon ii = new ImageIcon(); //We will use this to create the pictures
	
	//("Money: $" + player1.getMoney() + " | Properties owned: " + player1.getPropertiesOwned());
	JLabel player1MoneyAndProperties;
	JLabel player2MoneyAndProperties;
	JLabel player3MoneyAndProperties;
	JLabel player4MoneyAndProperties;
	JLabel picHolder1 = new JLabel();
	JLabel picHolder2 = new JLabel();
	JLabel picHolder3 = new JLabel();
	JLabel picHolder4 = new JLabel(); 
	int p1Spot = 0;
	int p2Spot = 0;
	int p3Spot = 0;
	int p4Spot = 0;
	int p1Marker = -1;
	int p2Marker = -1;
	int p3Marker = -1;
	int p4Marker = -1;
	
	JLabel moneyAndProperties;

	JButton rollDiceBut;
	JButton tradeOrMortgageBut;
	JButton endTurnBut;
	
	int cardCheck = 0;
	int spot1;
	int spot2;
	
	public static void main(String [] args)	{
		Monopoly monopolyLauncher = new Monopoly();
	}
	
	public Monopoly()	{
		//Main Menu (We will make a screen for this)
		//mainMenu();
		//Create users (We will make a screen for this)
		//until we make a create player screen we'll just do it here.
		createUsers();
		//create a JFrame
		createFrame();
		//lets make the game board with our players on there
		initBoard();
		
	}
	
	//creates a simple JFrame
	public void createFrame()	{
		mainFrame = new JFrame("College-Opoly");
		mainFrame.setSize(1350,725);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

		System.out.println(player1.getPicture());
		System.out.println(player2.getPicture());
	}
	
	//Create users (We will make a screen for this)
	public void createUsers()	{
		Object[] options = {"2 Player",
                "3 Player",
                "4 Player"};
		int n = JOptionPane.showOptionDialog(mainFrame,
				"How many Players will be playing?",
				"Player",
				JOptionPane.YES_NO_CANCEL_OPTION,
JOptionPane.QUESTION_MESSAGE,
null,
options,
options[2]);
		if(n == 0)
		{
			numPlayers =2;
		}
		else if( n== 1)
		{
			numPlayers = 3;

		}
		else
		{
			numPlayers = 4;
		}
		
		
		String name;
		ii = new ImageIcon(Monopoly.class.getResource("straight0.png"));
		if(numPlayers >= 1)	{
			name = "Player1";
			player1 = new Player(name, startingMoney, ii); //we will add using picture
		}
		if(numPlayers >= 2)	{
			name =  "Player2";
			player2 = new Player(name, startingMoney, ii); //we will add using picture
		}
		if(numPlayers >= 3)	{
			name =  "Player3";
			player3 = new Player(name, startingMoney, ii); //we will add using picture
		}
		if(numPlayers >= 4)	{
			name =  "Player4";
			player4 = new Player(name, startingMoney, ii); //we will add using picture
		}
	}
	
	public void initBoard()	{
		mainFrame.add(mainPanel); //adding a panel to out main frame so we can apply layouts
		mainPanel.validate();
		mainPanel.setLayout(new BorderLayout()); // creates a borderlayout so we can place out panels in the correct spots
		createBottom();
		createRight();
		createGameBoard();
	}
	
	public void createBottom(){
		cardPan.setLayout(new GridLayout(2, possibleCards /2));
		//adding all the cards to the bottom of screen 
		JPanel cur;
		for(int i = 0; i < possibleCards; i++)	{
			cur = new JPanel();
			cur.setLayout(new BorderLayout());
			//create a card to place
			card[i] = new Cards(i);
			cardHolder[i] = new JLabel();
			mainFrame.validate();
			//create image on bottom and scale it.
			ii = card[i].getPicture();
			cardHolder[i].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/14, mainPanel.getHeight()/14,
				       java.awt.Image.SCALE_SMOOTH)));
			//add owner of each card to bottom
			ownerHolder[i] = new JButton("No Owner");
			ownerHolder[i].setFont(new Font("Arial", Font.PLAIN, 12));
			ownerHolder[i].setBackground(Color.LIGHT_GRAY);
			ownerHolder[i].setForeground(Color.BLACK);
			cur.add(BorderLayout.CENTER, cardHolder[i]);
			cur.add(BorderLayout.NORTH, ownerHolder[i]);
			cardPan.add(cur);
		}
		
		mainPanel.add(BorderLayout.SOUTH, cardPan);
		mainFrame.validate();
	}
	
	public void createRight(){
		//four users in a grid layout and then some options on the bottom.
		userPan.setLayout(new BorderLayout());
		userPan.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		userPan.setBackground(Color.LIGHT_GRAY);
		
		//the three options on the bottom
		JPanel optionsPan = new JPanel();
		optionsPan.setLayout(new GridLayout(1, 3));
		rollDiceBut = new JButton("Roll Dice");
		rollDiceBut.setBackground(Color.GREEN);
		rollDiceBut.addActionListener(new diceListener());
		tradeOrMortgageBut = new JButton("Trade/Mortgage");
		tradeOrMortgageBut.setBackground(Color.YELLOW);
		tradeOrMortgageBut.addActionListener(new tradeListener());
		endTurnBut = new JButton("End Turn");
		endTurnBut.setBackground(Color.ORANGE);
		endTurnBut.setEnabled(false);
		endTurnBut.addActionListener(new endTurnListener());
		optionsPan.add(rollDiceBut);
		optionsPan.add(tradeOrMortgageBut);
		optionsPan.add(endTurnBut);
		userPan.add(BorderLayout.SOUTH, optionsPan);
		
		//to seperate users into 4 sections
		JPanel playersPan = new JPanel();

		playersPan.setLayout(new GridLayout(2,2));
		
		userPan.add(BorderLayout.CENTER, playersPan);
		//creating user info
		for(int i = 1; i <= numPlayers; i++)	{
			JPanel pan = new JPanel();
			pan.setBackground(Color.WHITE);
			pan.setLayout(new BorderLayout());
			JLabel name = new JLabel();
			moneyAndProperties = new JLabel();
			if(i == 1)	{
				ii = player1.getPicture();
				picHolder1.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
				java.awt.Image.SCALE_SMOOTH)));	
				picHolder1.setBorder(BorderFactory.createLineBorder(Color.black));
				pan.add(picHolder1, BorderLayout.CENTER);
				pan.setBorder(BorderFactory.createTitledBorder("Player 1"));
				name.setText(player1.getName());
				moneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());
				player1MoneyAndProperties = moneyAndProperties;
			}

			if(i == 2)	{
				ii = player2.getPicture();
				picHolder2.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
				java.awt.Image.SCALE_SMOOTH)));	
				picHolder2.setBorder(BorderFactory.createLineBorder(Color.black));
				pan.add(picHolder2, BorderLayout.CENTER);
				pan.setBorder(BorderFactory.createTitledBorder("Player 2"));
				name.setText(player2.getName());
				moneyAndProperties.setText("Money: $" + player2.getMoney() + " | Properties: " + player2.getPropertiesOwned());
				player2MoneyAndProperties = moneyAndProperties;
			}
			
			if(i == 3)	{
				ii = player3.getPicture();
				picHolder3.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
				java.awt.Image.SCALE_SMOOTH)));	
				picHolder3.setBorder(BorderFactory.createLineBorder(Color.black));
				pan.add(picHolder3, BorderLayout.CENTER);
				name.setText(player3.getName());
				pan.setBorder(BorderFactory.createTitledBorder("Player 3"));
				moneyAndProperties.setText("Money: $" + player3.getMoney() + " | Properties: " + player3.getPropertiesOwned());
				player3MoneyAndProperties = moneyAndProperties;
			}
			
			if(i == 4)	{
				ii = player4.getPicture();
				picHolder4.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
				java.awt.Image.SCALE_SMOOTH)));	
				picHolder4.setBorder(BorderFactory.createLineBorder(Color.black));
				pan.add(picHolder4, BorderLayout.CENTER);
				pan.setBorder(BorderFactory.createTitledBorder("Player 4"));
				name.setText(player4.getName());
				moneyAndProperties.setText("Money: $" + player4.getMoney() + " | Properties: " + player4.getPropertiesOwned());
				player4MoneyAndProperties = moneyAndProperties;
			}			
		
			
			name.setHorizontalAlignment(SwingConstants.CENTER);
			name.setFont(new Font("Arial", Font.BOLD, 17));
			pan.add(name, BorderLayout.NORTH);
			moneyAndProperties.setHorizontalAlignment(SwingConstants.CENTER);
			moneyAndProperties.setFont(new Font("Arial", Font.PLAIN, 13));
			pan.add(BorderLayout.SOUTH, moneyAndProperties);
			playersPan.add(pan);
		}
		
		mainPanel.add(BorderLayout.EAST, userPan);
		mainFrame.validate();
	}
	
	public void createGameBoard()	{
		boardPan = new JPanel();
		mainPanel.add(BorderLayout.CENTER, boardPan);
		boardPan.setBackground(Color.LIGHT_GRAY);
		boardPan.setLayout(new GridLayout(11,11));
		drawBoard();
		mainFrame.validate();
	}
	
	public void drawBoard()	{
		boolean flag;// checks if a picture goes in current cell
		for(int i = 0; i < 121; i++)	{
			flag = false;
			JLabel holder = new JLabel();
			switch(i){
				case 120: ii = new ImageIcon(Monopoly.class.getResource("Slide29.PNG")); flag = true; break;
				case 119: ii = new ImageIcon(Monopoly.class.getResource("Slide1.PNG")); flag = true; break;
				case 118: ii = new ImageIcon(Monopoly.class.getResource("Slide36.PNG")); flag = true; break;
				case 117: ii = new ImageIcon(Monopoly.class.getResource("Slide2.PNG")); flag = true; break;
				case 116: ii = new ImageIcon(Monopoly.class.getResource("Slide38.PNG")); flag = true; break;
				case 115: ii = new ImageIcon(Monopoly.class.getResource("Slide3.PNG")); flag = true; break;
				case 114: ii = new ImageIcon(Monopoly.class.getResource("Slide4.PNG")); flag = true; break;
				case 113: ii = new ImageIcon(Monopoly.class.getResource("Slide33.PNG")); flag = true; break;
				case 112: ii = new ImageIcon(Monopoly.class.getResource("Slide5.PNG")); flag = true; break;
				case 111: ii = new ImageIcon(Monopoly.class.getResource("Slide6.PNG")); flag = true; break;
				case 110: ii = new ImageIcon(Monopoly.class.getResource("Slide30.PNG")); flag = true; break;
				case 99: ii = new ImageIcon(Monopoly.class.getResource("Slide7.PNG")); flag = true; break;
				case 88: ii = new ImageIcon(Monopoly.class.getResource("Slide8.PNG")); flag = true; break;
				case 77: ii = new ImageIcon(Monopoly.class.getResource("Slide9.PNG")); flag = true; break;				
				case 66: ii = new ImageIcon(Monopoly.class.getResource("Slide10.PNG")); flag = true; break;
				case 55: ii = new ImageIcon(Monopoly.class.getResource("Slide11.PNG")); flag = true; break;
				case 44: ii = new ImageIcon(Monopoly.class.getResource("Slide12.PNG")); flag = true; break;
				case 33: ii = new ImageIcon(Monopoly.class.getResource("Slide40.PNG")); flag = true; break;
				case 22: ii = new ImageIcon(Monopoly.class.getResource("Slide13.PNG")); flag = true; break;
				case 11: ii = new ImageIcon(Monopoly.class.getResource("Slide14.PNG")); flag = true; break;
				case 0: ii = new ImageIcon(Monopoly.class.getResource("Slide31.PNG")); flag = true; break;
				case 1: ii = new ImageIcon(Monopoly.class.getResource("Slide15.PNG")); flag = true; break;
				case 2: ii = new ImageIcon(Monopoly.class.getResource("Slide35.PNG")); flag = true; break;
				case 3: ii = new ImageIcon(Monopoly.class.getResource("Slide16.PNG")); flag = true; break;
				case 4: ii = new ImageIcon(Monopoly.class.getResource("Slide17.PNG")); flag = true; break;
				case 5: ii = new ImageIcon(Monopoly.class.getResource("Slide18.PNG")); flag = true; break;
				case 6: ii = new ImageIcon(Monopoly.class.getResource("Slide19.PNG")); flag = true; break;
				case 7: ii = new ImageIcon(Monopoly.class.getResource("Slide20.PNG")); flag = true; break;
				case 8: ii = new ImageIcon(Monopoly.class.getResource("Slide21.PNG")); flag = true; break;
				case 9: ii = new ImageIcon(Monopoly.class.getResource("Slide22.PNG")); flag = true; break;
				case 10: ii = new ImageIcon(Monopoly.class.getResource("Slide32.PNG")); flag = true; break;
				case 21: ii = new ImageIcon(Monopoly.class.getResource("Slide23.PNG")); flag = true; break;
				case 32: ii = new ImageIcon(Monopoly.class.getResource("Slide24.PNG")); flag = true; break;
				case 43: ii = new ImageIcon(Monopoly.class.getResource("Slide39.PNG")); flag = true; break;
				case 54: ii = new ImageIcon(Monopoly.class.getResource("Slide25.PNG")); flag = true; break;
				case 65: ii = new ImageIcon(Monopoly.class.getResource("Slide26.PNG")); flag = true; break;
				case 76: ii = new ImageIcon(Monopoly.class.getResource("Slide34.PNG")); flag = true; break;
				case 87: ii = new ImageIcon(Monopoly.class.getResource("Slide27.PNG")); flag = true; break;
				case 98: ii = new ImageIcon(Monopoly.class.getResource("Slide37.PNG")); flag = true; break;
				case 109: ii = new ImageIcon(Monopoly.class.getResource("Slide28.PNG")); flag = true; break;
			}
			if(flag == true)	{
				holder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
				holder.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/16, mainPanel.getHeight()/13,
				       java.awt.Image.SCALE_SMOOTH)));
			}
			gameSpots[i] = holder;
			boardPan.add(holder);

		}
		
	}
	public class diceListener implements ActionListener	{
		public void actionPerformed(ActionEvent e)	{		
			System.out.println("Rolling Dice");
			rollDiceBut.setBackground(Color.RED);
			rollDiceBut.setEnabled(false);
			int roll1 = (int)(Math.random() * ((6 - 1) + 1) + 1);
			int roll2 = (int)(Math.random() * ((6 - 1) + 1) + 1);
			
			ii = new ImageIcon(Monopoly.class.getResource("dice" + roll1 + ".png"));
			JLabel ima = new JLabel();
			int diff1 = (int)(Math.random() * ((4 - 1) + 1) + 1);
			if(diff1 == 2) diff1 += 11;
			int diff2 = (int)(Math.random() * ((3 - 1) + 1) + 1);
			if(diff2 == 2) diff2 -= 12;
			
			spot1 = 56+diff1;
			spot2 = 60+diff2;
			gameSpots[56 + diff1].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/16, mainPanel.getHeight()/16, java.awt.Image.SCALE_SMOOTH)));
			
			ii = new ImageIcon(Monopoly.class.getResource("dice" + roll2 + ".png"));
			JLabel ima2 = new JLabel();
			ima2.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/12, mainPanel.getHeight()/12,
						java.awt.Image.SCALE_SMOOTH)));
			gameSpots[60 + diff2].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/14, mainPanel.getHeight()/14,
					java.awt.Image.SCALE_SMOOTH)));
			
			int roll = roll1 + roll2;
			System.out.println(roll);
			if(curPlayer == 1)	{
				p1Spot += roll;
				if(p1Spot > 39)	{
					p1Spot -=39; 
					System.out.println("You passed go!");
				}
				ii = new ImageIcon(Monopoly.class.getResource("straight" + p1Spot + ".png"));
				picHolder1.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
						java.awt.Image.SCALE_SMOOTH)));	
				if(p1Marker == -1)	{
					p1Marker = 0;
				}	else	{
					if(p2Marker != p1Marker && p3Marker != p1Marker && p4Marker != p1Marker){
						gameSpots[p1Marker].setIcon(null);
					}
				}
				p1Marker = movePlayer(p1Spot);
				ii = new ImageIcon(Monopoly.class.getResource("player1.png"));
				if(gameSpots[p1Marker] == null)	{
					JLabel image = new JLabel();
					image.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/12, mainPanel.getHeight()/12,
							java.awt.Image.SCALE_SMOOTH)));
					gameSpots[p1Marker].add(BorderLayout.SOUTH, image);
				}	else	{
				gameSpots[p1Marker].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/9, mainPanel.getHeight()/9,
						java.awt.Image.SCALE_SMOOTH)));	
				}
				
			}
			if(curPlayer == 2)	{
				p2Spot += roll;
				if(p2Spot > 39)	{
					p2Spot -= 39; 
					System.out.println("You passed go!");
					
				}
				ii = new ImageIcon(Monopoly.class.getResource("straight" + p2Spot + ".png"));
				picHolder2.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
						java.awt.Image.SCALE_SMOOTH)));	
				if(p2Marker == -1)	{
					p2Marker = 0;
				}	else	{
					if(p1Marker != p2Marker && p3Marker != p2Marker && p4Marker != p2Marker){
						gameSpots[p2Marker].setIcon(null);
					}
				}
				p2Marker = movePlayer(p2Spot);
				ii = new ImageIcon(Monopoly.class.getResource("player2.png"));
				if(gameSpots[p2Marker] == null)	{
					JLabel image = new JLabel();
					image.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/12, mainPanel.getHeight()/12,
							java.awt.Image.SCALE_SMOOTH)));
					gameSpots[p2Marker].add(BorderLayout.SOUTH, image);
				}	else	{
				gameSpots[p2Marker].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/11, mainPanel.getHeight()/11,
						java.awt.Image.SCALE_SMOOTH)));	
				}
			}
			if(curPlayer == 3)	{
				p3Spot += roll;
				if(p3Spot > 39)	{
					p3Spot -= 39; 
					System.out.println("You passed go!");
				}
				ii = new ImageIcon(Monopoly.class.getResource("straight" + p3Spot + ".png"));
				picHolder3.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
						java.awt.Image.SCALE_SMOOTH)));	
				if(p3Marker == -1)	{
					p3Marker = 0;
				}	else	{
					if(p1Marker != p3Marker && p2Marker != p3Marker && p4Marker != p3Marker){
						gameSpots[p3Marker].setIcon(null);
					}
				}
				p3Marker = movePlayer(p3Spot);
				ii = new ImageIcon(Monopoly.class.getResource("player3.png"));
				if(gameSpots[p3Marker] == null)	{
					JLabel image = new JLabel();
					image.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/12, mainPanel.getHeight()/12,
							java.awt.Image.SCALE_SMOOTH)));
					gameSpots[p3Marker].add(BorderLayout.SOUTH, image);
				}	else	{
					gameSpots[p3Marker].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/12, mainPanel.getHeight()/12,
							java.awt.Image.SCALE_SMOOTH)));	
				}
			}
			if(curPlayer == 4)	{
				p4Spot += roll;
				if(p4Spot > 39)	{
					p4Spot -= 39; 
					System.out.println("You passed go!");
				}
				ii = new ImageIcon(Monopoly.class.getResource("straight" + p4Spot + ".png"));
				picHolder4.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
						java.awt.Image.SCALE_SMOOTH)));	
				if(p4Marker == -1)	{
					p4Marker = 0;
				}	else	{
					if(p1Marker != p4Marker && p2Marker != p4Marker && p3Marker != p4Marker){
						gameSpots[p4Marker].setIcon(null);
					} 	
				}
				p4Marker = movePlayer(p4Spot);
				ii = new ImageIcon(Monopoly.class.getResource("player4.png"));
				if(gameSpots[p4Marker] == null)	{
					JLabel image = new JLabel();
					image.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/12, mainPanel.getHeight()/12,
							java.awt.Image.SCALE_SMOOTH)));
					gameSpots[p4Marker].add(BorderLayout.SOUTH, image);
				}	else	{
				gameSpots[p4Marker].setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/14, mainPanel.getHeight()/14,
						java.awt.Image.SCALE_SMOOTH)));	
				}
			}
			
			endTurnBut.setEnabled(true);
			endTurnBut.setBackground(Color.ORANGE);
			mainFrame.repaint();
			mainFrame.validate();
			locationCheck();
		}

		private int movePlayer(int spot) {
			switch(spot)	{
				case 1: return 108;
				case 2: return 107;
				case 3: return 106;
				case 4: return 105;
				case 5: return 104;
				case 6: return 103;
				case 7: return 102;
				case 8: return 101;
				case 9: return 100;
				case 10: return 100;
				case 11: return 100;
				case 12: return 89;
				case 13: return 78;
				case 14: return 67;
				case 15: return 56;
				case 16: return 45;
				case 17: return 34;
				case 18: return 23;
				case 19: return 12;
				case 20: return 12;
				case 21: return 12;
				case 22: return 13;
				case 23: return 14;
				case 24: return 15;
				case 25: return 16;
				case 26: return 17;
				case 27: return 18;
				case 28: return 19;
				case 29: return 20;
				case 30: return 20;
				case 31: return 20;
				case 32: return 31;
				case 33: return 42;
				case 34: return 53;
				case 35: return 64;
				case 36: return 75;
				case 37: return 86;
				case 38: return 97;
				case 39: return 108;
				case 40: return 108;
				default: System.out.println("woops"); break;
			}
			return 0;
		}
	}
	public class tradeListener implements ActionListener	{
		public void actionPerformed(ActionEvent e)	{		
			System.out.println("Trading or Mortgaging mode");
		}
	}
	public class endTurnListener implements ActionListener	{
		public void actionPerformed(ActionEvent e)	{		
			System.out.println("Ending Turn");
			rollDiceBut.setEnabled(true);
			rollDiceBut.setBackground(Color.GREEN);
			endTurnBut.setEnabled(false);
			endTurnBut.setBackground(Color.RED);
			curPlayer+=1;
			if(curPlayer > numPlayers)	{
				curPlayer = 1;
			}
			System.out.println("Player " + curPlayer + " turn!");
			gameSpots[spot1].setIcon(null);
			gameSpots[spot2].setIcon(null);
		}
	}
	int num;
	
	public void locationCheck()	{
		num = 0;
		if(curPlayer == 1)	{
			num = p1Spot;
		}
		if(curPlayer == 2)	{
			num = p2Spot;
		}
		if(curPlayer == 3)	{
			num = p3Spot;
		}
		if(curPlayer == 4)	{
			num = p4Spot;
		}
		System.out.println(num);
		
		Cards curCard;
		boolean owned = true;
		boolean chance = false;
		boolean cantBuy = false;
		if(num == 1 || num == 3 || num == 5 || num == 6 || num == 8 || num == 9 || num == 11 || num == 12 || num == 13 
				|| num == 14 || num == 15 || num == 16 || num == 18 || num == 19 || num == 21 || num == 23 || num == 24
				|| num == 25 || num == 26 || num == 27 || num == 28 || num == 29 || num == 31 || num == 32 || num == 34 
				|| num == 35 || num == 37 || num == 39);
			if(num == 1)	{
				checkCard(card[0], 0);
			} if(num == 3)	{
				checkCard(card[1], 1);
			} if(num == 5)	{
				checkCard(card[24], 24);
			} if(num == 6)	{
				checkCard(card[2], 2);
			} if(num == 8)	{
				checkCard(card[3], 3);
			} if(num == 9)	{
				checkCard(card[4], 4);
			} if(num == 11)	{
				checkCard(card[5], 5);
			} if(num == 12)	{
				checkCard(card[22], 22);
			} if(num == 13)	{
				checkCard(card[6], 6);
			} if(num == 14)	{
				checkCard(card[7], 7);
			} if(num == 15)	{
				checkCard(card[25], 25);
			} if(num == 16)	{
				checkCard(card[8], 8);
			} if(num == 18)	{
				checkCard(card[9], 9);
			} if(num == 19)	{
				checkCard(card[10], 10);
			} if(num == 21)	{
				checkCard(card[11],11);
			} if(num == 23)	{
				checkCard(card[12], 12);
			} if(num == 24)	{
				checkCard(card[13] , 13);
			}if(num == 25)	{
				checkCard(card[26] , 26);
			} if(num == 26)	{
				checkCard(card[14] , 14);
			} if(num == 27)	{
				checkCard(card[15], 15);
			} if(num == 28)	{
				checkCard(card[23], 23);
			} if(num == 29)	{
				checkCard(card[16], 16);
			} if(num == 31)	{
				checkCard(card[17], 17);
			} if(num == 32)	{
				checkCard(card[18], 18);
			} if(num == 34)	{
				checkCard(card[19], 19);
			} if(num == 35)	{
				checkCard(card[27], 27);
			} if(num == 37)	{
				checkCard(card[20], 20);
			} if(num == 39)	{
				checkCard(card[21], 21);
			
		}	else if(num == 2 || num == 17 || num == 33)	{
			foundSomething();
		}	else if(num == 7 || num == 22 || num == 36){
			chance();
		}	else if(num == 4)	{
			payFee(10);
		}	else if(num == 38)	{
			payFee(5);
		}	else if (num == 20)	{
			freeMoney();
		}	else if (num == 30)	{
			goToJail();
		}
		
	}	
		
			
			//askToBuy(card[1]);	
			//chargeUser(card[1]);
		
		private void checkCard(Cards cards, int cardNum) {
			System.out.println("Card: " + cards.getCardName());
			System.out.println(cards.isOwned());
			if(cards.isOwned() == false)	{
				System.out.println("Test");
				int n = JOptionPane.showConfirmDialog(
					    mainFrame,
					    "This will cost you $" + cards.getCost() + ". Would you like to buy it?",
					    "Would you like to buy " + cards.getCardName() + "?",
					    JOptionPane.YES_NO_OPTION);
				System.out.println("cur n" + n);
				System.out.println("The current player is " + curPlayer);
				if(n == 0)	{
					if(curPlayer == 1){
						System.out.println("1");
						player1.setMoney(player1.getMoney() - cards.getCost());
						ownerHolder[cardNum].setText("" + player1.getName());
						player1.propertiesOwned ++;
				}
					else if(curPlayer == 2){
						System.out.println("2");
						player2.setMoney(player2.getMoney() - cards.getCost());
						ownerHolder[cardNum].setText("" + player2.getName());
						player2.propertiesOwned ++;
					}
					else if(curPlayer == 3){
						System.out.println("3");
						player3.setMoney(player3.getMoney() - cards.getCost());
						ownerHolder[cardNum].setText("" + player3.getName());
						player3.propertiesOwned ++;
					}
					else if(curPlayer == 4){
						System.out.println("4");
						player4.setMoney(player4.getMoney() - cards.getCost());
						ownerHolder[cardNum].setText("" + player4.getName());
						player4.propertiesOwned ++;
					}
					cards.setOwned(true);
				}			
			}	else	{
				JOptionPane.showMessageDialog(mainFrame,"You landed on someone elses property and must pay them $" + cards.getCostToLandOn());
				if(curPlayer == 1)
					player1.setMoney(player1.getMoney() - cards.getCostToLandOn());
				else if(curPlayer == 2)
					player2.setMoney(player2.getMoney() - cards.getCostToLandOn());
				else if(curPlayer == 3)
					player3.setMoney(player3.getMoney() - cards.getCostToLandOn());
				else if(curPlayer == 4)
					player4.setMoney(player4.getMoney() - cards.getCostToLandOn());
			}
			if(curPlayer == 1)	{
				player1MoneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());	
			}
			if(curPlayer == 2)	{
				player2MoneyAndProperties.setText("Money: $" + player2.getMoney() + " | Properties: " + player2.getPropertiesOwned());	
			}
			if(curPlayer == 3)	{
				player3MoneyAndProperties.setText("Money: $" + player3.getMoney() + " | Properties: " + player3.getPropertiesOwned());	
			}
			if(curPlayer == 4)	{
				player4MoneyAndProperties.setText("Money: $" + player4.getMoney() + " | Properties: " + player4.getPropertiesOwned());	
			}
			mainFrame.validate();
		}



	private void goToJail() {
		// TODO Auto-generated method stub
		
	}

	private void freeMoney() {
		// TODO Auto-generated method stub
		
	}

	private void payFee(int i) {
		JOptionPane.showMessageDialog(mainFrame, "You pay $" + i);
		if(curPlayer == 1)	{
			player2.setMoney(player1.getMoney() - i);
			player1MoneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());	
		}
		else if(curPlayer == 2)	{
			player2.setMoney(player2.getMoney() - i);
			player2MoneyAndProperties.setText("Money: $" + player2.getMoney() + " | Properties: " + player2.getPropertiesOwned());	
		}
		else if(curPlayer == 3)	{
			player3.setMoney(player3.getMoney() - i);
			player3MoneyAndProperties.setText("Money: $" + player3.getMoney() + " | Properties: " + player3.getPropertiesOwned());	
		}
		else if(curPlayer == 4)	{
			player4.setMoney(player4.getMoney() - i);
			player4MoneyAndProperties.setText("Money: $" + player4.getMoney() + " | Properties: " + player4.getPropertiesOwned());	
		}
	}

	private void chance() {	
		num = 0;
		if(curPlayer == 1)	{
			num = p1Spot;
		}
		if(curPlayer == 2)	{
			num = p2Spot;
		}
		if(curPlayer == 3)	{
			num = p3Spot;
		}
		if(curPlayer == 4)	{
			num = p4Spot;
		}
		
		int rand = (int)(Math.random() * ((16 - 1) + 1) + 1);
		ii = new ImageIcon(Monopoly.class.getResource("c" + rand + ".png"));
		if(curPlayer == 1)	{
			picHolder1.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
					java.awt.Image.SCALE_SMOOTH)));	
			switch(rand)	{
				case 1: p1Spot = 0; player1.setMoney(player1.getMoney() + 10); break;
				case 2: player1.setMoney(player1.getMoney() + 3); break;
				case 3: if( p1Spot > 4)	{ p1Spot -= 3;} break;
				case 4: if (p1Spot < 12 || p1Spot > 28) { 
							p1Spot = 12;
						}	else	{
							p1Spot = 28; 
						} break;
				case 5: p1Spot = 30; break;
				case 6: player1.setMoney(player1.getMoney() - .5); break;
				case 7: if(p1Spot > 17)	{
					player1.setMoney(player1.getMoney() + 10);
				} p1Spot = 17;   break;
				case 8: player1.setMoney((player1.getMoney() - (numPlayers * 2.5)) + 2.5); player2.setMoney(player2.getMoney() + 2.5);
				if(numPlayers > 2)	{player3.setMoney(player3.getMoney() + 2.5);} if(numPlayers >3)	{ player4.setMoney(player4.getMoney() + 2.5);} break;
				case 9: if (p1Spot <= 5 || p1Spot > 35)	{
					p1Spot = 5;
				}	if (p1Spot > 5 && p1Spot <= 15)	{
					p1Spot = 15;
				}	if (p1Spot > 15 && p1Spot <=25)	{
					p1Spot = 25;
				}	else if (p1Spot > 25 && p1Spot <= 35){
					p1Spot = 35;
				} break;
				case 10: if (p1Spot <= 5 || p1Spot > 35)	{
					p1Spot = 5;
				}	if (p1Spot > 5 && p1Spot <= 15)	{
					p1Spot = 15;
				}	if (p1Spot > 15 && p1Spot <=25)	{
					p1Spot = 25;
				}	else if (p1Spot > 25 && p1Spot <= 35){
					p1Spot = 35;
				} break;
				case 11: if(p1Spot > 5)	{
					player1.setMoney(player1.getMoney() + 10);
				} p1Spot = 5; break;
				case 12: p1Spot = 24; break;
				case 13: player1.setMoney(player1.getMoney() - 5); break;
				case 14: p1Spot = 39; break;
				case 15: player1.setMoney(player1.getMoney() + 7.5); break;
				case 16: ownerHolder[29].setText("" + player1.getName()); player1.propertiesOwned ++; break;
			}
			if(curPlayer == 2)	{
				picHolder1.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
						java.awt.Image.SCALE_SMOOTH)));	
				switch(rand)	{
				case 1: p2Spot = 0; player1.setMoney(player1.getMoney() + 10); break;
				case 2: player1.setMoney(player1.getMoney() + 3); break;
				case 3: if( p2Spot > 4)	{ p2Spot -= 3;} break;
				case 4: if (p2Spot < 12 || p2Spot > 28) { 
							p2Spot = 12;
						}	else	{
							p2Spot = 28; 
						} break;
				case 5: p2Spot = 30; break;
				case 6: player1.setMoney(player1.getMoney() - .5); break;
				case 7: if(p2Spot > 17)	{
					player1.setMoney(player1.getMoney() + 10);
				} p2Spot = 17;   break;
				case 8: player1.setMoney((player1.getMoney() - (numPlayers * 2.5)) + 2.5); player2.setMoney(player2.getMoney() + 2.5);
				if(numPlayers > 2)	{player3.setMoney(player3.getMoney() + 2.5);} if(numPlayers >3)	{ player4.setMoney(player4.getMoney() + 2.5);} break;
				case 9: if (p2Spot <= 5 || p2Spot > 35)	{
					p2Spot = 5;
				}	if (p2Spot > 5 && p2Spot <= 15)	{
					p2Spot = 15;
				}	if (p2Spot > 15 && p2Spot <=25)	{
					p2Spot = 25;
				}	else if (p2Spot > 25 && p2Spot <= 35){
					p2Spot = 35;
				} break;
				case 10: if (p2Spot <= 5 || p2Spot > 35)	{
					p2Spot = 5;
				}	if (p2Spot > 5 && p2Spot <= 15)	{
					p2Spot = 15;
				}	if (p2Spot > 15 && p2Spot <=25)	{
					p2Spot = 25;
				}	else if (p2Spot > 25 && p2Spot <= 35){
					p2Spot = 35;
				} break;
				case 11: if(p2Spot > 5)	{
					player1.setMoney(player1.getMoney() + 10);
				} p2Spot = 5; break;
				case 12: p2Spot = 24; break;
				case 13: player1.setMoney(player1.getMoney() - 5); break;
				case 14: p2Spot = 39; break;
				case 15: player1.setMoney(player1.getMoney() + 7.5); break;
				case 16: ownerHolder[29].setText("" + player1.getName()); player1.propertiesOwned ++; break;
			}
			}
			player1MoneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());	
			player2MoneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());	
			if(numPlayers > 2)	{
				if(curPlayer == 1)	{
					picHolder1.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
							java.awt.Image.SCALE_SMOOTH)));	
					switch(rand)	{
					case 1: p3Spot = 0; player1.setMoney(player1.getMoney() + 10); break;
					case 2: player1.setMoney(player1.getMoney() + 3); break;
					case 3: if( p3Spot > 4)	{ p3Spot -= 3;} break;
					case 4: if (p3Spot < 12 || p3Spot > 28) { 
								p3Spot = 12;
							}	else	{
								p3Spot = 28; 
							} break;
					case 5: p3Spot = 30; break;
					case 6: player1.setMoney(player1.getMoney() - .5); break;
					case 7: if(p3Spot > 17)	{
						player1.setMoney(player1.getMoney() + 10);
					} p3Spot = 17;   break;
					case 8: player1.setMoney((player1.getMoney() - (numPlayers * 2.5)) + 2.5); player2.setMoney(player2.getMoney() + 2.5);
					if(numPlayers > 2)	{player3.setMoney(player3.getMoney() + 2.5);} if(numPlayers >3)	{ player4.setMoney(player4.getMoney() + 2.5);} break;
					case 9: if (p3Spot <= 5 || p3Spot > 35)	{
						p3Spot = 5;
					}	if (p3Spot > 5 && p3Spot <= 15)	{
						p3Spot = 15;
					}	if (p3Spot > 15 && p3Spot <=25)	{
						p3Spot = 25;
					}	else if (p3Spot > 25 && p3Spot <= 35){
						p3Spot = 35;
					} break;
					case 10: if (p3Spot <= 5 || p3Spot > 35)	{
						p3Spot = 5;
					}	if (p3Spot > 5 && p3Spot <= 15)	{
						p3Spot = 15;
					}	if (p3Spot > 15 && p3Spot <=25)	{
						p3Spot = 25;
					}	else if (p3Spot > 25 && p3Spot <= 35){
						p3Spot = 35;
					} break;
					case 11: if(p3Spot > 5)	{
						player1.setMoney(player1.getMoney() + 10);
					} p3Spot = 5; break;
					case 12: p3Spot = 24; break;
					case 13: player1.setMoney(player1.getMoney() - 5); break;
					case 14: p3Spot = 39; break;
					case 15: player1.setMoney(player1.getMoney() + 7.5); break;
					case 16: ownerHolder[29].setText("" + player1.getName()); player1.propertiesOwned ++; break;
			}
		
				}}
				player3MoneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());	
			}	else if (numPlayers > 3)	{
				if(curPlayer == 1)	{
					picHolder1.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
							java.awt.Image.SCALE_SMOOTH)));	
					switch(rand)	{
					case 1: p4Spot = 0; player1.setMoney(player1.getMoney() + 10); break;
					case 2: player1.setMoney(player1.getMoney() + 3); break;
					case 3: if( p4Spot > 4)	{ p4Spot -= 3;} break;
					case 4: if (p4Spot < 12 || p4Spot > 28) { 
								p4Spot = 12;
							}	else	{
								p4Spot = 28; 
							} break;
					case 5: p4Spot = 30; break;
					case 6: player1.setMoney(player1.getMoney() - .5); break;
					case 7: if(p4Spot > 17)	{
						player1.setMoney(player1.getMoney() + 10);
					} p4Spot = 17;   break;
					case 8: player1.setMoney((player1.getMoney() - (numPlayers * 2.5)) + 2.5); player2.setMoney(player2.getMoney() + 2.5);
					if(numPlayers > 2)	{player3.setMoney(player3.getMoney() + 2.5);} if(numPlayers >3)	{ player4.setMoney(player4.getMoney() + 2.5);} break;
					case 9: if (p4Spot <= 5 || p4Spot > 35)	{
						p4Spot = 5;
					}	if (p4Spot > 5 && p4Spot <= 15)	{
						p4Spot = 15;
					}	if (p4Spot > 15 && p4Spot <=25)	{
						p4Spot = 25;
					}	else if (p4Spot > 25 && p4Spot <= 35){
						p4Spot = 35;
					} break;
					case 10: if (p4Spot <= 5 || p4Spot > 35)	{
						p4Spot = 5;
					}	if (p4Spot > 5 && p4Spot <= 15)	{
						p4Spot = 15;
					}	if (p4Spot > 15 && p4Spot <=25)	{
						p4Spot = 25;
					}	else if (p4Spot > 25 && p4Spot <= 35){
						p4Spot = 35;
					} break;
					case 11: if(p4Spot > 5)	{
						player1.setMoney(player1.getMoney() + 10);
					} p4Spot = 5; break;
					case 12: p4Spot = 24; break;
					case 13: player1.setMoney(player1.getMoney() - 5); break;
					case 14: p4Spot = 39; break;
					case 15: player1.setMoney(player1.getMoney() + 7.5); break;
					case 16: ownerHolder[29].setText("" + player1.getName()); player1.propertiesOwned ++; break;
			}
				player4MoneyAndProperties.setText("Money: $" + player1.getMoney() + " | Properties: " + player1.getPropertiesOwned());	
			}
		}
		else if(curPlayer == 2)	{
			picHolder2.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
					java.awt.Image.SCALE_SMOOTH)));	
		}
		else if(curPlayer == 3)	{
			picHolder3.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
					java.awt.Image.SCALE_SMOOTH)));	
		}
		else if(curPlayer == 4)	{
			picHolder4.setIcon(new ImageIcon(ii.getImage().getScaledInstance(mainPanel.getWidth()/7, mainPanel.getHeight()/4,
					java.awt.Image.SCALE_SMOOTH)));	
		}
		
	}

	private void foundSomething() {
		// TODO Auto-generated method stub
		
	}
	
	private void chargeUser(Cards cards) {
		// TODO Auto-generated method stub
		
	}

	public void askToBuy(Cards card)	{
		
	}
}
