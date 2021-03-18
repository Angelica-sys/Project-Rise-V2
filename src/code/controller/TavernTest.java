package code.controller;

import code.model.player.Player;
import code.model.tiles.Tavern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Tavern object.
 * @author Angelica Asplund, Tor Stenfeldt
 * @date 2021-03-18
 */

class TavernTest {
    private String playerPath = "src/resources/images/players/";

    @Test
    @DisplayName("A Player should start with 1500 gold coins.")
    void testPlayer() {
        Player player = new Player("Hans", new ImageIcon(this.playerPath+"playerRed.jpg"), 0);
        assertEquals(1500 , player.getBalance());
    }

    @Test
    @DisplayName("The price of a Tavern should be a static 150.")
    void getTavernPrice() {
        Tavern tavern = new Tavern("Western Tavern", 10);
        assertEquals(150, tavern.getPrice());
    }

    @Test
    @DisplayName("The price of landing on a Tavern while that tile " +
            "is the only Tavern owned by that opponent should be 10.")
    void getRentOneTavern() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        Tavern west = new Tavern("Western Tavern", 10);
        west.purchase(playerA);
        playerA.addCapital(west);

        int price = west.getRent();
        assertEquals(10, price);
    }

    @Test
    @DisplayName("The price of landing on a Tavern while both taverns are owned by the same opponent should be 20.")
    void getRentTwoTaverns() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        Tavern west = new Tavern("Western Tavern", 10);
        west.purchase(playerA);
        playerA.addCapital(west);

        Tavern north = new Tavern("Northern Tavern", 10);
        north.purchase(playerA);
        playerA.addCapital(north);

        int price = west.getRent();
        assertEquals(20, price);
    }

    @Test
    @DisplayName("If a player sells a Tavern, it should be considered purchasable again.")
    void clearTavern() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        Tavern west = new Tavern("Western Tavern", 10);
        west.purchase(playerA);
        playerA.addCapital(west);

        west.clearTavern();
        playerA.removeCapital(west);

        assertTrue(west.isPurchasable());
    }

    @Test
    @DisplayName("If a player owned both Taverns and sold one, its rent should decrease.")
    void getRentSoldTavern() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        Tavern west = new Tavern("Western Tavern", 10);
        west.purchase(playerA);
        playerA.addCapital(west);

        Tavern north = new Tavern("Northern Tavern", 10);
        north.purchase(playerA);
        playerA.addCapital(north);

        west.clearTavern();
        playerA.removeCapital(west);

        int price = north.getRent();
        assertEquals(10, price);
    }

    @Test
    @DisplayName("If a player owned both Taverns and sold one, its rent should decrease.")
    void getRentSoldTavern2() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        Tavern west = new Tavern("Western Tavern", 10);
        west.purchase(playerA);
        playerA.addCapital(west);

        Tavern north = new Tavern("Northern Tavern", 10);
        north.purchase(playerA);
        playerA.addCapital(north);

        west.clearTavern();
        playerA.removeCapital(west);

        int price = west.getRent();
        assertEquals(10, price);
    }
}