package code.view.startMenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import code.view.GamePanels;
import code.model.player.PlayerList;

/**
 * First screen a player sees, here he is able to choose the amount of players and
 * what names and colors the players will have during the game.
 * @author Aevan Dino, Chanon Borgström, Lanna Maslo, Tor Stenfeldt
 */
public class StartingScreen extends JFrame {
    private BackgroundMusic bgm = new BackgroundMusic();
    private PlayerList playerList = new PlayerList();
    private GamePanels mainWindow = new GamePanels(bgm);

    private JButton btnConfirm = new JButton("Confirm");
    private JButton btnStartGame = new JButton("Start Game");
    private JButton btnReset = new JButton("Reset");

    String imageSource = "src/resources/images/popups/fancyRoll.jpg";
    private ImageIcon imgBackground = new ImageIcon(
            new ImageIcon(imageSource).getImage().getScaledInstance(900, 860, Image.SCALE_SMOOTH)
    );

    private Font fontRadioButtons = new Font("Gabriola", Font.PLAIN, 24);
    private Font fontHeader = new Font("Gabriola", Font.BOLD, 92);
    private Font fontLabel = new Font("Gabriola", Font.BOLD, 42);
    private Font fontLabelPlayer = new Font("Gabriola", Font.BOLD, 30);

    private JPanel pnlPlayerInfo = new JPanel();

    private JRadioButton[] radioButtons = new JRadioButton[4];
    private ButtonGroup btnGroup = new ButtonGroup();

    private JLabel lblPlayer = new JLabel("How many players?");
    private JLabel lblBackground = new JLabel("", imgBackground, JLabel.CENTER);
    private JLabel lblRise = new JLabel("RISE");

    private JLabel lblPlayerIndex1 = new JLabel("Player 1:");
    private JLabel lblPlayerIndex2 = new JLabel("Player 2:");
    private JLabel lblPlayerIndex3 = new JLabel("Player 3:");
    private JLabel lblPlayerIndex4 = new JLabel("Player 4:");
    private JLabel[] playerLabels = new JLabel[]{lblPlayerIndex1, lblPlayerIndex2, lblPlayerIndex3, lblPlayerIndex4};

    private JTextField tfPlayer1 = new JTextField("Name1...");
    private JTextField tfPlayer2 = new JTextField("Name2...");
    private JTextField tfPlayer3 = new JTextField("Name3...");
    private JTextField tfPlayer4 = new JTextField("Name4...");
    private JTextField[] playerTf = new JTextField[]{tfPlayer1, tfPlayer2, tfPlayer3, tfPlayer4};

    private JCheckBox mute = new JCheckBox("Mute music");
    private int amountOfPlayers;

    private JComboBox<String>[] playerColors;

    public StartingScreen() {
        String[] colors = new String[] {"RED", "GREEN", "ORANGE", "YELLOW", "CYAN", "MAGENTA"};

        this.playerColors = new JComboBox[] {
                new JComboBox<>(colors),
                new JComboBox<>(colors),
                new JComboBox<>(colors),
                new JComboBox<>(colors)
        };

        for (int i=0; i<this.playerColors.length; i++) {
            this.playerColors[i].setSelectedIndex(i);
        }

        initializeGUI();
    }

    /**
     * Method to initilize the GUI.
     */
    public void initializeGUI() {

        // JPanel for information about players
        this.pnlPlayerInfo.setBounds(0, 0, 900, 830);
        this.pnlPlayerInfo.setOpaque(false);
        this.pnlPlayerInfo.setLayout(null);

        // Label used to create a background
        this.lblBackground.setBounds(0, 0, 900, 830);
        this.lblBackground.setIcon(imgBackground);
        this.lblBackground.setLayout(null);

        // Header reading "RISE"
        this.lblRise.setFont(fontHeader);
        this.lblRise.setBounds(375, 125, 175, 200);
        this.lblBackground.add(lblRise);

        // JLabel reading "How many players?"
        this.lblPlayer.setFont(fontLabel);
        this.lblPlayer.setBounds(315, 175, 300, 200);

        // Create three JRadioButtons
        initRadioButtons();
        initPlayers();
        initButtons();
        initFrame();
    }

