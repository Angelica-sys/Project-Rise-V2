package code.view;

import java.awt.Color;
import java.util.HashMap;

/**
 * This class returns a color with a string 
 * @author Seth Oberg
 */
public class StringColorMap {
	private HashMap<String, Color> colorMap = new HashMap<String, Color>();
	
	/**
	 * Add colors to hashmap
	 */
	public StringColorMap() {
		addColorsToMap();
	}

	private void addColorsToMap() {
		/*
		colorMap.put("MAGENTA", new Color(255, 15, 226));
		colorMap.put("RED", new Color(255, 0, 10, 255));
		colorMap.put("ORANGE", new Color(254, 119, 14, 255));
		colorMap.put("YELLOW", new Color(206, 183, 51, 255));
		colorMap.put("GREEN", new Color(35, 254, 14, 255));
		colorMap.put("CYAN", new Color(93, 188, 210, 255));
		colorMap.put("PURPLE", Color.decode("#9542f4"));
		*/
		colorMap.put("MAGENTA", new Color(204, 41, 184));
		colorMap.put("RED", new Color(204, 40, 46));
		colorMap.put("ORANGE", new Color(204, 111, 41));
		colorMap.put("YELLOW", new Color(204, 196, 41));
		colorMap.put("GREEN", new Color(56, 204, 42));
		colorMap.put("CYAN", new Color(40, 155, 203));
		colorMap.put("PURPLE", new Color(135, 40, 205));
	}

	/**
	 * Either magenta, red, orange, yellow, green, cyan, purple
	 * @param chosenColor color to Get
	 * @return
	 */
	public Color getColorFromMap(String chosenColor) {
		return colorMap.get(chosenColor);
	}
}
