package model.tiles;

import javax.swing.ImageIcon;

/**
 * Player does not have to pay anything and doesn't get paid for it. // But the model.player gets all the collected church taxes.
 * @author AevanDino, SebastianViro
 */
public class SundayChurch extends Tile {
	public SundayChurch() {
		super("Sunday Church", "", new ImageIcon("tilePics/church.png"));
		getTileInfo();
	}

	public String getTileInfo() {
		String description = "Sunday Church \nPlayer attends church, free of charge";
		setDescription(description);
		return description;
	}
}
