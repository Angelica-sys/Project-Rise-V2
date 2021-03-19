package code.view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants; 
import javax.swing.border.MatteBorder;

import code.model.player.Player;
import code.model.player.PlayerList;
import code.model.tiles.Property;
import code.model.tiles.Purchasable;

/**
 * A class representing each Purchasable a Player owns.
 * @author Muhammad Abdulkhuder Aevan Dino sebastian Viro, Tor Stenfeldt
 */
public class PlayerPropertyPanel extends JPanel implements ActionListener {
	//private static final long serialVersionUID = 14L;

	private JTextArea taLevel = new JTextArea("");
	private JButton btnUpgrade = new JButton("Upgrade");
	private JButton btnDowngrade = new JButton("Downgrade");
	private JButton btnTrade = new JButton("Trade");
	private JButton btnSell = new JButton("Sell");
	private PlayerList playerList;
	private int playerNumber, capitalNumber;

	/**
	 * Constructs a new instance of PlayerProperties, containing objects representing each capital a player owns.
	 * @param players a PlayerList object representing all active players.
	 * @param playerNumber an int representing a specific player.
	 * @param capitalNumber an int representing a specific property.
	 */
	public PlayerPropertyPanel(PlayerList players, int playerNumber, int capitalNumber) {
		Player p = players.getPlayerFromIndex(playerNumber);
		Purchasable capital = p.getCapital(capitalNumber);

		this.playerList = players;
		this.playerNumber = playerNumber;
		this.capitalNumber = capitalNumber;

		this.setBorder(null);
		this.setOpaque(false);
		this.setBackground(Color.DARK_GRAY);
		this.setPreferredSize(new Dimension(330, 607));
		this.setLayout(null);

		JLabel lblRent = new JLabel("Rent");
		lblRent.setForeground(Color.white);
		JLabel lblRentPerLevel = new JLabel("Rent Per Level");
		lblRentPerLevel.setForeground(Color.white);

		lblRent.setText("The rent is: " + capital.getRent());

		if (capital instanceof Property) {
			lblRentPerLevel.setText("The rent per level : " + ((Property) capital).getRentPerLevel());
		} else {
			lblRentPerLevel.setText("Taverns cannot rank up.");
		}

		Font font = new Font("ALGERIAN", Font.BOLD, 22);
		lblRent.setFont(font);
		lblRentPerLevel.setFont(font);

		lblRent.setBounds(0, 92, 330, 64);
		add(lblRent);
		lblRentPerLevel.setBounds(0, 140, 330, 64);
		add(lblRentPerLevel);

		this.btnDowngrade.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 0, 0)));
		this.btnDowngrade.setBounds(163, 481, 167, 53);
		this.add(this.btnDowngrade);

		this.btnUpgrade.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 0, 0)));
		this.btnUpgrade.setBounds(0, 481, 167, 53);
		add(this.btnUpgrade);

		this.btnTrade.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 0, 0)));
		this.btnTrade.setBounds(163, 532, 167, 46);
		add(this.btnTrade);

		this.btnSell.setBorder(new MatteBorder(2, 2, 2, 2, new Color(0, 0, 0)));
		this.btnSell.setBounds(0, 532, 167, 46);
		this.btnSell.setForeground(Color.red);
		add(this.btnSell);
		this.btnSell.setFont(font);

		this.taLevel.setEditable(false);
		this.taLevel.setBounds(46, 38, 263, 53);
		this.taLevel.setOpaque(false);
		Font fontLevel = new Font("ALGERIAN", Font.BOLD, 50);
		this.taLevel.setFont(fontLevel);
		this.taLevel.setForeground(Color.white);

		if (capital instanceof Property) {
			int level = ((Property) capital).getLevel();

			for (int i=0; i<level; i++) {
				String plus = "+";
				this.taLevel.append(plus);
			}
		}

		add(this.taLevel);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.white);
		lblName.setOpaque(false);
		lblName.setText(capital.getName());

		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(0, 11, 330, 46);
		add(lblName);
		lblName.setFont(font);
		JLabel lblPicture = new JLabel("");
		lblPicture.setForeground(Color.WHITE);

		lblPicture.setBorder(null);
		lblPicture.setBounds(0, 0, 330, 480);
		add(lblPicture);

		lblPicture.setFont(font);
		lblPicture.setOpaque(true);
		this.btnDowngrade.setFont(font);
		this.btnUpgrade.setFont(font);
		this.btnTrade.setFont(font);

		int imageWidth = lblPicture.getWidth();
		int imageHeight = lblPicture.getHeight();

		Image capitalImage = capital.getImage();
		capitalImage = capitalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
		ImageIcon capitalIcon = new ImageIcon(capitalImage);

		lblPicture.setIcon(capitalIcon);
		this.btnUpgrade.addActionListener(this);
		this.btnDowngrade.addActionListener(this);
		this.btnSell.addActionListener(this);
		this.btnTrade.addActionListener(this);
	}

	/**
	 * What happens when a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnSell) {
			sellCapital();
		} else if (e.getSource() == this.btnUpgrade) {
			upgradeProperty();
		} else if (e.getSource() == this.btnDowngrade) {
			downgradeProperty();
		} else if (e.getSource() == this.btnTrade) {
			tradeCapital();
		}
	}

	private void sellCapital() {
		Player p = this.playerList.getPlayerFromIndex(this.playerNumber);
		p.sellCapital(this.capitalNumber);
	}

	private void upgradeProperty() {
		Player p = this.playerList.getPlayerFromIndex(this.playerNumber);
		Purchasable capital = p.getCapital(this.capitalNumber);

		if (capital instanceof Property) {
			String message = "Do you want to upgrade " + capital.getName() + " for: " +
					((Property) capital).getLevelPrice() + " GC?";
			int res = JOptionPane.showConfirmDialog (null, message);

			if (res == 0) {
				if (((Property) capital).increaseLevel()) {
					int propertyLevel = ((Property) capital).getLevel();
					String upgrades = "+".repeat(propertyLevel);
					this.taLevel.setText(upgrades);
				}
			}
		}
	}

	private void downgradeProperty() {
		Player p = this.playerList.getPlayerFromIndex(this.playerNumber);
		Purchasable capital = p.getCapital(this.capitalNumber);

		if (capital instanceof Property) {
			String message = "Do you want to downgrade " + capital.getName() + " and gain " +
					((Property) capital).getLevelPrice() + " GC?";
			int res = JOptionPane.showConfirmDialog (null, message);

			if (res == 0) {
				if (((Property) capital).decreaseLevel()) {
					int propertyLevel = ((Property) capital).getLevel();
					String upgrades = "+".repeat(propertyLevel);
					this.taLevel.setText(upgrades);
				}
			}
		}
	}

	private void tradeCapital() {
		int otherPlayerInt = 0;
		int whichPropertyToGive = 0;
		int whichPropertyYouWant = 0;
		int offer = 0;
		int choice = 0;
		int confirm;

		Player activePlayer = this.playerList.getActivePlayer();
		Player otherPlayer = this.playerList.getPlayerFromIndex(otherPlayerInt);

		do {
			String otherPlayerChoice = JOptionPane.showInputDialog(
					null,
					"Which code.model.player do you want to trade with?\n " +
							"1 for code.model.player 1 \n " +
							"2 for code.model.player 2 and so on..."
			);
			otherPlayerInt = (Integer.parseInt(otherPlayerChoice) - 1);

		} while (
				otherPlayerInt == this.playerList.getActivePlayer().getPlayerIndex() ||
				otherPlayerInt > this.playerList.getLength()-1
		);

		if (otherPlayer.getCapital().size() > 0) {
			do {
				choice = (Integer.parseInt(JOptionPane.showInputDialog(
						null,
						"Pick a trade type\n 1 = Property for property \n" +
								"2 = Money for property\n" +
								"3 = Both"))
				);
			} while (choice<0 || choice >3);

			if (choice == 1 || choice == 3) {
				do {
					whichPropertyToGive = (Integer.parseInt(JOptionPane.showInputDialog(
							null,
							"Which property do you want to give away \n" +
									"1 for property 1 \n" +
									"2 for property 2 and so on...")) - 1
					);
				} while (whichPropertyToGive > activePlayer.getCapital().size());
			}

			if (choice == 2 || choice == 3) {
				do {
					offer = (Integer.parseInt(JOptionPane.showInputDialog(null,
							"How much do you offer " + otherPlayer.getName() + "?")));
				} while (offer > activePlayer.getBalance());

			}

			do {
				whichPropertyYouWant = (Integer.parseInt(JOptionPane.showInputDialog(
						null,
						"Which property do you want \n 1 for property 1 \n 2 for property 2 and so on...")) - 1
				);
			} while (whichPropertyYouWant > otherPlayer.getCapital().size());

			Purchasable activePlayerCapital = activePlayer.getCapital(whichPropertyToGive);
			Purchasable otherPlayerCapital = otherPlayer.getCapital(whichPropertyYouWant);

			if (choice == 1 || choice == 3) {
				confirm = JOptionPane.showConfirmDialog(
						null,
						otherPlayer.getName() + " Are you okay with this trade?" + "\n You are getting " +
								offer + "Gold coins" + "\n and are trading away " + otherPlayerCapital.getName() +
								"\n for " + activePlayerCapital.getName()
				);

				if (confirm == 0) {
					activePlayer.removeCapital(activePlayerCapital);
					otherPlayer.removeCapital(otherPlayerCapital);

					activePlayer.decreaseBalace(offer);
					activePlayer.decreaseNetWorth(offer);

					otherPlayer.increaseBalance(offer);
					otherPlayer.increaseNetWorth(offer);

					activePlayerCapital.setOwner(otherPlayer);
					activePlayer.addCapital(otherPlayerCapital);

					otherPlayerCapital.setOwner(activePlayer);
					otherPlayer.addCapital(activePlayerCapital);

					JOptionPane.showMessageDialog(
							null,
							"Trade Complete!"
					);
				}
			}

			if (choice == 2) {
				confirm = JOptionPane.showConfirmDialog(null, otherPlayer.getName() +
						" Are you okay with this trade?"
						+ "\n You are getting " + offer + "Gold coins for " + otherPlayerCapital.getName());
				if (confirm == 0) {
					otherPlayer.removeCapital(otherPlayerCapital);
					activePlayerCapital.setOwner(otherPlayer);
					activePlayer.removeCapital(otherPlayerCapital);

					activePlayer.decreaseBalace(offer);
					activePlayer.decreaseNetWorth(offer);

					otherPlayer.increaseBalance(offer);
					otherPlayer.increaseNetWorth(offer);
					JOptionPane.showMessageDialog(null, "Trade Complete!");
				}
			}
		} else {
			JOptionPane.showMessageDialog(
					null,
					"Trade can not be done! The player you picked does not own any properties!"
			);
		}
	}
}