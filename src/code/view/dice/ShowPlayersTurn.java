package code.view.dice;

import code.model.player.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;

/**
 * A Lable which shows current players turn
 * @author Rohan Samandari
 */
public class ShowPlayersTurn extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel lblPlayer;
	private Color players;
	private String playerName;
	
	/**
	 * @param player The current player name in which the label displays.
	 */
	public ShowPlayersTurn(String player) {
		this.playerName = player;
		players = Color.DARK_GRAY;
		showTheLabel();
	}
	
	/**
	 * Shows the label
	 */
	private void showTheLabel() {
		setPreferredSize(new Dimension(250,50));
		setBackground(players);
		
		lblPlayer = new JLabel(playerName);
		lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayer.setForeground(Color.white);
		lblPlayer.setPreferredSize(new Dimension(240,45));
		lblPlayer.setFont(new Font("ALGERIAN", Font.BOLD, 14 ));
		lblPlayer.setBorder(BorderFactory.createLineBorder(Color.white));
		add(lblPlayer);
	}

	/**
	 * @param p the Player whose turn it is.
	 * Updates the label
	 */
	public void uppdateGUI(Player p) {
		lblPlayer.setOpaque(true);
		lblPlayer.setBackground(p.getPlayerColor());
		lblPlayer.setText(p.getName() + "'s turn");
	}
}
