package model.tiles;

import model.player.Player;

import javax.swing.*;

public abstract class Purchasable implements Tile {
    String name;
    String description;
    Player owner;
    ImageIcon image;
    boolean purchasable;

    public Purchasable(String name, String description, Player owner, ImageIcon image, boolean purchasable) {
        this.name = name;
        this.description = description;
        this.owner = owner;
        this.image = image;
        this.purchasable = purchasable;
    }
}