    public void initFrame() {
        this.setSize(900, 830);
        this.setTitle("Configure the different players.");
        this.setLayout(null);
        this.setVisible(false);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void start() {
        this.setVisible(true);
        this.bgm.startMusic();
    }

    /**
     * Methods create radio buttons using for-loop
     */
    public void initRadioButtons() {
        for (int i = 0; i < 3; i++) {
            JRadioButton btnRadio = new JRadioButton((i + 2) + "");
            btnRadio.setBounds(375 + i * 65, 275, 50, 50);
            btnRadio.setFont(fontRadioButtons);
            btnRadio.setOpaque(false);
            btnGroup.add(btnRadio);
            radioButtons[i] = btnRadio;
            add(btnRadio);
        }
    }

    /**
     * Creates all players, textfields, labels and color choice boxes
     */
    public void initPlayers() {
        for (int i = 0; i < 4; i++) {
            playerLabels[i].setBounds(280, 360 + i * 40, 150, 50);
            playerLabels[i].setFont(fontLabelPlayer);
            playerLabels[i].setVisible(false);

            playerTf[i].setBounds(375, 360 + i * 40, 150, 30);
            playerTf[i].setVisible(false);
            playerTf[i].addMouseListener(new MouseAction());

            playerColors[i].setBounds(530, 360 + i * 40, 100, 30);
            playerColors[i].setVisible(false);

            pnlPlayerInfo.add(playerLabels[i]);
            pnlPlayerInfo.add(playerTf[i]);
            pnlPlayerInfo.add(playerColors[i]);
        }
    }

    private void initButtons() {
        // Confirm button
        this.btnConfirm.setBounds(375, 315, 150, 30);
        this.btnConfirm.addActionListener(e -> confirm());

        // Start game button
        this.btnStartGame.setBounds(350, 530, 200, 40);
        this.btnStartGame.setVisible(false);
        this.btnStartGame.addActionListener(e -> {
            if (checkNames() && checkColours()) {
                startUpGame();
            }
        });

        // Rest button
        this.btnReset.setBounds(375, 575, 150, 30);
        this.btnReset.setVisible(false);
        this.btnReset.addActionListener(e -> btnPressed(3, false));

        // Mute button
        this.mute.setBounds(2, 740, 150, 35);
        this.mute.setForeground(Color.white);
        this.mute.setFont(fontRadioButtons);
        this.mute.setOpaque(false);
        this.mute.addActionListener(e -> toggleMute());

        // Adding stuff to background label
        this.lblBackground.add(lblRise);
        this.lblBackground.add(lblPlayer);
        this.lblBackground.add(btnConfirm);
        this.lblBackground.add(pnlPlayerInfo);
        this.lblBackground.add(btnReset);
        this.lblBackground.add(btnStartGame);
        this.lblBackground.add(mute);
        this.add(lblBackground);
    }

    /**
     * Checks if the player names are unique
     * @return false if invalid name, true if valid name
     * @author Chanon Borgström, Lanna Maslo, Tor Stenfeldt
     */
    private boolean checkNames() {
        String[] playerNames = new String[4];
        playerNames[0] = this.tfPlayer1.getText();
        playerNames[1] = this.tfPlayer2.getText();
        playerNames[2] = this.tfPlayer3.getText();
        playerNames[3] = this.tfPlayer4.getText();

        for (String s : playerNames) {
            if (s.length() == 0) {
                JOptionPane.showMessageDialog(null, "All players must have a name");
                return false;
            }
        }

        for (int i=0; i<playerNames.length-1; i++) {
            for (int j=i+1; j<playerNames.length; j++) {
                if (playerNames[i].equals(playerNames[j])) {
                    JOptionPane.showMessageDialog(null, "Two players can not have the same name");
                    return false;
                } else if(!validateName(playerNames[i])) {
                    JOptionPane.showMessageDialog(null, playerNames[i] + " is not a valid name");
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the name is more than three characters long and has no blank spaces in the beginning or end
     * @return false if invalid name, true if valid name (according to regex)
     * @author Chanon Borgström, Lanna Maslo
     */
    private boolean validateName(String inputName){
        return inputName.matches("^[^\\s].+[^\\s]${3,10}");
    }

    /**
     * Checks if the users have chosen unique colors
     * @author Chanon Borgström, Lanna Maslo, Tor Stenfeldt
     */
    private boolean checkColours() {
        boolean approved = true;

        outer: for (int i=0; i<this.amountOfPlayers; i++) {
            for (int j=i+1; j<this.amountOfPlayers; j++) {
                if (this.playerColors[i].getSelectedIndex() == this.playerColors[j].getSelectedIndex()) {
                    approved = false;
                    String s = "Two players are not allowed to have the same color";
                    JOptionPane.showMessageDialog(null, s);
                    break outer;
                }
            }

        }

        return approved;
    }

    /**
     * Method called when player clicks start game
     */
    private void startUpGame() {
        createNewUsers();
        this.mainWindow.addPlayer(this.playerList);
        this.mainWindow.startboard();
        dispose();
        Introduction intro = new Introduction(this.playerList);
    }

    /**
     * Creates the right amount of players.
     */
    private void createNewUsers() {
        for (int i=0; i<this.amountOfPlayers; i++) {
            if (this.playerTf[i].getText().length() >= 10) {
                this.playerTf[i].setText(this.playerTf[i].getText().substring(0, 10));
            }

            this.playerList.addNewPlayer(this.playerTf[i].getText(), (String) this.playerColors[i].getSelectedItem());
        }
    }

    private void confirm() {
        if (this.radioButtons[0].isSelected()) {
            btnPressed(2, true);
            this.amountOfPlayers = 2;
        } else if (radioButtons[1].isSelected()) {
            btnPressed(3, true);
            this.amountOfPlayers = 3;

        } else if (radioButtons[2].isSelected()) {
            btnPressed(4, true);
            this.amountOfPlayers = 4;
        }
    }

    /**
     * Whenever player chooses to reset the start screen
     * @param amountOfPlayers, how many players to draw
     * @param bool,            boolean indicating whether or not components should be visible.
     */
    private void btnPressed(int amountOfPlayers, boolean bool) {
        for (int i=0; i<amountOfPlayers; i++) {
            this.playerLabels[i].setVisible(bool);
            this.playerTf[i].setVisible(bool);
            this.playerColors[i].setVisible(bool);
        }

        this.btnStartGame.setVisible(bool);
        this.btnReset.setVisible(bool);
        this.btnConfirm.setEnabled(!bool);
    }

    private void toggleMute() {
        if (this.mute.isSelected()) {
            this.bgm.pauseMusic();
        } else {
            this.bgm.startMusic();
        }
    }

    /**
     * MouseClickedListener for the name inserting so the text disappear when the player clicks.
     */
    private class MouseAction implements MouseListener {
        int counter1 = 0, counter2 = 0, counter3 = 0, counter4 = 0;

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == tfPlayer1) {
                if (counter1 < 1) {
                    counter1++;
                    tfPlayer1.setText(null);
                }
            }
            if (e.getSource() == tfPlayer2) {
                if (counter2 < 1) {
                    counter2++;
                    tfPlayer2.setText(null);
                }
            }
            if (e.getSource() == tfPlayer3) {
                if (counter3 < 1) {
                    counter3++;
                    tfPlayer3.setText(null);
                }
            }
            if (e.getSource() == tfPlayer4) {
                if (counter4 < 1) {
                    counter4++;
                    tfPlayer4.setText(null);
                }
            }
        }

        /**
         * Nothing will happen with the other implemented methods. Methods must be implemented by MouseListener.
         */
        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        public void mouseReleased(MouseEvent e) {
        }
    }
}