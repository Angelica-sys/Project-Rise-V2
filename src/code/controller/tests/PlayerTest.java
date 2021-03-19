package code.controller.tests;

import code.model.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the Player object.
 * @author Tor Stenfeldt
 * @date 2021-03-19
 */
public class PlayerTest {
    private String playerPath = "src/resources/images/players/";

    @Test
    @DisplayName("Player objects should be creatable in order to represent players.")
    void createPlayer() {
        Player p = new Player("Hans", new ImageIcon(this.playerPath+"playerRed.jpg"), 0);
        assertNotNull(p);
    }

    void purchaseProperty() {

    }

    void sellProperty() {

    }

    //TODO:
    // put in jail
    // bail out of jail
    // check number of properties
    // check number of taverns
    // test setting position
    // test having passed go.
    // test ranking up
    // test ranking down
}
