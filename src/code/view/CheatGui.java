package code.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import code.view.dice.Dice;

/**
 * @author Sebastian Viro, Muhammad Abdulkhuder
 * This class is used for testing purposes only.
 */
public class CheatGui extends JFrame implements ActionListener {
	private JTextField inputTF;
	private JButton btnTeleport;
	private Dice betterDice;
	private int index;

	/**
	 * @param dice
	 * Calls the method that starts the gui and gets a reference from code.view.dice
	 */
	public CheatGui(Dice dice) {
		this.setMinimumSize(new Dimension(200, 200));
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setName("Cheat GUI");

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(100, 100));
		mainPanel.setLayout(new BorderLayout());
		this.add(mainPanel);

		this.btnTeleport = new JButton("Teleport");
		this.btnTeleport.setPreferredSize(new Dimension(300, 50));
		this.inputTF = new JTextField("");
		mainPanel.add(inputTF, BorderLayout.CENTER);
		mainPanel.add(btnTeleport, BorderLayout.SOUTH);
		this.btnTeleport.addActionListener(this);

		this.betterDice = dice;
	}

	/**
	 * This is what happens when a button is pressed
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnTeleport) {
			try {
				setIndex(Integer.parseInt(inputTF.getText()));
				betterDice.moveWCheat(getIndex());
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * @return index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index 
	 */
	public void setIndex(int index) {
		this.index = index;
	}
}
