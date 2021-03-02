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
	private JTabbedPane tab;
	private int currentPlayer;
	private int nCapital;
	private EastSidePanel eastSidePanel;

	/**
	 *this method is used to update the panel
	 */
	public PropertyWindow(EastSidePanel eastSidePanel) {
		this.eastSidePanel = eastSidePanel;

		setPreferredSize(new Dimension(330, 600));
		setOpaque(false);
		setLayout(null);
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.selected", Color.cyan);

		this.tab = new JTabbedPane();
		this.tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		this.tab.setBorder(null);
		this.tab.setBounds(0, 0, 330, 600);
		add(this.tab);
	}

	/**
	 * TODO: no need to rewrite every time? Just if a new property has been added?
	 * this method loops through each Purchasable a Player owns and adds them to their JTabbedPane to the east.
	 */
	public void addPlayerList(PlayerList playerList) {
		Player p = playerList.getPlayerFromIndex(this.currentPlayer);
		int nCapital = p.getCapital().size();

		// If the number of Purchasables a Player owns has changed.
		if (nCapital != this.nCapital) {
			this.nCapital = nCapital;
			this.tab.removeAll();
			this.tab.setForeground(Color.white);

			// For every Purchasable a Player owns.
			for (int i=0; i<nCapital; i++) {
				new PropertyWindow(eastSidePanel);
				PlayerPropertyPanel playerPropertyPanel = new PlayerPropertyPanel(playerList, this.currentPlayer, i, eastSidePanel);
				this.tab.addTab("Capital " + (i+1), playerPropertyPanel);

				Purchasable playerCapital = p.getCapital(i);
				if (playerCapital instanceof Property) {
					this.tab.setBackgroundAt(i, ((Property) playerCapital).getColor());
				} else {
					this.tab.setBackgroundAt(i, Color.GRAY);
				}
			}
		}
	}

	/**
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
