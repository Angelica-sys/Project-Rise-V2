package model.tiles;

import javax.swing.ImageIcon;

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

	public String getTitle() {
		return null;
	}
}
