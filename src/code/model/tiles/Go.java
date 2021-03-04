package code.model.tiles;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * Simple Go tile class. 
 * @author AevanDino, SebastianViro, Tor Stenfeldt
 */
public class Go extends Tile {
	public Go() {
		super("Go", "", new ImageIcon("tilePics/Go.png"));
		getTileInfo();
	}
	
	/**
	 * Returns color of tile
	 */
	public Color getColor() {
		return Color.WHITE;
	}
	
	/**
	 * Returns info about tile
	 */
	public String getTileInfo() {
		String description = getName() + "\nEvery time a player passes by, they \nare rewarded with 200 gold coins";
		setDescription(description);
		return description;
	}
	
	/**
	 * returns null
	 */
	public String getTitle() {
		return null;
	}
}
