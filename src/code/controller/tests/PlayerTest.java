package code.controller.tests;

import code.model.player.Player;
import code.model.tiles.Property;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Player object.
 * @author Tor Stenfeldt
 * @date 2021-03-23
 */
public class PlayerTest {
    private String playerPath = "src/resources/images/players/";
    private String propertyPath = "src/resources/images/tiles/";

    @Test
    @DisplayName("Player objects should be creatable in order to represent players.")
    void createPlayer() {
        Player p = new Player("Hans", new ImageIcon(this.playerPath+"playerRed.jpg"), 0);
        assertNotNull(p);
    }

    @Test
    @DisplayName("A player should be able to add a Property to their capital.")
    void purchaseProperty() {
        Player p = new Player("Hans", new ImageIcon(this.playerPath+"playerRed.jpg"), 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        p.addCapital(general);
        general.purchase(p);

        assertEquals(general, p.getCapital(0));
    }

    @Test
    @DisplayName("A player should be able to sell off purchased capital.")
    void sellProperty() {
        Player p = new Player("Hans", new ImageIcon(this.playerPath+"playerRed.jpg"), 0);

        ImageIcon imageGeneral = new ImageIcon(this.propertyPath + "general.png");
        Property general = new Property("House", 150, 10, 5, Color.GREEN, 100, imageGeneral);

        p.addCapital(general);
        general.purchase(p);
        p.removeCapital(general);

        assertEquals(0, p.getCapital().size());
    }

    @Test
    @DisplayName("A player should be able to get out of jail.")
    void goToJail() {
        Player p = new Player("Hans", new ImageIcon(this.playerPath+"playerRed.jpg"), 0);

        p.setIncarcerated(true);
        p.setIncarcerated(false);

        assertFalse(p.isIncarcerated());
    }
}
