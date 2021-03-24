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
		generateTileInfo();
	}

	public void purchase(Player player) {
		this.setOwner(player);
		this.setPurchasable(false);
		this.generateTileInfo();
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public String generateTileInfo() {
		String ownerName;

		if (getOwner() == null) {
			ownerName = "No Owner";
		} else {
			ownerName = getOwner().getName();
		}

		String description =
				"\nOwner:\t" + ownerName +
				"\nPrice:\t" + this.price +
				"\nOne Tavern Owned:\t 10x sum of dice roll" +
				"\nTwo Taverns Owned:\t 20x sum of dice roll"
				;

		setDescription(description);
		return description;
	}
	
	public void clearTavern() {
		setPurchasable(true);
	}
}
