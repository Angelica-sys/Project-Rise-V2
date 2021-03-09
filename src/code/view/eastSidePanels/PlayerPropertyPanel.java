package code.view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

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
import code.model.tiles.TradeType;

/**
 * A class representing each Purchasable a Player owns.
 *
 * @author Muhammad Abdulkhuder Aevan Dino sebastian Viro.
 */
public class PlayerPropertyPanel extends JPanel implements ActionListener {
    private JTextArea taLevel = new JTextArea("");
    private JButton btnUpgrade = new JButton("Upgrade");
    private JButton btnDowngrade = new JButton("Downgrade");
    private JButton btnTrade = new JButton("Trade");
    private JButton btnSell = new JButton("Sell");
    private PlayerList playerList;
    private int playerNumber, capitalNumber;
    private EastSidePanel eastSidePanel;

    /**
     * Constructs a new instance of PlayerProperties, containing objects representing each capital a player owns.
     *
     * @param players       a PlayerList object representing all active players.
     * @param playerNumber  an int representing a specific player.
     * @param capitalNumber an int representing a specific property.
     */
    public PlayerPropertyPanel(PlayerList players, int playerNumber, int capitalNumber, EastSidePanel eastSidePanel) {
        Player p = players.getPlayerFromIndex(playerNumber);
        Purchasable capital = p.getCapitals(capitalNumber);

        this.eastSidePanel = eastSidePanel;
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

            for (int i = 0; i < level; i++) {
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
        eastSidePanel.addPlayerList(playerList);
    }

    private void sellCapital() {
        Player p = this.playerList.getPlayerFromIndex(this.playerNumber);
        p.sellCapital(this.capitalNumber);
    }

    private void upgradeProperty() {
        Player p = this.playerList.getPlayerFromIndex(this.playerNumber);
        Purchasable capital = p.getCapitals(this.capitalNumber);

        if (capital instanceof Property) {
            ((Property) capital).increaseLevel();
            String tempRes = taLevel.getText();

            if (tempRes.length() < ((Property) capital).getLevel()) {
                taLevel.append("+");
            }
        }
    }

    private void downgradeProperty() {
        Player p = this.playerList.getPlayerFromIndex(this.playerNumber);
        Purchasable capital = p.getCapitals(this.playerNumber);

        if (capital instanceof Property) {
            ((Property) capital).decreaseLevel();
            String tempRes = this.taLevel.getText();

            if (tempRes.length() > ((Property) capital).getLevel()) {
                tempRes = tempRes.substring(0, tempRes.length() - 1);
                this.taLevel.setText(tempRes);
            }
        }
    }

    /**
     * Makes it possible for a player to trade a property for money, a property for property or
     * both a property and money for property
     *
     * @author Chanon Borgström, Lanna Maslo
     */
    private void tradeCapital() {
        Player activePlayer = playerList.getActivePlayer();
        Player chosenPlayer = choosePlayer();
        int offer = 0;
        int giveAwayProperty = 0;
        int wantedProperty;


        if (chosenPlayer.getCapitals().size() > 0) {
            TradeType tradeType = tradeType();
            if (tradeType == TradeType.Property || tradeType == TradeType.Both) {
                giveAwayProperty = chooseProperty(activePlayer);
            }

            if (tradeType == TradeType.Money || tradeType == TradeType.Both) {
                do {
                    offer = (Integer.parseInt(JOptionPane.showInputDialog(null,
                            "How much do you offer " + chosenPlayer.getName() + "?")));
                } while (offer > activePlayer.getBalance());
            }

            do {
                wantedProperty = chooseProperty(chosenPlayer);
            } while (wantedProperty > chosenPlayer.getCapitals().size());

            Purchasable activePlayerCapital = activePlayer.getCapitals(giveAwayProperty);
            Purchasable chosenPlayerCapital = chosenPlayer.getCapitals(wantedProperty);

            if (tradeType == TradeType.Money) {
                if (!confirmMoneyTrade(activePlayer, chosenPlayer, offer, activePlayerCapital, chosenPlayerCapital)){
                    JOptionPane.showMessageDialog(null, "Trade can not be done! :(");
                }
            }

            if (tradeType == TradeType.Property) {
                if (!confirmPropertyTrade(activePlayer, chosenPlayer, activePlayerCapital, chosenPlayerCapital)){
                    JOptionPane.showMessageDialog(null, "Trade can not be done! :(");
                }
            }

            if (tradeType == TradeType.Both) {
                if(!confirmBothTrade(activePlayer, chosenPlayer, offer, activePlayerCapital, chosenPlayerCapital)){
                    JOptionPane.showMessageDialog(null, "Trade can not be done! :(");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Trade can not be done! The player you picked does not own any properties!");
        }
    }

    /**
     * Makes it possible for an active player to choose another player to trade with
     *
     * @author Chanon Borgström, Lanna Maslo
     * @return the chosen player to trade with
     */
    public Player choosePlayer() {
        Player activePlayer = playerList.getActivePlayer();
        LinkedList<Player> players = playerList.getPlayers();
        String[] playerNames = new String[playerList.getLength()];
        Player choice = null;

        for (int i = 0; i < playerList.getLength(); i++) {
            if (!activePlayer.getName().equals(players.get(i).getName())) {
                playerNames[i] = players.get(i).getName();
            }
        }

        String chosenPlayer = (String) JOptionPane.showInputDialog(null, "Choose a player to trade with...",
                "Trade", JOptionPane.QUESTION_MESSAGE, null, playerNames, // Array of choices
                playerNames[playerNames.length - 1]);

        for (int i = 0; i < playerList.getLength(); i++) {
            if (chosenPlayer.equals(players.get(i).getName())) {
                choice = players.get(i);
            }
        }

        return choice;
    }

    /**
     * Allows the active player to choose a type of trade
     *
     * @author Chanon Borgström, Lanna Maslo
     * @return the chosen type of trade as an Enum
     */
    public TradeType tradeType() {
        TradeType tradeType = TradeType.Money;
        int choice = JOptionPane.showOptionDialog(null, "Choose a trade type", "Trade",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, TradeType.values(), tradeType);

        switch (choice){
            case 0:
                tradeType = TradeType.Money;
                break;
            case 1:
                tradeType = TradeType.Property;
                break;
            case 2:
                tradeType = TradeType.Both;
                break;
        }
        return tradeType;
    }

    /**
     * Makes it possible for the active player to choose which properties should be involved in the trade
     *
     * @author Chanon Borgström, Lanna Maslo
     * @param player
     * @return The chosen property to trade with
     */
    public int chooseProperty(Player player){
        ArrayList<Purchasable> ownedProperties = player.getCapitals();
        String[] propertyNames = new String[ownedProperties.size()];

        for (int i = 0; i < ownedProperties.size(); i++) {
            propertyNames[i] = ownedProperties.get(i).getName();
        }

        int choice = JOptionPane.showOptionDialog(null, player.getName() + ", choose a property to trade with", "Property",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, propertyNames, propertyNames[0]);

        return choice;
    }

    /**
     * Confirms the trade between the active player and the chosen player
     *
     * @author Chanon Borgström, Lanna Maslo
     * @param activePlayer the player that initiated the trade
     * @param chosenPlayer the player that has been chosen to be traded with
     * @param offer the amount of gold coins that the active player has offered the chosen player for their property
     * @param activePlayerCapital the property that the active player is trading with
     * @param chosenPlayerCapital the property that the chosen player is trading with
     * @return false if the trade has not been accepted, true if the trade is a success
     */
    public boolean confirmBothTrade(Player activePlayer, Player chosenPlayer, int offer, Purchasable activePlayerCapital, Purchasable chosenPlayerCapital){
        int confirm = JOptionPane.showConfirmDialog(
                null,
                chosenPlayer.getName() + ", are you okay with this trade?" + "\n You are getting " +
                        offer + "gold coins" + "\n and are trading away " + chosenPlayerCapital.getName() +
                        "\n for " + activePlayerCapital.getName()
        );

        if (confirm == 0) {
            activePlayer.removeCapital(activePlayerCapital);
            chosenPlayer.removeCapital(chosenPlayerCapital);

            activePlayer.decreaseBalance(offer);
            activePlayer.decreaseNetWorth(offer);

            chosenPlayer.increaseBalance(offer);
            chosenPlayer.increaseNetWorth(offer);

            activePlayerCapital.setOwner(chosenPlayer);
            activePlayer.addCapital(chosenPlayerCapital);

            chosenPlayerCapital.setOwner(activePlayer);
            chosenPlayer.addCapital(activePlayerCapital);



            JOptionPane.showMessageDialog(
                    null,
                    "Trade Complete!"
            );
            return true;
        }
        return false;
    }

    /**
     * Confirms the trade between the active player and the chosen player
     *
     * @author Chanon Borgström, Lanna Maslo
     * @param activePlayer the player that initiated the trade
     * @param chosenPlayer the player that has been chosen to be traded with
     * @param offer the amount of gold coins that the active player has offered the chosen player for their property
     * @param activePlayerCapital the property that the active player is trading with
     * @param chosenPlayerCapital the property that the chosen player is trading with
     * @return false if the trade has not been accepted, true if the trade is a success
     */
    public boolean confirmMoneyTrade(Player activePlayer, Player chosenPlayer, int offer, Purchasable activePlayerCapital, Purchasable chosenPlayerCapital){
        int confirm = JOptionPane.showConfirmDialog(null, chosenPlayer.getName() +
                " Are you okay with this trade?"
                + "\n You are getting " + offer + "gold coins for " + chosenPlayerCapital.getName());
        if (confirm == 0) {
            activePlayer.addCapital(chosenPlayerCapital);
            chosenPlayer.removeCapital(chosenPlayerCapital);

            activePlayer.decreaseBalance(offer);
            activePlayer.decreaseNetWorth(offer);

            chosenPlayer.increaseBalance(offer);
            chosenPlayer.increaseNetWorth(offer);

            chosenPlayerCapital.setOwner(activePlayer);

            JOptionPane.showMessageDialog(null, "Trade Complete!");

            return true;
        }
        return false;
    }

    /**
     * Confirms the trade between the active player and the chosen player
     *
     * @author Chanon Borgström, Lanna Maslo
     * @param activePlayer the player that initiated the trade
     * @param chosenPlayer the player that has been chosen to be traded with
     * @param activePlayerCapital the property that the active player is trading with
     * @param chosenPlayerCapital the property that the chosen player is trading with
     * @return false if the trade has not been accepted, true if the trade is a success
     */
    private boolean confirmPropertyTrade(Player activePlayer, Player chosenPlayer, Purchasable activePlayerCapital, Purchasable chosenPlayerCapital) {
        int confirm = JOptionPane.showConfirmDialog(
                null,
                chosenPlayer.getName() + ", are you okay with this trade?" + "\n You are trading away " + chosenPlayerCapital.getName() +
                        "\n for " + activePlayerCapital.getName()
        );

        if (confirm == 0) {
            activePlayer.removeCapital(activePlayerCapital);
            chosenPlayer.removeCapital(chosenPlayerCapital);

            activePlayerCapital.setOwner(chosenPlayer);
            activePlayer.addCapital(chosenPlayerCapital);

            chosenPlayerCapital.setOwner(activePlayer);
            chosenPlayer.addCapital(activePlayerCapital);



            JOptionPane.showMessageDialog(
                    null,
                    "Trade Complete!"
            );
            return true;
        }
        return false;
    }
}