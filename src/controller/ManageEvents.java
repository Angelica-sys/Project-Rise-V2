package controller;

import java.util.Random;
import javax.swing.JOptionPane;

import model.tiles.*;
import view.board.Board;
import view.dice.Dice;
import view.eastSidePanels.EastSidePanel;
import view.messageGui.DeathGUI;
import view.messageGui.FortuneTellerGUI;
import view.messageGui.WinGui;
import model.player.Player;
import model.player.PlayerList;
import model.player.PlayerRanks;
import view.WestSidePanel;

/**
* The class handles all the controller.events that occur when a model.player lands on a tile.
* @author Seth Oberg, Rohan Samandari,Muhammad Abdulkhuder ,Sebastian Viro, Aevan Dino.
*/
public class ManageEvents {
	private PlayerList playerList;
	private Board board;
	private Dice dice;
	private DeathGUI deathGUI;
	private FortuneTellerGUI msgGUI;
	private EastSidePanel eastPanel;
	private Random rand = new Random();
	private int roll;
	private int taxCounter = 0;
	private WestSidePanel westPanel;

	/**
	 * Constructor initializes objects in the parameter. Creates Death -and MessageGUI.
	 * @param board
	 * @param playerList
	 * @param pnlWest
	 * @param dice
	 * @param eastPanel
	 */
	public ManageEvents(Board board, PlayerList playerList, WestSidePanel pnlWest, Dice dice, EastSidePanel eastPanel) {
		this.dice = dice;
		this.westPanel = pnlWest;
		this.board = board;
		this.playerList = playerList;
		this.eastPanel = eastPanel;
		deathGUI = new DeathGUI();
		msgGUI = new FortuneTellerGUI();
		deathGUI = new DeathGUI();
	}

	/**
	 * Method checks what type of tile the model.player has landed on.
	 * @param tile the model.player landed on.
	 * @param player, model.player who landed on a tile.
	 */
	public void newEvent(Tile tile, Player player) {
		player.checkPlayerRank();

		if (player.getPlayerRank() == PlayerRanks.KINGS) {
			new WinGui();
		}

		if (playerList.getLength() == 1) {
			new WinGui();
		}

		if (tile instanceof Property) {
			propertyEvent(tile, player);
		}

		if (tile instanceof Tax) {
			taxEvent(tile, player);
		}

		if (tile instanceof Jail) {
			jailEvent(tile, player);
		}

		if (tile instanceof GoToJail) {
			goToJailEvent(tile, player);
		}

		if (tile instanceof Tavern) {
			tavernEvent(tile, player);
		}

		if (tile instanceof SundayChurch) {
			churchEvent(player);
		}

		if (tile instanceof Work) {
			workEvent(player);
		}

		if (tile instanceof FortuneTeller) {
			fortuneTellerEvent((FortuneTeller)tile, player);
		}

		eastPanel.addPlayerList(playerList);
	}

	/**
	 * This method is supposed to be called from any class that requires the current
	 * model.player to pay any amount, if the user does not have the amount required they
	 * should be removed from the game
	 */
	public void checkPlayerBalance(Player player, int amount) {
		if (player.getBalance() < amount) {
			player.setIsAlive(false);
			playerList.switchToNextPlayer();
			playerList.eliminatePlayer(player);
			playerList.updatePlayerList();
			eastPanel.addPlayerList(playerList.getList());
			dice.setPlayerList(playerList.getList());
			board.removePlayer(player);

			System.out.println("Removing player");
			deathGUI.showGUI();
		} 
	}

	/**
	 * Method called when model.player lands on a property. Checks if it's availability and if the model.player has to pay rent or
	 * can purchase the property.
	 * @param tile
	 * @param player
	 */
	public void propertyEvent(Tile tile, Player player) {
		Property tempProperty = (Property) tile;

		if (tempProperty.isPurchasable()) {
			if (player.getBalance() < tempProperty.getPrice()) {
				JOptionPane.showMessageDialog(null, "Not enough funds to purchase this property");
			} else {
				propertyDialog(tempProperty, player);
			}
		} else {
			if (player != tempProperty.getOwner()) {
				int tempInt;

				if (tempProperty.getLevel() == 0) {
					tempInt = tempProperty.getDefaultRent();
				} else {
					tempInt = tempProperty.getTotalRent();
				}

				checkPlayerBalance(player, tempInt);

				if (player.isAlive()) {
					String effect = player.getName() + " paid " + tempProperty.getTotalRent() +
							" GC to " + tempProperty.getOwner().getName();

					JOptionPane.showMessageDialog(null, effect);
					westPanel.append(effect);
					westPanel.append("\n");

					player.decreaseBalace(tempInt);
					if (tempProperty.getLevel() == 0) {
						player.decreaseNetWorth(tempInt);
					}
					tempProperty.getOwner().increaseBalance(tempInt);
				}
			}
		}
	}

