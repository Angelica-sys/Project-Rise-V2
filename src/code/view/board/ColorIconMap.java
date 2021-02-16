package code.view.board;

import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * @author Seth Oberg 
 * Get a image icon object from a string value
 */
public class ColorIconMap {
	private HashMap<String, ImageIcon> colorMap = new HashMap<>();

	/**
	 * Adds all the colors to a hashmap
	 */
	public ColorIconMap() {
		addColorsToMap();
	}

	/**
	 * The method that adds all the colors to a hashmap
	 */
	private void addColorsToMap() {
		String sourceRoot = "src/resources/images/players/";
		colorMap.put("MAGENTA", new ImageIcon(sourceRoot + "playerMagenta.jpg"));
		colorMap.put("RED", new ImageIcon(sourceRoot + "playerRed.jpg"));
		colorMap.put("ORANGE", new ImageIcon(sourceRoot + "playerOrange.jpg"));
		colorMap.put("YELLOW", new ImageIcon(sourceRoot + "playerYellow.jpg"));
		colorMap.put("GREEN", new ImageIcon(sourceRoot + "playerGreen.jpg"));
		colorMap.put("CYAN", new ImageIcon(sourceRoot + "playerCyan.jpg"));
		colorMap.put("PURPLE", new ImageIcon(sourceRoot + "playerPurple.jpg"));
	}

	/**
	 * @param chosenColor
	 * @return ImageIcon
	 */
	public ImageIcon getColorFromMap(String chosenColor) {
		return colorMap.get(chosenColor);
	}
}
