package code.model.tiles;

import javax.swing.*;

public abstract class Tile {
    private String name;
    private String description;
    private ImageIcon image;

    public Tile(String name, String description, ImageIcon image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