	/**
	 * Method called when the model.player lands on a work tile.
	 * @param player
	 */
	public void workEvent(Player player) {
		int roll = getRoll();
		int salary = player.getPlayerRank().getSalary(roll);

		player.increaseBalance(player.getPlayerRank().getSalary(roll));
		player.increaseNetWorth(salary);

		westPanel.append(player.getName() + " Got " + salary + " GC\n");
		JOptionPane.showMessageDialog(
				null,
				"The roll is " + roll + "\n" + "You got: " + salary + " GC for your hard work"
		);
	}

	/**
	 * Method called when the model.player lands on a tax tile.
	 * @param tile
	 * @param player
	 */
	public void taxEvent(Tile tile, Player player) {
		Tax tempTaxObject = (Tax) tile;
		int chargePlayer = tempTaxObject.getTax();
		checkPlayerBalance(player, chargePlayer);

		if (player.isAlive()) {
			westPanel.append(player.getName() + " paid 200 GC in tax\n");
			player.decreaseBalace(chargePlayer);
			player.decreaseNetWorth(chargePlayer);
			taxCounter++;
		}
	}

	/**
	 * Gets the total tax paid by players
	 * @return total tax
	 */
	public int getChurchTax() {
		int totalTax = taxCounter * 200;
		return totalTax;
	}

	/**
	 * Method called when players lands on a tavern tile, checks it's availability. 
	 * @param tile
	 * @param player
	 */
	public void tavernEvent(Tile tile, Player player) {
		Tavern tempTavernObj = (Tavern) tile;

		if (tempTavernObj.isPurchasable()) {
			if (player.getBalance() < tempTavernObj.getPrice()) {
				JOptionPane.showMessageDialog(null, "Not enough funds to purchase this tavern");
			} else {
				tavernDialog(tempTavernObj, player);
			}
		} else {
			if (player != tempTavernObj.getOwner()) {
				int randomValue = getRoll();
				randomValue *= 10 * tempTavernObj.getOwner().getAmountOfTaverns();
				checkPlayerBalance(player, randomValue);

				if (player.isAlive()) {
					JOptionPane.showMessageDialog(null, player.getName() + " paid " +
							randomValue + " GC to " + tempTavernObj.getOwner().getName());
					westPanel.append(player.getName() + " paid " + randomValue + " GC to "
							+ tempTavernObj.getOwner().getName() + "\n");

					tempTavernObj.getOwner().increaseBalance(randomValue);
					tempTavernObj.getOwner().increaseNetWorth(randomValue);
					player.decreaseBalace(randomValue);
				}
			}
		}
	}

	/**
	 * Method for jailed players, giving them the option to pay bail if the have enough balance.
	 * @param tile
	 * @param player in jail
	 */
	public void jailEvent(Tile tile, Player player) {
		if (player.isPlayerInJail() && (player.getJailCounter()) < 2) {
			westPanel.append(player.getName() + " is in jail for " + (2 - player.getJailCounter()) + " more turns\n");
			player.increaseJailCounter();
			if (player.getBalance() > (player.getJailCounter() * 50)) {
				jailDialog(player);
			} else {
				JOptionPane.showMessageDialog(null, "You can not afford the bail");
			}
		} else if (player.getJailCounter() >= 2) {
			player.setPlayerIsInJail(false);
			player.setJailCounter(0);
			dice.activateRollDice();
		}
	}

	/**
	 * Method to jail a model.player.
	 * @param tile
	 * @param player
	 */
	public void goToJailEvent(Tile tile, Player player) {
		player.setPlayerIsInJail(true);
		board.removePlayer(player);
		player.setPositionInSpecificIndex(10);
		board.setPlayer(player);
		JOptionPane.showMessageDialog(null, player.getName() + " got in jail.");
		westPanel.append(player.getName() + " is in jail for " + (2 - player.getJailCounter()) + " more turns\n");
	}

