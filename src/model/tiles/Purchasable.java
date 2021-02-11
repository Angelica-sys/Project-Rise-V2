package model.tiles;

import model.player.Player;

import javax.swing.*;

public abstract class Purchasable extends Tile {
    private Player owner;
    private ImageIcon image;
    private boolean purchasable;

    public Purchasable(String name, String description, Player owner, ImageIcon image, boolean purchasable) {
        super(name, description, image);
        this.owner = owner;
        this.image = image;
        this.purchasable = purchasable;
    }

    public Player getOwner() {
        return this.owner;
    }

    public ImageIcon getImage() {
        return this.image;
    }

    public boolean isPurchasable() {
        return this.purchasable;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }
}
