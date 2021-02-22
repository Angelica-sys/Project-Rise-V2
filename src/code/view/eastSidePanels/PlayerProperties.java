package code.view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
 * @author Muhammad Abdulkhuder Aevan Dino sebastian Viro.
 */
public class PlayerProperties extends JPanel implements ActionListener {
	private static final long serialVersionUID = 14L;

	private JLabel lblName = new JLabel("Name");
	private JLabel lblPicture = new JLabel("");
	private JLabel lblRent = new JLabel("Rent");
	private JLabel lblRentPerLevel = new JLabel("Rent Per Level");
	private JTextArea taLevel = new JTextArea("");
	private JButton btnUpgrade = new JButton("Upgrade");
	private JButton btnDowngrade = new JButton("Downgrade");
	private JButton btnTrade = new JButton("Trade");
	private JButton btnSell = new JButton("Sell");
	private Font font = new Font("ALGERIAN", Font.BOLD, 22);
	private Font fontLevel = new Font("ALGERIAN", Font.BOLD, 50);
	private String plus = "+";
	private PlayerList playerList;
	private int playerAtI, propertyAtI;

	/**
	 * @param playerList
	 * @param playerAtI
	 * @param propertyAtI 
	 */
	public PlayerProperties(PlayerList playerList, int playerAtI, int propertyAtI) {
		this.playerList = playerList;
		this.playerAtI = playerAtI;
		this.propertyAtI = propertyAtI;

		setBorder(null);
		setOpaque(false);
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(330, 607));
		setLayout(null);

		lblRent.setForeground(Color.white);
		lblRentPerLevel.setForeground(Color.white);

		Purchasable capital = playerList.getPlayerFromIndex(playerAtI).getCapital(propertyAtI);
		lblRent.setText("The rent is: " + capital.getRent());

		if (capital instanceof Property) {
			lblRentPerLevel.setText("The rent per level : " + ((Property) capital).getRentPerLevel());
		} else {
			lblRentPerLevel.setText("Taverns cannot rank up.");
		}

		lblRent.setFont(font);
		lblRentPerLevel.setFont(font);

		lblRent.setBounds(0, 92, 330, 64);
		add(lblRent);
		lblRentPerLevel.setBounds(0, 140, 330, 64);
		add(lblRentPerLevel);

		btnDowngrade.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnDowngrade.setBounds(163, 481, 167, 53);
		add(btnDowngrade);

		btnUpgrade.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnUpgrade.setBounds(0, 481, 167, 53);
		add(btnUpgrade);

		btnTrade.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnTrade.setBounds(163, 532, 167, 46);
		add(btnTrade);

		btnSell.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		btnSell.setBounds(0, 532, 167, 46);
		btnSell.setForeground(Color.red);
		add(btnSell);
		btnSell.setFont(font);

		taLevel.setEditable(false);
		taLevel.setBounds(46, 38, 263, 53);
		taLevel.setOpaque(false);
		taLevel.setFont(fontLevel);
		taLevel.setForeground(Color.white);

		updateLevels();

		add(taLevel);

		lblName.setForeground(Color.white);
		lblName.setOpaque(false);
		lblName.setText(playerList.getPlayerFromIndex(playerAtI).getCapital(propertyAtI).getName());

		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(0, 11, 330, 46);
		add(lblName);
		lblName.setFont(font);
		lblPicture.setForeground(Color.WHITE);

		lblPicture.setBorder(null);
		lblPicture.setBounds(0, 0, 330, 480);
		add(lblPicture);

		lblPicture.setFont(font);
		lblPicture.setOpaque(true);
		btnDowngrade.setFont(font);
		btnUpgrade.setFont(font);
		btnTrade.setFont(font);