	/**
	 * Method called if the model.player lands on sunday church. Pays out all the collected tax then resets the counter.
	 * @param player
	 */
	public void churchEvent(Player player) {
		player.increaseBalance(200 * taxCounter);
		player.increaseNetWorth(200 * taxCounter);
		westPanel.append(player.getName() + " got " + taxCounter * 200 + " GC from the church\n");
		taxCounter = 0;
	}

	/**
	 * Method for a dialog if the model.player is able to purchase a property.
	 * @param property in question.
	 * @param player in question.
	 */
	public void propertyDialog(Property property, Player player) {
		String question = property.getName() + "\n" + "Do you want to purchase this property for " +
				property.getPrice() + " GC";

		boolean purchase = (JOptionPane.showConfirmDialog(
				null,
				question,
				"Decide your fate!",
				JOptionPane.YES_NO_OPTION
		) == 0);

		if (purchase && (property.getPrice() <= player.getBalance())) {
			property.setOwner(player);
			property.setPurchasable(false);
			property.getTileInfo();

			player.addNewProperty(property);
			player.decreaseBalace(property.getPrice());
			westPanel.append(player.getName() + " purchased " + property.getName() + "\n");
		} else {
			westPanel.append(player.getName() + " did not purchase " + property.getName() + "\n");
		}
	}

	/**
	 * Method for a dialog if the model.player wants to purchase a tavern.
	 * @param tavern, the to buy.
	 * @param player, model.player who landed on the tavern.
	 */
	public void tavernDialog(Tavern tavern, Player player) {
		int yesOrNo = JOptionPane.showConfirmDialog(
				null,
				"Do you want to purchase this property?",
				"JOption",
				JOptionPane.YES_NO_OPTION
		);

		if (yesOrNo == 0 && (tavern.getPrice() <= player.getBalance())) {
			tavern.setOwner(player);
			player.addNewTavern(tavern);
			tavern.setPurchasable(false);
			player.decreaseBalace(tavern.getPrice());
			westPanel.append(player.getName() + " purchased " + tavern.getName() + "\n");
		} else {
			westPanel.append(player.getName() + " did not purchase " + tavern.getName() + "\n");
		}
	}

	/**
	 * @return roll of the view.dice.
	 */
	public int getRoll() {
		return dice.getRoll();
	}

	/**
	 * Sets the roll of the view.dice.
	 * @param dice
	 */
	public void setRoll(Dice dice) {
		this.roll = dice.getRoll();
	}

	/**
	 * Message for the prisoner to choose if the model.player wants to pay the bail and
	 * get free
	 * @param player in jail.
	 */
	public void jailDialog(Player player) {
		int yesOrNo = JOptionPane.showConfirmDialog(
				null,
				"Do you want to pay the bail\nWhich is " + (player.getJailCounter() * 50) + " GC?",
				"JOption",
				JOptionPane.YES_NO_OPTION
		);

		int totalBail = player.getJailCounter() * 50;
		if (yesOrNo == 0 && (totalBail <= player.getBalance())) {
			player.setJailCounter(0);
			player.setPlayerIsInJail(false);
			westPanel.append(player.getName() + " paid the bail and\ngot free from jail\n");
			dice.activateRollDice();
		} else {
			westPanel.append(player.getName() + " did not pay tha bail\n and is still in jail\n");
		}
	}
	
	/**
	 * Method for FortuneTeller, small chance for a secret event to trigger.
	 * @param fortune, tile the model.player landed on.
	 * @param player, model.player in question.
	 */
	private void fortuneTellerEvent(FortuneTeller fortune, Player player) {
        fortune.setAmount(rand.nextInt(600) - 300);

        if (fortune.getAmount() < 0) {
            int pay = (fortune.getAmount() * -1);
            fortune.setIsBlessing(false);
            fortune.setFortune("CURSE");
            checkPlayerBalance(player, pay);

            if (player.isAlive()) {
                westPanel.append(player.getName() + " paid " + pay + " GC\n");
                player.decreaseBalace(pay);
                player.decreaseNetWorth(pay);
                msgGUI.newFortune(false, pay);
            }
        } else {
            fortune.setIsBlessing(true);
            fortune.setFortune("BLESSING");
            player.increaseBalance(fortune.getAmount());
            player.increaseNetWorth(fortune.getAmount());
            westPanel.append(player.getName() + " received " + fortune.getAmount() + " CG\n");
            msgGUI.newFortune(true, fortune.getAmount());
        }
	}
}
