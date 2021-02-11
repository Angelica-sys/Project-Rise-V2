package model.tiles;

import javax.swing.ImageIcon;

/**
 * @author Muhammad abdulkhuder, AevanDino, Sebastian Viro, .
 */
public class Work extends Tile {
	public Work() {
		super("Work", "", new ImageIcon("tilePics/Work.png"));
		getTileInfo();
	}

	public String getTileInfo() {
		String description = getName() + "\nPlayer gets to roll the view.dice, and depending \n"
				+ "on his or her rank they are rewarded gold coins for their effort. \n"
				+ "Peasant: 20 times the sum of the roll \n" + "Knight: 25 times the sum of the roll \n"
				+ "Lord: 30 times the sum of the roll \n" + "Ruler: 40 times the sum of the roll";

		setDescription(description);
		return description;
	}
}
