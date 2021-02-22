package code.view.board;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Simple tabs with different rules on each page.
 * @author AevanDino
 */
public class Rules extends JPanel {
	private static final long serialVersionUID = 1L;

	public Rules() {
		new GridLayout(1, 1);
		JTabbedPane tabbedPane = new JTabbedPane();

		String sourceRoot = "src/resources/images/rules/";

		JComponent panel1 = makeTextPanel(sourceRoot + "Introduction.jpg");
		tabbedPane.addTab("Intro", panel1);

		JComponent panel2 = makeTextPanel(sourceRoot + "WinCondition.jpg");
		tabbedPane.addTab("Win", panel2);

		JComponent panel3 = makeTextPanel(sourceRoot + "Movement.jpg");
		tabbedPane.addTab("Move", panel3);

		JComponent panel4 = makeTextPanel(sourceRoot + "Properties.jpg");
		tabbedPane.addTab("Property", panel4);
		
		JComponent panel5 = makeTextPanel(sourceRoot + "Work.jpg");
		tabbedPane.addTab("Work", panel5);
		
		JComponent panel6 = makeTextPanel(sourceRoot + "Tavern.jpg");
		tabbedPane.addTab("Tavern", panel6);
		
		JComponent panel7 = makeTextPanel(sourceRoot + "FortuneTeller.jpg");
		tabbedPane.addTab("Fortune", panel7);
		
		JComponent panel8 = makeTextPanel(sourceRoot + "SundayChurch.jpg");
		tabbedPane.addTab("Tax", panel8);
		
		JComponent panel9 = makeTextPanel(sourceRoot + "Jail.jpg");
		tabbedPane.addTab("Jail", panel9);
		
		JComponent panel10 = makeTextPanel(sourceRoot + "Sell.jpg");
		tabbedPane.addTab("Sell", panel10);

		add(tabbedPane); 		
	}

	protected JComponent makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		ImageIcon imgBackground = new ImageIcon(new ImageIcon(text).getImage().getScaledInstance(525, 800, Image.SCALE_SMOOTH));
		JLabel filler = new JLabel(imgBackground);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from
	 * the event dispatch thread.
	 */
	private static void createAndShowFrame() {
		JFrame frame = new JFrame("Rules");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(new Rules(), BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Method which is called to display rules.
	 */
	public void showRules() {
		createAndShowFrame();
	}
}