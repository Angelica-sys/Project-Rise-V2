package code.model.tiles;

import code.model.player.Player;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for Tavern.
 * @author Angelica Asplund
 */

class TavernTest {

    @Test
    void testPlayer() {
        Player player = new Player("Hans", new ImageIcon(), 1);
        assertEquals(1500 , player.getBalance());
    }

    @Test
    void getPriceNorthernTavern() {
        Tavern tavern = new Tavern("Northern Tavern", 10);
        assertEquals(150, tavern.getPrice());
    }
    @Test
    void getPriceWesternTavern() {
        Tavern tavern = new Tavern("Western Tavern", 10);
        assertEquals(150, tavern.getPrice());
    }

    @Test
    void getTileInfo() {
    }

    @Test
    void clearTavern() {
    }
}