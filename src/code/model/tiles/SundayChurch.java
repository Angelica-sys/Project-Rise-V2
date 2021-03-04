package code.model.tiles;

import javax.swing.ImageIcon;

/**
 * Whoever lands here gets the sum of all the church tax allocated since the last time someone landed here.
 * @author AevanDino, SebastianViro, Tor Stenfeldt
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
