package code.model.tiles;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * A tile which forces the player that lands on it to pay tax to the church.
 * @author Tor Stenfeldt
 */
public class Tax extends Tile {
	private int taxToPay = 200;

	public Tax() {
		super("Tax", "", new ImageIcon("tilePics/tax.png"));
		getTileInfo();
	}
	
	public int getTax() {
		return this.taxToPay;
	}

	public Color getColor() {
		return Color.WHITE;
	}
	
	public String getTileInfo() {
		String description = getName() + "\n"
				+ "Owner: \t \t" 			+ "The king" + "\n"
				+ "Peasant tax: \t" 		+ "100 gold coins" + "\n"
				+ "Knight tax: \t" 		+ "150 gold coins" + "\n"
				+ "Lord tax: \t" 	        + "200 gold coins"+ "\n"
				+ "\n";

		//+ "Your tax: \t" 		    + player.getPlayerRank().calculateTax() + "\n";

		setDescription(description);
		return description;
	}
}
