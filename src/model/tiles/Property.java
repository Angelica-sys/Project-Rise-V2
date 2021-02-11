package model.tiles;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import model.player.Player;

/**
 * Class for property.
 * @author Sebastian Viro, Aevan Dino, Muhammad Abdulkhuder
 */
public class Property extends Purchasable {
	private int price, rentPerLevel, defaultRent, levels, levelPrice;
	private Color color;

	/**
	 * Construtor which sets values of property
	 * @param name, of property
	 * @param price, of property
	 * @param defaultRent, rent for level 0
	 * @param rentPerLevel, amount to be increased by per level
	 * @param color, color of property
	 * @param levelPrice, cost of upgrade 
	 * @param image, image of tile
	 */
	public Property(String name, int price, int defaultRent, int rentPerLevel, Color color, int levelPrice, ImageIcon image) {
		super(name, "", null, image, true);
		getTileInfo();
		this.color = color;

		setPrice(price);
		setDefaultRent(defaultRent);
		setRentPerLevel(rentPerLevel);
		this.levelPrice= levelPrice;
	}
	
	/**
	 * Returns information about tile
	 */
	public String getTileInfo() {
		String ownerName;

		if (getOwner() == null) {
			ownerName = "No Owner";
		} else {
			ownerName = getOwner().getName();
		}

		String description =
				"\nOwner: \t         " + ownerName +
				"\nPrice:\t\t" + price +
				"\nDefault rent:\t" + defaultRent +
				"\nRent per level:\t" + rentPerLevel +
				"\nTotal rent:\t" + getTotalRent();

		setDescription(description);
		return description;
	}

	public void setColor(Color colorOfTile) {
		this.color=colorOfTile;
	}

	public Color getColor() {
		return this.color;
	} 

	public void setPrice(int price) {
		this.price=price;
	}

	public int getPrice() {
		return this.price;
	}

	public void setDefaultRent(int defRent) {
		this.defaultRent = defRent;
	}

	public int getDefaultRent() {
		return this.defaultRent;
	}

	public void setRentPerLevel(int rentPerLevel) {
		this.rentPerLevel = rentPerLevel;
	}

	public int getRentPerLevel() {
		return this.rentPerLevel;
	}
	
	public int getTotalRent() {
		return this.defaultRent + (this.rentPerLevel * this.levels);
	}
	
	public void setLevel(int num) {
		this.levels=num;
	}
	
	public int getLevelPrice() {
		return levelPrice;
	}
 
	public void setLevelPrice(int levelPrice) {
		this.levelPrice = levelPrice;
	}
	
	public void increaseLevel() {
		int res = JOptionPane.showConfirmDialog(
				null,
				"Do you want to upgrade " + getName() + " for: " + getLevelPrice()
		);

		if (res == 0 && getOwner().getPlayerRank().nbrOfLevels() > levels && getOwner().getBalance() >= getLevelPrice()) {
			this.levels+=1;
			getOwner().decreaseBalace(getLevelPrice());
		}
	}
	
	public void decreaseLevel() {
		int res = JOptionPane.showConfirmDialog(
				null,
				"Do you really want to downgrade " + getName() + " for: " + getLevelPrice()
		);

		if (levels>0 && res == 0) {	
			this.levels-=1;
			getOwner().increaseBalance(getLevelPrice());
		}
	}
		
	public int getLevel() {
		return this.levels;
	}
	
	public void clearProperty() {
		setPurchasable(true);
		setLevel(0);
	}
}
