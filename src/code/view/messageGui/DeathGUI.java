package code.view.messageGui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to draw a GUI when players are eliminated
 * @author Sebastian Viro, Muhammad Abdulkhuder
 */
public class DeathGUI extends JPanel implements ActionListener {
	private JButton btnExit;
	private JFrame frame;
	
	/**
	 * Draws the gui
	 */
	public DeathGUI() {
		this.setPreferredSize(new Dimension(1200, 675));
		this.setLayout(null);

		JLabel lblPic = new JLabel();
		lblPic.setBounds(0, 0, 1200, 675);
		lblPic.setIcon(new ImageIcon("src/resources/images/popups/death.png"));
		this.add(lblPic);

		this.btnExit = new JButton("OK");
		this.btnExit.setBounds(400, 625, 400, 50);
		this.btnExit.setFont(new Font("Gabriola", Font.BOLD, 32));
		this.btnExit.addActionListener(this);

		this.add(btnExit);
	}

	/**
	 * Creates the frame
	 */
	public void showGUI() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}

	/**
	 * Adds action the the btnExit, disposes the frame.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExit) {
			frame.dispose();
		}
	}
}
