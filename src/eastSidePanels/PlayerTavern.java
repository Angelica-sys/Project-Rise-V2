package eastSidePanels;

import player.Player;
import player.PlayerList;
import tiles.Property;
import tiles.Tavern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author Hanna My Jansson, 2021-02-01
 * Denna klassen skapade jag för att kunna presentera taverns också på sidan,
 *
 */
public class PlayerTavern extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JLabel lblName = new JLabel("Name");
    private JLabel lblPicture = new JLabel("");
    private JLabel lblRent = new JLabel("Rent");
    private JLabel lblRentPerLevel = new JLabel("Rent Per Level");
    private JTextArea taLevel = new JTextArea("");
    private JButton btnSell = new JButton("Sell");
    private Font font = new Font("ALGERIAN", Font.BOLD, 22);
    private Font fontLevel = new Font("ALGERIAN", Font.BOLD, 50);
    private String plus = "+";
    private PlayerList playerList;
    private int playerAtI, TavernAtI;

    /**
     * @param playerList
     * @param playerAtI
     * @param TavernAtI
     */
    public PlayerTavern(PlayerList playerList, int playerAtI, int TavernAtI) {

        this.playerList = playerList;
        this.playerAtI = playerAtI;
        this.TavernAtI = TavernAtI;

        setBorder(null);

        setOpaque(false);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(330, 607));
        setLayout(null);

        lblRent.setForeground(Color.white);
        lblRentPerLevel.setForeground(Color.white);
        lblRent.setText(
                "The Rent is: " + playerList.getPlayerFromIndex(playerAtI).getTavernAt(TavernAtI).getRent());
        lblRentPerLevel.setText("The rent per level : "
                + playerList.getPlayerFromIndex(playerAtI).getTavernAt(TavernAtI).getRentPerLevel());
        lblRent.setFont(font);
        lblRentPerLevel.setFont(font);

        lblRent.setBounds(0, 92, 330, 64);
        add(lblRent);
        lblRentPerLevel.setBounds(0, 140, 330, 64);
        add(lblRentPerLevel);


        btnSell.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
        btnSell.setBounds(0, 532, 167, 46);
        btnSell.setForeground(Color.red);
        add(btnSell);
        btnSell.setFont(font);

        taLevel.setEditable(false);
        taLevel.setBounds(46, 38, 263, 53);
        taLevel.setOpaque(false);
        taLevel.setFont(fontLevel);
        taLevel.setForeground(Color.white);
        add(taLevel);

        lblName.setForeground(Color.white);
        lblName.setOpaque(false);
        lblName.setText(playerList.getPlayerFromIndex(playerAtI).getTavernAt(TavernAtI).getName());

        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(0, 11, 330, 46);
        add(lblName);
        lblName.setFont(font);
        lblPicture.setForeground(Color.WHITE);

        lblPicture.setBorder(null);
        lblPicture.setBounds(0, 0, 330, 480);
        add(lblPicture);

        lblPicture.setFont(font);
        lblPicture.setOpaque(true);

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(
                    playerList.getPlayerFromIndex(playerAtI).getTavernAt(TavernAtI).getPicture().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image resizedImg = img.getScaledInstance(lblPicture.getWidth(), lblPicture.getHeight(), Image.SCALE_SMOOTH);

        lblPicture.setIcon(new ImageIcon(resizedImg));


        btnSell.addActionListener(this);
    }

    /**
     * What happens when a button is pressed
     */
    public void actionPerformed(ActionEvent e) {

        // Sell Taverns
        if (e.getSource() == btnSell) {

            Tavern tempTavern = playerList.getPlayerFromIndex(playerAtI).getTavernAt(TavernAtI);

            playerList.getPlayerFromIndex(playerAtI).sellTavern(tempTavern);

        }
    }


}
