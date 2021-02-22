package code.view.eastSidePanels;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import code.model.player.Player;
import code.model.player.PlayerList;
import code.model.tiles.Property;
import code.model.tiles.Purchasable;

/**
 * @author Muhammad Abdulkhuder, Aevan Dino.
 */
public class PropertyWindow extends JPanel {
	private static final long serialVersionUID = 12L;

	private PlayerList playerList;
	private JTabbedPane tab;
	private int playerAt;
	private int nCapital;

	/**
	 *this method is used to update the panel
	 */
	public PropertyWindow() {
		setPreferredSize(new Dimension(330, 600));
		setOpaque(false);
		setLayout(null);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.selected", Color.cyan);

		tab = new JTabbedPane();
		tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tab.setBorder(null);
		tab.setBounds(0, 0, 330, 600);
		add(tab);
	}

	/**
	 * TODO: no need to rewrite every time? Just if a new property has been added?
	 * this method loops the amount of players and adds tabs according to the number of properties
	 */
	public void addPlayerList(PlayerList playerList) {
		this.playerList = playerList;
		Player p = this.playerList.getPlayerFromIndex(getPlayerAt());
		int nCapital = p.getCapital().size();

		System.out.println("Player: " + p.getName());
		System.out.println("nCapital: " + nCapital);
		System.out.println("last: " + this.nCapital);

		if (nCapital != this.nCapital) {
			this.nCapital = nCapital;
			this.tab.removeAll();
			this.tab.setForeground(Color.white);

			for (int i=0; i<nCapital; i++) {
				System.out.println("I'm in me mums car.");
				new PropertyWindow();
				PlayerProperties playerProperties = new PlayerProperties(playerList, getPlayerAt(), i);
				tab.addTab("Capital " + (i+1), playerProperties);

				Purchasable playerCapital = playerList.getPlayerFromIndex(getPlayerAt()).getCapital(i);
				if (playerCapital instanceof Property) {
					tab.setBackgroundAt(i, ((Property) playerCapital).getColor());
				} else {
					tab.setBackgroundAt(i, Color.GRAY);
				}
			}
		}
	}

	/**
	 * @return playerAT
	 */
	public int getPlayerAt() {
		return playerAt;
	} 

	/**
	 * @param playerAt
	 */
	public void setPlayerAt(int playerAt) {
		this.playerAt = playerAt;
	}
}
