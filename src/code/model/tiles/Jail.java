package code.model.tiles;

import java.awt.Color;

import javax.swing.ImageIcon;

/**
 * Class for jail tile. 
 * @author Sebastian Viro, Aevan Dino	
 */
public class Jail extends Tile {
	public Jail() {
		super("Jail", "", new ImageIcon("tilePics/jail.png"));
		getTileInfo();
	}

	/**
	 * Returns name of tile
	 */
	public String getName() {
		return "JAIL";
	}

	/**
	 * returns false, because it is not purchasable
	 */
	public Boolean getPurchasable() {
		return Boolean.FALSE;
	}
	
	/**
	 * Returns Color.White
	 */
	public Color getColor() {
		return Color.WHITE;
	}
	
	/**
	 * returns information about tile
	 */
	public String getTileInfo() {
		return
				"JAIL + \nPlayer can choose to spend three rounds here" +
				"\nor pay the bail.\nFirst day costs 50 gold coins" +
				"\nSecond time costs 100 gold coins" +
				"\nThird time code.model.player has to pay 200 and is set free";
	}

	/**
	 * returns null
	 */
	public String getTitle() {
		return null;
	}
}
