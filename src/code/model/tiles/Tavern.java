package code.model.tiles;

import code.model.player.Player;

import javax.swing.ImageIcon;

/**
 * A subclass of Purchasable, functioning like its sibling the Property with the main difference being
 * an increase to the land cost if the owner owns both Tavern tiles.
 * @author Tor Stenfeldt
 */
public class Tavern extends Purchasable {
	private int price;

	public Tavern(String name, int rent) {
		super(name, "", null, rent, new ImageIcon("src/resources/images/tiles/tavern.png"), true);
		this.price = 150;
		getTileInfo();
	}

	public void purchase(Player player) {
		this.setOwner(player);
		this.setPurchasable(false);
		this.getTileInfo();
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String getTileInfo() {
		String ownerName;

		if (getOwner() == null) {
			ownerName = "No Owner";
		} else {
			ownerName = getOwner().getName();
		}

		String description =
				"\nOwner: \t         " + ownerName +
				"\nPrice:\t\t" + this.price +
				"\nDefault rent:    Read Rules" +
				"\nRent with Levels:\t" 	+ 0 +
				"\nTotal rent:        Read Rules";

		setDescription(description);
		return description;
	}
	
	public void clearTavern() {
		setPurchasable(true);
	}
}