		/*
		BufferedImage img = null;

		try {
			Purchasable currentCapital = playerList.getPlayerFromIndex(playerAtI).getCapital(propertyAtI);
			img = ImageIO.read(new File(currentCapital.getImage().toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/

		//Image resizedImg = img.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);

		Image resizedImg = playerList.getPlayerFromIndex(playerAtI).getCapital(propertyAtI).getImage().getImage();
		resizedImg = resizedImg.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);

		lblPicture.setIcon(new ImageIcon(resizedImg));
		btnUpgrade.addActionListener(this);
		btnDowngrade.addActionListener(this);
		btnSell.addActionListener(this);
		btnTrade.addActionListener(this);
	}

	/**
	 * What happens when a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSell) {
			sellCapital();
		} else if (e.getSource() == btnUpgrade) {
			upgradeProperty();
		} else if (e.getSource() == btnDowngrade) {
			downgradeProperty();
		} else if (e.getSource() == btnTrade) {
			tradeCapital();
		}
	}

	private void sellCapital() {
		Purchasable capital = this.playerList.getPlayerFromIndex(playerAtI).getCapital(playerAtI);
		playerList.getPlayerFromIndex(playerAtI).sellCapital(capital);
	}

	private void upgradeProperty() {
		Purchasable capital = this.playerList.getPlayerFromIndex(playerAtI).getCapital(playerAtI);

		if (capital instanceof Property) {
			((Property) capital).increaseLevel();
			String tempRes = taLevel.getText();

			if (tempRes.length() < ((Property) capital).getLevel()) {
				taLevel.append(plus);
			}
		}
	}

	private void downgradeProperty() {
		Purchasable capital = this.playerList.getPlayerFromIndex(playerAtI).getCapital(playerAtI);

		if (capital instanceof Property) {
			((Property) capital).decreaseLevel();
			String tempRes = taLevel.getText();

			if (tempRes.length() > ((Property) capital).getLevel()) {
				tempRes = tempRes.substring(0, tempRes.length() - 1);
				taLevel.setText(tempRes);
			}
		}
	}

	private void tradeCapital() {
		int otherPlayerInt = 0;
		int whichPropertyToGive = 0;
		int whichPropertyYouWant = 0;
		int offer = 0;
		int type = 0;
		int confirm;

		do {
			String otherPlayerChoice = JOptionPane.showInputDialog(
					null,
					"Which code.model.player do you want to trade with?\n " +
							"1 for code.model.player 1 \n " +
							"2 for code.model.player 2 and so on..."
			);
			otherPlayerInt = (Integer.parseInt(otherPlayerChoice) - 1);

		} while (
				otherPlayerInt == playerList.getActivePlayer().getPlayerIndex() ||
				otherPlayerInt > playerList.getLength()-1
		);

		Player activePlayer = playerList.getActivePlayer();
		Player otherPlayer = playerList.getPlayerFromIndex(otherPlayerInt);

		if (otherPlayer.getCapital().size() > 0) {
			do {
				type = (Integer.parseInt(JOptionPane.showInputDialog(
						null,
						"Pick a trade type\n 1 = Property for property \n" +
								"2 = Money for property\n" +
								"3 = Both"))
				);
			} while (type<0 ||type >3);

			if (type == 1 || type == 3) {
				do {
					whichPropertyToGive = (Integer.parseInt(JOptionPane.showInputDialog(
							null,
							"Which property do you want to give away \n" +
									"1 for property 1 \n" +
									"2 for property 2 and so on...")) - 1
					);
				} while (whichPropertyToGive > activePlayer.getCapital().size());
			}

			if (type == 2 || type == 3) {
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

			Purchasable activePlayerCapital = this.playerList.getActivePlayer().getCapital(whichPropertyToGive);
			Purchasable otherPlayerCapital = this.playerList.getPlayerFromIndex(otherPlayerInt).getCapital(whichPropertyYouWant);

			if (type == 1 || type == 3) {
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

			if (type == 2) {
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

	/**
	 * updates levels shown adds a plus to the picture
	 */
	public void updateLevels() {
		Player p = this.playerList.getPlayerFromIndex(this.playerAtI);
		Purchasable capital = p.getCapital(this.propertyAtI);

		if (capital instanceof Property) {
			int level = ((Property) capital).getLevel();

			for (int i=0; i<level; i++) {
				taLevel.append(plus);
			}
		}
	}

	/**
	 * @param property updates levels shown adds a plus to the picture
	 */
	public void updateLevels(Property property) {
		int lvl = property.getLevel();

		for (int i = 0; i < lvl; i++) {
			taLevel.append(plus);
		}
	}
}