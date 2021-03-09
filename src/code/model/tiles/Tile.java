package code.model.tiles;

import javax.swing.*;
import java.awt.*;

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
        if (this instanceof Property) {
            ((Property) this).generateTileInfo();
        }else if(this instanceof Tavern){
            ((Tavern) this).generateTileInfo();
        }
        return description;
    }

    public Image getImage() {
        return image.getImage();
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
