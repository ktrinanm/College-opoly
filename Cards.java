/*
*@author: Chris Wolf and Katrina Mehring
*
*This class is where the properties are given ImageIcons, names, cost, rentCost, and
*whether it isOwned or not
*/

import javax.swing.ImageIcon;


public class Cards {
	
	String cardName;
	int code;
	ImageIcon picture;
	double costToLandOn; // changes if hotels or houses
	boolean owned;
	int cost;
	
	//checks the cards code to populate its name, cost, picture, etc.
	public Cards(int code) {
		super();
		owned = false;
		switch (code)	{
		case 0: cardName = "Ramen"; picture = new ImageIcon(Monopoly.class.getResource("1.PNG")); costToLandOn = 1.05; cost = 3; break;
		case 1: cardName = "Toilet Paper"; picture = new ImageIcon(Monopoly.class.getResource("2.PNG")); costToLandOn = 1.00; cost = 3; break;
		case 2: cardName = "Cereal"; picture = new ImageIcon(Monopoly.class.getResource("4.PNG")); costToLandOn = 1.75; cost = 5; break;
		case 3: cardName = "Milk"; picture = new ImageIcon(Monopoly.class.getResource("5.PNG")); costToLandOn = 1.80; cost = 5; break;
		case 4: cardName = "Waffles"; picture = new ImageIcon(Monopoly.class.getResource("6.PNG")); costToLandOn = 2.25; cost = 6; break; 
		case 5: cardName = "Frozen Pizza"; picture = new ImageIcon(Monopoly.class.getResource("7.PNG")); costToLandOn = 2.50; cost = 7; break;
		case 6: cardName = "Bus Pass"; picture = new ImageIcon(Monopoly.class.getResource("9.PNG")); costToLandOn = 2.55; cost = 7; break;
		case 7: cardName = "Fast Food"; picture = new ImageIcon(Monopoly.class.getResource("10.PNG")); costToLandOn = 2.8; cost = 8; break;
		case 8: cardName = "Sit-Down Restaurant"; picture = new ImageIcon(Monopoly.class.getResource("12.PNG")); costToLandOn = 3.40; cost = 9; break;
		case 9: cardName = "New Shirt"; picture = new ImageIcon(Monopoly.class.getResource("13.PNG")); costToLandOn = 3.25; cost = 9; break;
		case 10: cardName = "Haircut"; picture = new ImageIcon(Monopoly.class.getResource("14.PNG")); costToLandOn = 3.80; cost = 10; break; 
		case 11: cardName = "Video Game"; picture = new ImageIcon(Monopoly.class.getResource("15.PNG")); costToLandOn = 4.00; cost = 11; break;
		case 12: cardName = "Parking Pass"; picture = new ImageIcon(Monopoly.class.getResource("16.PNG")); costToLandOn = 4.20; cost = 11; break;
		case 13: cardName = "Tank of Gas"; picture = new ImageIcon(Monopoly.class.getResource("17.PNG")); costToLandOn = 4.60; cost = 12; break; 
		case 14: cardName = "Healthy Food"; picture = new ImageIcon(Monopoly.class.getResource("19.PNG")); costToLandOn = 5.05; cost = 13;break;
		case 15: cardName = "Bike"; picture = new ImageIcon(Monopoly.class.getResource("20.PNG")); costToLandOn = 5.00; cost = 13;break; 
		case 16: cardName = "Chair"; picture = new ImageIcon(Monopoly.class.getResource("22.PNG")); costToLandOn = 5.20; cost = 14; break; 
		case 17: cardName = "Textbooks"; picture = new ImageIcon(Monopoly.class.getResource("23.PNG")); costToLandOn = 5.85; cost = 15; break; 
		case 18: cardName = "Christmas Gifts"; picture = new ImageIcon(Monopoly.class.getResource("24.PNG")); costToLandOn = 5.65;  cost = 15; break; 
		case 19: cardName = "Road Trip"; picture = new ImageIcon(Monopoly.class.getResource("25.PNG")); costToLandOn = 6.00; cost = 16; break;
		case 20: cardName = "Television"; picture = new ImageIcon(Monopoly.class.getResource("27.PNG")); costToLandOn = 7.25; cost = 18; break; 
		case 21: cardName = "Computer"; picture = new ImageIcon(Monopoly.class.getResource("28.PNG")); costToLandOn = 8.50; cost = 20; break;  
		case 22: cardName = "Power Bill"; picture = new ImageIcon(Monopoly.class.getResource("8.PNG")); costToLandOn = -1; cost = 8; break;
		case 23: cardName = "Water Bill"; picture = new ImageIcon(Monopoly.class.getResource("21.PNG")); costToLandOn = -1; cost = 8; break;
		case 24: cardName = "Carpool With Friends"; picture = new ImageIcon(Monopoly.class.getResource("3.PNG")); costToLandOn = -1; cost = 10; break;
		case 25: cardName = "Subway"; picture = new ImageIcon(Monopoly.class.getResource("11.PNG")); costToLandOn = -1; cost = 10; break;
		case 26: cardName = "The Bus"; picture = new ImageIcon(Monopoly.class.getResource("18.PNG")); costToLandOn = -1; cost = 10;  break;
		case 27: cardName = "Taxi Cab"; picture = new ImageIcon(Monopoly.class.getResource("26.PNG")); costToLandOn = -1; cost = 10; break;
		case 28: cardName = "Get out of Jail"; picture = new ImageIcon(Monopoly.class.getResource("29.PNG")); costToLandOn = -1; break;
		case 29:  cardName = "Get out of Jail2"; picture = new ImageIcon(Monopoly.class.getResource("30.PNG")); costToLandOn = -1; break;
		default : System.out.println("not valid"); break;
		}
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public ImageIcon getPicture() {
		return picture;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setPicture(ImageIcon picture) {
		this.picture = picture;
	}

	public double getCostToLandOn() {
		return costToLandOn;
	}

	public void setCostToLandOn(double costToLandOn) {
		this.costToLandOn = costToLandOn;
	}
	
	public void setOwned(boolean bought)
	{
		owned = bought;
	}
	
	public boolean isOwned()
	{
		return owned;
	}
	
	
	
	
}
