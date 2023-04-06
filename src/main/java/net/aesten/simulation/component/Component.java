package net.aesten.simulation.component;

import net.aesten.simulation.data.Position;

import java.awt.*;

public abstract class Component {
    private final int id;
    protected Position position;
    private Color color;

    public Component(int id, Position position, Color color) {
        this.id = id;
        this.position = position;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
