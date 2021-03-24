package code.model.tiles;

import code.model.player.Player;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Class for property.
 * @author Sebastian Viro, Aevan Dino, Muhammad Abdulkhuder, Tor Stenfeldt, Angelica Asplund
 */
public class Property extends Purchasable {
	private int price, baseRent, rentPerLevel, levels, levelPrice;
	private Color color;

	/**
	 * Construtor which sets values of property
	 * @param name, of property
	 * @param price, of property
	 * @param baseRent, rent for level 0
	 * @param rentPerLevel, amount to be increased by per level
	 * @param color, color of property
	 * @param levelPrice, cost of upgrade 
	 * @param image, image of tile
	 */
	public Property(String name, int price, int baseRent, int rentPerLevel, Color color, int levelPrice, ImageIcon image) {
		super(name, "", null, baseRent, image, true);
		generateTileInfo();
		this.baseRent = baseRent;
		this.color = color;
		this.levels = 0;

		setPrice(price);
		setRentPerLevel(rentPerLevel);
		this.levelPrice = levelPrice;
	}

	public void purchase(Player player) {
		this.setOwner(player);
		this.setPurchasable(false);
		this.generateTileInfo();
	}
	
	/**
	 * Returns information about tile
	 */
	public String generateTileInfo() {
		String ownerName;

		if (getOwner() == null) {
			ownerName = "No Owner";
		} else {
			ownerName = getOwner().getName();
		}

		String description =
				"\nOwner:\t           " + ownerName +
				"\nPrice:\t           " + this.price +
				"\nBase rent:\t       " + this.getRent() +
				"\nRent per level:\t  " + this.rentPerLevel +
				"\nTotal rent:\t      " + getRent();

		setDescription(description);
		return description;
	}

	/**
	 * Returns relevent information about purchasable tile
	 */
	public String generatePurchaseInfo(){
		String description =
						"\nOwner:" + "No Owner" +
						"\nPrice:" + this.price +
						"\nBase rent:" + this.getRent() +
						"\nRent per level:" + this.rentPerLevel;

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

	public void setRentPerLevel(int rentPerLevel) {
		this.rentPerLevel = rentPerLevel;
	}

	public int getRentPerLevel() {
		return this.rentPerLevel;
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

	/**
	 * Increases the level of the property.
	 */
	public boolean increaseLevel() {
		boolean upgraded = false;

		Player p = getOwner();
		int maxPlayerPropertyRank = p.getPlayerRank().nbrOfLevels();
		int playerBalance = p.getBalance();

		if (maxPlayerPropertyRank > this.levels && playerBalance >= getLevelPrice()) {
			this.levels += 1;
			this.setRent(this.baseRent + this.rentPerLevel*this.levels);
			getOwner().decreaseBalance(getLevelPrice());
			upgraded = true;
		}

		return upgraded;
	}

	/**
	 * Decreases the level of the property.
	 */
	public boolean decreaseLevel() {
		boolean downgraded = false;

		if (this.levels > 0) {
			this.levels -= 1;
			this.setRent(this.baseRent + this.rentPerLevel*this.levels);
			getOwner().increaseBalance(getLevelPrice());
			downgraded = true;
		}

		return downgraded;
	}
		
	public int getLevel() {
		return this.levels;
	}

	/**
	 * Removes any levels of the property and sets it to purchasable.
	 */
	public void clearProperty() {
		setPurchasable(true);
		setLevel(0);
	}
}
