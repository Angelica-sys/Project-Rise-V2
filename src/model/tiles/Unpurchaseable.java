package model.tiles;

import javax.swing.*;
import java.awt.*;

public abstract class Unpurchaseable {
    String name;
    String description;
    ImageIcon image;
    Color color;

    public Unpurchaseable(String name, String description, ImageIcon image, Color color) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.color = color;
    }
}
