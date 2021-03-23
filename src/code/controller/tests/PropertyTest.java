package code.controller.tests;

import code.model.player.Player;
import code.model.player.PlayerRanks;
import code.model.tiles.Property;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Property object.
 * @author Tor Stenfeldt
 * @date 2021-03-19
 */
public class PropertyTest {
    private String playerPath = "src/resources/images/players/";
    private String propertyPath = "src/resources/images/tiles/";

    @Test
    @DisplayName("Whenever a Property is purchased, its owner should be changed.")
    void purchaseProperty() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        assertEquals(playerA, general.getOwner());
    }

    @Test
    @DisplayName("When a property is sold off, its owner should be set to null.")
    void sellProperty() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        playerA.removeCapital(general);
        general.clearProperty();

        assertNull(general.getOwner());
    }

    @Test
    @DisplayName("A player should be able to upgrade a property, increasing its rent.")
    void upgradeProperty() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        int rent = general.getRent();
        general.increaseLevel();
        int newRent = rent + general.getRentPerLevel();

        assertEquals(newRent, general.getRent());
    }

    @Test
    @DisplayName("If a player is out of money they should not be able to upgrade their properties.")
    void downgradeWhileBroke() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        playerA.setBalance(10);

        boolean result = general.increaseLevel();
        assertFalse(result);
    }

    @Test
    @DisplayName("Player rank should limit the maximum property level.")
    void upgradeAtMaxLevel() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();

        assertEquals(1, general.getLevel());
    }

    @Test
    @DisplayName("A higher player rank should allow higher property levels.")
    void upgradeAtHigherRank() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        playerA.setPlayerRank(PlayerRanks.KNIGHT);

        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();

        assertEquals(3, general.getLevel());
    }

    @Test
    @DisplayName("A player should be able to downgrade property.")
    void downgradeProperty() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        playerA.setPlayerRank(PlayerRanks.KNIGHT);

        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();

        general.decreaseLevel();

        assertEquals(2, general.getLevel());
    }

    @Test
    @DisplayName("A property shouldn't be downgradable to a negative level.")
    void downgradeAtLowest() {
        ImageIcon imageA = new ImageIcon(this.playerPath + "playerRed.jpg");
        Player playerA = new Player("Player A", imageA, 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        playerA.addCapital(general);
        general.purchase(playerA);

        playerA.setPlayerRank(PlayerRanks.KNIGHT);

        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();
        general.increaseLevel();

        general.decreaseLevel();
        general.decreaseLevel();
        general.decreaseLevel();
        general.decreaseLevel();
        general.decreaseLevel();

        assertEquals(0, general.getLevel());
    }
}
