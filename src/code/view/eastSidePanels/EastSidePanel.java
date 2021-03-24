package code.view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import code.model.player.Player;
import code.model.player.PlayerList;

/**
 * this class add tabs that displays information about the players in tabs
 *
 * @author Abdulkhuder Muhammad, Sebastian Viro, Hanna My Jansson
 */
public class EastSidePanel extends JPanel {
    private static final long serialVersionUID = 15L;
    private PlayerList playerList;
    private JTabbedPane tab;
    private PlayerInfoPanel playerInfoPnl;
    private int currentPlayer = 0;

    /**
     * @param playerList this method is also used to update the information displayed
     *                   this method adds tabs according to the amount of players
     */
    public void setPlayerList(PlayerList playerList) {
        this.playerList = playerList;
        updatePanel();


    }

    public void updatePanel() {
        this.tab.removeAll();

        for (int i = 0; i < playerList.getLength(); i++) {
            //new EastSidePanel();
            this.playerInfoPnl = new PlayerInfoPanel(playerList, i, this);
            this.playerInfoPnl.setOpaque(false);
            this.tab.addTab("Player " + (i + 1), this.playerInfoPnl);
            this.tab.setOpaque(false);
        }

        updateTab();
    }

    /**
     * Draws the GUI
     */
    public EastSidePanel() {
        setPreferredSize(new Dimension(345, 860));
        setOpaque(false);
        setLayout(null);
        UIManager.put("TabbedPane.contentOpaque", false);
        UIManager.put("TabbedPane.selected", Color.cyan);

        tab = new JTabbedPane();

        tab.setBounds(0, 0, 355, 860);
        tab.setBackground(new Color(0, 0, 0));
        add(tab);
    }

    /**
     * this method is used to display the correct color
     * the active players turn should be green and the others should be red.
     */
    public void nextPlayerUpdateTab() {
        tab.setBackgroundAt(currentPlayer, null);
        currentPlayer++;

        if (currentPlayer > playerList.getLength() - 1) {
            currentPlayer = 0;
        }
        updateTab();
    }

    public void updateTab() {
        tab.setSelectedIndex(currentPlayer);
        tab.setForeground(Color.white);
        tab.setBackground(new Color(157, 0, 0));
        tab.setBackgroundAt(currentPlayer, new Color(0, 157, 0));
    }

    public int getTab() {
        return currentPlayer;
    }

    public void setCurrentPlayer(int currentPlayerIndex) {
        if (currentPlayerIndex < playerList.getLength()) {
            currentPlayer = currentPlayerIndex;
            updateTab();
        }
    }
}
