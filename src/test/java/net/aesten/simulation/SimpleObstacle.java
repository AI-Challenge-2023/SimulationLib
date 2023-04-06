package net.aesten.simulation;

import net.aesten.simulation.component.Obstacle;

import java.awt.*;

public class SimpleObstacle extends Obstacle {
    private final String name;
    public SimpleObstacle(int x, int y, Color color, String name) {
        super(x, y, color);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
