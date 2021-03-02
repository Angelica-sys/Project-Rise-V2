package code.view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import code.model.player.Player;
import code.model.player.PlayerList;

/**
 * This class is used to show information about the players and the current properties in tabs from property window
 * @author Abdulkhuder Muhammad, Sebastian Viro.
 */
public class PlayerInfoPanel extends JPanel {
	/**
	 * player list is used to get the players to display correct information playernbr is to specify what player
	 * @param playerList
	 * @param playerNumber
	 */
	public PlayerInfoPanel(PlayerList playerList, int playerNumber, EastSidePanel eastSidePanel) {
		Player p = playerList.getPlayerFromIndex(playerNumber);
		setPreferredSize(new Dimension(345, 860));
		JPanel p1 = new JPanel();
		p1.setBounds(10, 5, 330, 50);
		setBackground(Color.DARK_GRAY);

		p1.setBackground(p.getPlayerColor());
		p1.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel p2 = new JPanel();
		p2.setBounds(10, 55, 330, 50);
		p2.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel p3 = new JPanel();
		p3.setBounds(10, 105, 330, 50);
		p3.setBorder(BorderFactory.createLineBorder(Color.black));

		JPanel p4 = new JPanel();
		p4.setBounds(10, 154, 330, 50);
		p4.setBorder(BorderFactory.createLineBorder(Color.black));
		setLayout(null);

		JLabel lblName = new JLabel("");
		lblName.setText(p.getName().toUpperCase());
		Font font = new Font("ALGERIAN", Font.PLAIN, 18);
		lblName.setFont(font);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setOpaque(false);
		lblName.setForeground(Color.white);
		lblName.setBackground(p.getPlayerColor());

		p1.add(lblName);
		add(p1);

		JLabel lblGold = new JLabel("");
		lblGold.setText("Amount of gold: " + p.getBalance());
		lblGold.setFont(font);
		lblGold.setHorizontalAlignment(SwingConstants.CENTER);
		lblGold.setForeground(Color.black);

		p2.add(lblGold);
		add(p2);

		JLabel lblNetworth = new JLabel("");
		lblNetworth.setText("Total wealth: " + p.getNetWorth());
		lblNetworth.setFont(font);
		lblNetworth.setHorizontalAlignment(SwingConstants.CENTER);
		p3.add(lblNetworth);
		add(p3);

		JLabel lblRank = new JLabel("");
		lblRank.setText("Player Rank: " + p.getPlayerRank());
		lblRank.setFont(font);
		lblRank.setHorizontalAlignment(SwingConstants.CENTER);
		p4.add(lblRank);
		add(p4);
		PropertyWindow propertyWindow = new PropertyWindow(eastSidePanel);
		propertyWindow.setBounds(10, 210, 335, 626);

		propertyWindow.setCurrentPlayer(playerNumber);
		propertyWindow.addPlayerList(playerList);
		add(propertyWindow);
	}
}
