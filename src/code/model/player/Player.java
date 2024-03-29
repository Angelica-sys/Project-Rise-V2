package code.model.player;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import code.model.tiles.Property;
import code.model.tiles.Purchasable;
import code.model.tiles.Tavern;

/**
 * Player class deals with everything that has to do with a player.
 * @author AevanDino, Seth �berg, Muhammad Hasan, Sebastian Viro, Tor Stenfeldt, Hanna My Jansson
 */
public class Player {
	private String name;
	private Boolean isAlive;
	private ImageIcon playerIcon;
	private int counter;
	private int playerIndex;
	private int playerJailCounter = 0;
	private boolean playerIsInJail = false;
	private Color playerColor;
	private PlayerRanks playerRank;
	private int balance;
	private int netWorth;
	private boolean playerPassedgo = false;
	private ArrayList<Purchasable> capital;
	private int nProperties, nTaverns;

	/**
	 * Constructor for adding a new player, new players are created by the playerList class and
	 * are automatically set at index 0 on the board with the counter variable set to 0
	 * @param inPlayerName chosen Name
	 * @param playerIcon   imageIcon from ColorIconMap
	 * @param playerIndex  index of player (for example if second player the playerIndex is 1)
	 */
	public Player(String inPlayerName, ImageIcon playerIcon, int playerIndex) {
		setName(inPlayerName);
		this.playerIcon = playerIcon;
		setIsAlive(true);
		this.playerIndex = playerIndex;

		setBalance(1500);
		setNetWorth(1500);
		setPlayerRank(playerRank.PEASANT);
		this.playerIndex = playerIndex;
		this.capital = new ArrayList<>();

		this.nProperties = 0;
		this.nTaverns = 0;

		counter = 0;
	}

	public Player(String inPlayerName, ImageIcon playerIcon, Color playerColor, int playerIndex) {
		this.playerColor = playerColor;
		setName(inPlayerName);
		this.playerIcon = playerIcon;
		setIsAlive(true);
		this.playerIndex = playerIndex;

		setBalance(1500);
		setNetWorth(1500);
		setPlayerRank(playerRank.PEASANT);
		this.playerIndex = playerIndex;
		this.capital = new ArrayList<>();

		this.nProperties = 0;
		this.nTaverns = 0;

		counter = 0;
	}

	/**
	 * Keep track of how many turns a user has been in jail, if 3 the player gets out of jail
	 * if less than 3 the "roll dice" button is to be inactivated and the end turn activated
	 * @return playerJailCounter
	 */
	public int getIncarceration() {
		return this.playerJailCounter;
	}

	/**
	 * Increase number of turns spent in jail by one
	 */
	public int decreaseIncarceration() {
		if (this.playerJailCounter > 0) {
			this.playerJailCounter--;
		}

		return this.playerJailCounter;
	}

	/**
	 * @param inJail if player is sent to jail send true, if player is not in jail anymore set to false
	 */
	public void setIncarcerated(boolean inJail) {
		if (inJail) {
			this.playerJailCounter = 4;
		} else {
			this.playerJailCounter = 0;
		}

		this.playerIsInJail = inJail;
	}

	/**
	 * @return Return either true or false if player in in jail or not
	 */
	public Boolean isIncarcerated() {
		return this.playerIsInJail;
	}

	/**
	 * @return name, the players name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param playerName, the player name to set
	 */
	public void setName(String playerName) {
		this.name = playerName;
	}

	/**
	 * Set the playerIndex of the player (the index the user has in the playerList array)
	 * @param index
	 */
	public void setPlayerIndex(int index) {
		this.playerIndex = index;
	}

	/**
	 * @return the playerIndex of a player
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * Get the position a player has on the board from 0-39
	 * @return counter
	 */
	public int getPosition() {
		return counter;
	}

	/**
	 * Move player to a specific index on the board
	 * @param newPosition
	 */
	public void setPositionInSpecificIndex(int newPosition) {
		this.counter = newPosition;
	}

	/**
	 * method used to move the player by either one or many steps
	 * @param amountOfStepsToMove
	 */
	public void increasePosition(int amountOfStepsToMove) {
		//System.out.println("Steg/position på banan" + counter +"  +  " + amountOfStepsToMove);
		for (int i = 0; i < amountOfStepsToMove; i++) {
			if (counter < 39) {
				counter++;
			} else {
				counter = 0;
				playerPassedgo = true;
			}
		}
	}

	/**
	 * @return a boolean representing whether the player has passed Go or not.
	 */
	public boolean passedGo() {
		return playerPassedgo;
	}

	/**
	 * sets the boolean representing the player having passed Go to false
	 */
	public void resetPassedGo() {
		playerPassedgo = false;
	}

