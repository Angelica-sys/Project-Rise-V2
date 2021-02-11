package model.tiles;

import java.awt.Color;

import javax.swing.ImageIcon;

import model.player.Player;

public class Tavern extends Purchasable {
	private int price;

	public Tavern(String name, int price) {
		super(name, "", null, new ImageIcon("tilePics/tavern.png"), true);
		this.price = price;
		getTileInfo();
	}
	public void onLanding() {
		// TODO Auto-generated method stub
	}

	public String getName() {
		return this.name;
	}

	public Boolean getPurchasable() {
		return this.purchasable;
	}

	public Color getColor() {
		return null;
	}
	
	public void setOwner(Player newOwner) {
		this.owner = newOwner;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String getTileInfo() {
		String ownerName;

		if (this.owner == null) {
			ownerName = "No Owner";
		} else {
			ownerName = this.owner.getName();
		}

		this.description =
				"\nOwner: \t         " + ownerName +
				"\nPrice:\t\t" + this.price +
				"\nDefault rent:    Read Rules" +
				"\nRent with Levels:\t" 	+ 0 +
				"\nTotal rent:        Read Rules";

		return this.description;
	}
	
	public void setPurchasable(boolean b) {
		this.purchasable = b;
	}
	
	public void clearTavern() {
		purchasable = true; 
	}

	public String getTitle() {
		return null;
	}
	
	public ImageIcon getPicture(){
		return this.image;
	}
}
