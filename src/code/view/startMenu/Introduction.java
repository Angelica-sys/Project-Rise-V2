package code.view.startMenu;

import code.model.player.PlayerList;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Panel that shows up when players start the game. This contains information about the game
 * @author Muhammad Abdulkhuder, Sebastian Viro, Gustaf Hermansson
 */
public class Introduction extends JPanel {
	private JLabel lblTitel = new JLabel("Welcome to Rise!");
	private JLabel lblPic = new JLabel();
	private JFrame frame;
	private JTextArea taText = new JTextArea();
	private Font fontTitel = new Font("ALGERIAN", Font.ITALIC, 20);
	private Font fontText = new Font("Gabriola", Font.ITALIC, 22);
	private JButton btnContinue = new JButton("Click To Continue!");

	/**
	 * Constructor that calls upon method which draws gui
	 * @param playerList
	 */
	public Introduction(PlayerList playerList) {
		startGUI(playerList);
	}

	/**
	 * Creates gui
	 * @param playerList
	 */
	private void startGUI(PlayerList playerList) {
		String playerOne = playerList.getPlayers().get(0).getName();
		String playerTwo = playerList.getPlayers().get(1).getName();
		setPreferredSize(new Dimension(600, 350));
		getFrame();
		lblTitel.setBounds(87, -20, 411, 86);
		taText.setAlignmentX(Component.RIGHT_ALIGNMENT);
		taText.setBounds(10, 53, 600, 286);
		lblTitel.setFont(fontTitel);
		lblTitel.setHorizontalAlignment(SwingConstants.CENTER);
		taText.setText(
				"Salutations adventurers and welcome to Rise! The people you see next to you are no longer your " +
				"friends. This is a race to the top where only one will triumph as king! \r\n" + "\r\n You start as " +
				"peasants and will rise through the ranks when your total wealth reaches a certain number. " + playerOne +
				" will now begin by rolling the dice and finish their round by pressing the end turn " +
				"button to let " + playerTwo + " begin their turn. Press the info button in the menu tab to know more. " +
				"Good luck on your adventures!  \r\n"
		);
		
		taText.setEditable(false);
		lblTitel.setBackground(Color.black);
		taText.setForeground(Color.black);
		taText.setLineWrap(true);
		taText.setWrapStyleWord(true);
		taText.setFont(fontText);
		setLayout(null);
		taText.setOpaque(false);
		lblPic.setBounds(0, 0, 600, 350);
		btnContinue.setBounds(225, 315, 150, 30);
		btnContinue.addActionListener(new ButtonListener());

		lblPic.setIcon(new ImageIcon("src/resources/images/board/backpaper.jpg"));


		this.add(btnContinue);
		this.add(lblTitel);
		this.add(taText);
		this.add(lblPic);
	}

	 /**
	  * Creates a frame which the panel is shown in.
	  */
    public void getFrame() {
    	frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	private class ButtonListener implements ActionListener {

    	public void actionPerformed (ActionEvent e){
    		if(e.getSource() == btnContinue){
				frame.dispose();
			}
		}
	}
}