	/**
	 * @return an int representing the players balance.
	 */
	public int getBalance() {
		return this.balance;
	}

	/**
	 * @return playerIcon, the image of a player
	 */
	public ImageIcon getImage() {
		return playerIcon;
	}

	/**
	 * @param playerBalance the playerBalance to set
	 */
	public void setBalance(int playerBalance) {
		this.balance = playerBalance;
	}

	/**
	 * @param decrease amount to decrease players balance by
	 */
	public void decreaseBalance(int decrease) {
		this.balance -= decrease;
	}

	/**
	 * @param income increase players balance by an amount
	 */
	public void increaseBalance(int income) {
		this.balance += income;
	}

	/**
	 * @return the playerIsAlive
	 */
	public Boolean isAlive() {
		return isAlive;
	}

	/**
	 * @param playerIsAlive the playerIsAlive to set
	 */
	public void setIsAlive(Boolean playerIsAlive) {
		this.isAlive = playerIsAlive;
	}

	/**
	 * @return playerRank the rank of the player
	 */
	public PlayerRanks getPlayerRank() {
		return this.playerRank;
	}

	/**
	 * @param playerRank set the rank of this player
	 */
	public void setPlayerRank(PlayerRanks playerRank) {
		this.playerRank = playerRank;
	}

	/**
	 * @return the netWorth
	 */
	public int getNetWorth() {
		return this.netWorth;
	}

	/**
	 * @param netWorth the netWorth to set
	 */
	public void setNetWorth(int netWorth) {
		this.netWorth = netWorth;
	}

	/**
	 * @param decrease
	 */
	public void decreaseNetWorth(int decrease) {
		this.netWorth -= decrease;
	}

	/**
	 * @param income
	 */
	public void increaseNetWorth(int income) {
		this.netWorth += income;
	}

	public void addCapital(Purchasable capital) {
		this.capital.add(capital);

		if (capital instanceof Property) {
			this.nProperties++;
		} else {
			this.nTaverns++;

			if (this.nTaverns > 1) {
				this.capital.forEach(e -> {
					if (e instanceof Tavern) {
						e.setRent(10*this.nTaverns);
					}
				});
			}
		}
	}

	public void removeCapital(Purchasable capital) {
		if (capital instanceof Property) {
			this.nProperties--;
		} else {
			this.nTaverns--;

			if (this.nTaverns != 0) {
				this.capital.forEach(e -> {
					if (e instanceof Tavern) {
						e.setRent(10*this.nTaverns);
					}
				});
			}
		}

		this.capital.remove(capital);
		capital.setOwner(null);
	}

	public void sellCapital(Purchasable capital) {
		int total;
		int amountOfLevels;

		if (capital instanceof Property) {
			amountOfLevels = ((Property) capital).getLevel();
			total = ((Property) capital).getPrice();

			for(int i = 0; i < amountOfLevels; i++){
				total += ((Property) capital).getLevelPrice();
			}

		} else {
			total = ((Tavern) capital).getPrice();
		}

		int res = JOptionPane.showConfirmDialog(
				null,
				"Do you really want to sell " + capital.getName() + " for: " + total
		);

		if (res == 0) {
			increaseBalance(total);
			removeCapital(capital);
			capital.setOwner(null);
			capital.setPurchasable(true);
		}
	}

	public void sellCapital(int capitalNumber) {
		sellCapital(this.getCapital(capitalNumber));
	}

	/**
	 * If user is eliminated reset all users properties and taverns by setting the
	 * amount of houses to 0 and remove the owner
	 */
	public void clearPlayer() {
		this.capital.forEach(e -> {
			if (e instanceof Property) {
				((Property) e).clearProperty();
				((Property) e).setOwner(null);

			} else {
				((Tavern)e).clearTavern();
				((Tavern)e).setOwner(null);
			}
		});
	}

	/**
	 * Gets property at specified position
	 * @param pos
	 * @return
	 */
	public Purchasable getCapital(int pos) {
		return this.capital.get(pos);
	}

	/**
	 * @return propertiesOwned, returns entire ArrayList of properties owned.
	 */
	public ArrayList<Purchasable> getCapital() {
		return this.capital;
	}

	public void checkPlayerRank() {
		if (getNetWorth() < 2000){
			setPlayerRank(PlayerRanks.PEASANT);
		}

		if (getNetWorth() >= 2000) {
			setPlayerRank(PlayerRanks.KNIGHT);
		}

		if (getNetWorth() >= 4000) {
			setPlayerRank(PlayerRanks.LORD);
		}

		if (getNetWorth() >= 7500) {
			setPlayerRank(PlayerRanks.KINGS);
		}
	}

	/**
	 * Returns the players color
	 * @return playerColor
	 */
	public Color getPlayerColor() {
		return playerColor;
	}
}