package model.tiles;

import javax.swing.ImageIcon;

/**
 * Fortune teller class, either returns a fortune where the model.player
 * gains money or has to pay.
 * @author SebastianViro, AevanDino, MuhammadAbdulkhuder
 */
public class FortuneTeller extends Tile {
	private int amount;

	public FortuneTeller() {
		super("Fortune Teller", "", new ImageIcon("tilePics/fortune.png"));
		getTileInfo();
	}

	/**
	 * @param fortune the fortune to set
	 */
	public void setFortune(String fortune) {
		// TODO: remove?
	}

	/**
	 * @param isBlessing the isBlessing to set
	 */
	public void setIsBlessing(Boolean isBlessing) {
		// TODO: remove?
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns tile info
	 */
	public String getTileInfo() {
		return "There are two types of cards, blessings and curses." + 
				"\nBlessing affect the model.player in a positive way." +
				"\nCurses affect the model.player in a negative way.";
	}
}