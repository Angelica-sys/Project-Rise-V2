package code.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import code.view.board.Rules;
import code.view.startMenu.BackgroundMusic;
import code.view.startMenu.StartingScreen;

/**
 * This class displays the game board as well as what the players are called and how much they own.
 * @author Muhammad Hasan, Rohan Samandari
 */
public class Menu extends JPanel {
	private BackgroundMusic bgm;
	private JMenuItem jmExit = new JMenuItem("Exit");
	private JMenuItem jmOptions = new JMenuItem("Pause Music");
	private JMenuItem jmRestart = new JMenuItem("Restart Game");
	private JMenuItem jmRules = new JMenuItem("Read Rules");
	private Rules rules = new Rules();
	private int musicValue = 0;

	/**
	 * Constructor which draws the gui
	 */
	public Menu(BackgroundMusic music) {
		setOpaque(false);
		setPreferredSize(new Dimension(400, 40));
		setLayout(new BorderLayout());
		JMenuBar jmMenuBar = new JMenuBar();
		jmMenuBar.setPreferredSize(new Dimension(100, 5));
		jmExit.addActionListener(new ButtonListener());
		jmOptions.addActionListener(new ButtonListener());
		jmRules.addActionListener(new ButtonListener());
		jmRestart.addActionListener(new ButtonListener());
		JMenu jmMenu = new JMenu("Menu");
		jmMenu.add(jmOptions);
		jmMenu.add(jmRules);
		jmMenu.add(jmRestart);
		jmMenu.add(jmExit);
		jmMenuBar.add(jmMenu);

		add(jmMenuBar, BorderLayout.WEST);
		setBackground(Color.black);
		this.bgm = music;
	}

	/**
	 * Button listener class used to listen for actions
	 * @author Rohan Samandari
	 */
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jmOptions) {
				if (musicValue == 0) {
					System.out.println("pause");
					bgm.pauseMusic();
					jmOptions.setText("Start Music");
					musicValue += 1;
				} else {
					bgm.startMusic();
					jmOptions.setText("Pause Music");
					musicValue -= 1;
				}
			} else if (e.getSource() == jmRestart) {
				StartingScreen ss = new StartingScreen();
				ss.initializeGUI();
				GamePanels gp = new GamePanels(bgm);
				gp.Dispose();
			} else if (e.getSource() == jmExit) {
				System.exit(0);
			} else if (e.getSource() == jmRules) {
				rules.showRules();
			}
		}
	}
}
