package model.tiles;

import model.player.Player;

import javax.swing.*;

public abstract class Purchasable extends Tile {
    private Player owner;
    private ImageIcon image;
    private boolean purchasable;
    private int rent;

    public Purchasable(String name, String description, Player owner, int rent, ImageIcon image, boolean purchasable) {
        super(name, description, image);
        this.owner = owner;
        this.rent = rent;
        this.image = image;
        this.purchasable = purchasable;
    }

    public Player getOwner() {
        return this.owner;
    }

    public int getRent() {
        return rent;
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

    public void setRent(int rent) {
        this.rent = rent;
    }

    public void setPurchasable(boolean purchasable) {
        this.purchasable = purchasable;
    }
}
