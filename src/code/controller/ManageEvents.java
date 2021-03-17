package code.controller;

import java.util.Random;
import javax.swing.JOptionPane;

import code.model.tiles.*;
import code.view.board.Board;
import code.view.dice.Dice;
import code.view.eastSidePanels.EastSidePanel;
import code.view.messageGui.DeathGUI;
import code.view.messageGui.FortuneTellerGUI;
import code.view.messageGui.WinGui;
import code.model.player.Player;
import code.model.player.PlayerList;
import code.model.player.PlayerRanks;
import code.view.WestSidePanel;

/**
* The class handles all the code.controller.events that occur when a code.model.player lands on a tile.
* @author Seth Oberg, Rohan Samandari,Muhammad Abdulkhuder ,Sebastian Viro, Aevan Dino, Hanna My Jansson
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
		msgGUI = new FortuneTellerGUI();
		deathGUI = new DeathGUI();
	}

	/**
	 * Method checks what type of tile the code.model.player has landed on.
	 * @param tile the player landed on.
	 * @param player, player who landed on a tile.
	 */
	public void newEvent(Tile tile, Player player) {
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
			tavernEvent((Tavern)tile, player);
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

		checkPlayerUpgrade(player);

		if (player.getPlayerRank() == PlayerRanks.KINGS) {
			new WinGui();
		}

		eastPanel.setPlayerList(playerList);
	}

	/**
	 * This method is supposed to be called from any class that requires the current
	 * player to pay any amount, if the user does not have the amount required they
	 * should be removed from the game
	 */
	public void checkPlayerBalance(Player player, int amount) {
		if (player.getBalance() < amount) {
			int oldPlayerOldIndex = eastPanel.getTab();
			int newplayerNewIndex = 0;

            if(oldPlayerOldIndex==(playerList.getLength()-1)){
                newplayerNewIndex = 0;
            }else{
                newplayerNewIndex = oldPlayerOldIndex;
            }


			player.setIsAlive(false);
			//System.out.println("playerList: " + playerList.getList().getLength() + " " + playerList.getLength());
			playerList.eliminatePlayer(player);
			//System.out.println("eliminated player: " + player.getPlayerIndex());
			board.removePlayer(player);


			if (playerList.getLength() == 1) {
				new WinGui();
			}else {
				deathGUI.showGUI();
				westPanel.append(player.getName() + " has died");
                playerList.setCurrentPlayer(newplayerNewIndex);
				playerList.updatePlayerList();
                eastPanel.setCurrentPlayer(newplayerNewIndex);
				eastPanel.setPlayerList(playerList.getList());
				dice.setPlayerList(playerList.getList());
				dice.activateRollDice();
                updatePlayersPosition();
				//System.out.println("playerList: " + playerList.getList().getLength() + " " + playerList.getLength());
			}
		}
	}
    //loopar igenom alla spelare och placerar om dem på rätt plats.
	public void updatePlayersPosition(){
        for(int i = 0; i< playerList.getLength();i++){
            Player player = playerList.getPlayerFromIndex(i);
            board.removePlayer(player);
            board.clearLabel(player.getPlayerIndex()+1, player.getPosition());
            board.setPlayer(playerList.getPlayerFromIndex(i));
        }
    }

	/**
	 * @author Chanon Borgström, Lanna Maslo
	 * Method that checks if a player has upgraded/downgraded and informs them about the event in a pop up message
	 * @param player active player
	 */
	public void checkPlayerUpgrade(Player player){
		String levelUpMsg = "Congratulations!\nYou have leveled up to ";
		String levelDownMsg = "Oh no!\nYou have leveled down to ";

		if (player.getPlayerRank() == PlayerRanks.PEASANT) {
			if (player.getNetWorth() >= 2000) {
				player.checkPlayerRank();
				JOptionPane.showMessageDialog(null, levelUpMsg + player.getPlayerRank());
			}
		}else if (player.getPlayerRank() == PlayerRanks.KNIGHT){
			if (player.getNetWorth() >= 4000){
				player.checkPlayerRank();
				JOptionPane.showMessageDialog(null, levelUpMsg + player.getPlayerRank());
			} else if (player.getNetWorth() < 2000){
				player.checkPlayerRank();
				JOptionPane.showMessageDialog(null, levelDownMsg + player.getPlayerRank());
			}
		}else if (player.getPlayerRank() == PlayerRanks.LORD){
			if (player.getNetWorth() >= 7500){
				player.checkPlayerRank();
			} else if (player.getNetWorth() < 4000){
				player.checkPlayerRank();
				JOptionPane.showMessageDialog(null, levelDownMsg + player.getPlayerRank());
			}
		}
	}

	/**
	 * Method called when player lands on a property. Checks if it's availability and if the player has to pay rent or
	 * can purchase the property.
	 * @param tile
	 * @param player
	 */
	public void propertyEvent(Tile tile, Player player) {
		Property tempProperty = (Property) tile;

		if (tempProperty.isPurchasable()) {
			if (player.getBalance() >= tempProperty.getPrice()) {
				propertyDialog(tempProperty, player);
			} else {
				JOptionPane.showMessageDialog(null, "Not enough funds to purchase this property");
			}
		} else {
			if (player != tempProperty.getOwner()) {
				int tempInt = tempProperty.getRent();
				checkPlayerBalance(player, tempInt);

				if (player.isAlive()) {
					String effect = player.getName() + " paid " + tempProperty.getRent() +
							" GC to " + tempProperty.getOwner().getName();

					JOptionPane.showMessageDialog(null, effect);
					westPanel.append(effect);
					westPanel.append("\n");

					player.decreaseBalance(tempInt);
					if (tempProperty.getLevel() == 0) {
						player.decreaseNetWorth(tempInt);
					}
					tempProperty.getOwner().increaseBalance(tempInt);
				}
			}
		}
	}

	/**
	 * Method called when the player lands on a work tile.
	 * @param player active player
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
	 * Method called when the code.model.player lands on a tax tile.
	 * @param tile
	 * @param player
	 */
	public void taxEvent(Tile tile, Player player) {
		Tax tempTaxObject = (Tax) tile;
		int chargePlayer = tempTaxObject.getTax();
		checkPlayerBalance(player, chargePlayer);

		if (player.isAlive()) {
			westPanel.append(player.getName() + " paid 200 GC in tax\n");
			player.decreaseBalance(chargePlayer);
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
	 * @param tavern
	 * @param player
	 */
	public void tavernEvent(Tavern tavern, Player player) {
		if (tavern.isPurchasable()) {
			if (player.getBalance() >= tavern.getPrice()) {
				tavernDialog(tavern, player);
			} else {
				JOptionPane.showMessageDialog(null, "Not enough funds to purchase this tavern");
			}
		} else {
			if (player != tavern.getOwner()) {
				int randomValue = getRoll();
				randomValue *= tavern.getRent();

				checkPlayerBalance(player, randomValue);

				if (player.isAlive()) {
					JOptionPane.showMessageDialog(null, player.getName() + " paid " +
							randomValue + " GC to " + tavern.getOwner().getName());
					westPanel.append(player.getName() + " paid " + randomValue + " GC to "
							+ tavern.getOwner().getName() + "\n");

					tavern.getOwner().increaseBalance(randomValue);
					tavern.getOwner().increaseNetWorth(randomValue);
					player.decreaseBalance(randomValue);
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
	 * Method to jail a code.model.player.
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
	 * Method called if the code.model.player lands on sunday church. Pays out all the collected tax then resets the counter.
	 * @param player
	 */
	public void churchEvent(Player player) {
		player.increaseBalance(200 * taxCounter);
		player.increaseNetWorth(200 * taxCounter);
		westPanel.append(player.getName() + " got " + taxCounter * 200 + " GC from the church\n");
		taxCounter = 0;
	}

	/**
	 * Method for a dialog if the code.model.player is able to purchase a property.
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
			property.purchase(player);
			player.addCapital(property);
			player.decreaseBalance(property.getPrice());

			westPanel.append(player.getName() + " purchased " + property.getName() + "\n");
		} else {
			westPanel.append(player.getName() + " did not purchase " + property.getName() + "\n");
		}
	}

	/**
	 * Method for a dialog if the code.model.player wants to purchase a tavern.
	 * @param tavern, the to buy.
	 * @param player, code.model.player who landed on the tavern.
	 */
	public void tavernDialog(Tavern tavern, Player player) {
		int playerResponse = JOptionPane.showConfirmDialog(
				null,
				"Do you want to purchase this property?",
				"JOption",
				JOptionPane.YES_NO_OPTION
		);

		if (playerResponse == 0 && (player.getBalance() >=tavern.getPrice())) {
			tavern.purchase(player);
			player.addCapital(tavern);

			player.decreaseBalance(tavern.getPrice());

			westPanel.append(player.getName() + " purchased " + tavern.getName() + "\n");
		} else {
			westPanel.append(player.getName() + " did not purchase " + tavern.getName() + "\n");
		}
	}

	/**
	 * @return roll of the code.view.dice.
	 */
	public int getRoll() {
		return dice.getRoll();
	}

	/**
	 * Sets the roll of the code.view.dice.
	 * @param dice
	 */
	public void setRoll(Dice dice) {
		this.roll = dice.getRoll();
	}

	/**
	 * Message for the prisoner to choose if the code.model.player wants to pay the bail and
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
	 * @param fortune, tile the code.model.player landed on.
	 * @param player, code.model.player in question.
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
                player.decreaseBalance(pay);
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
