package net.aesten.simulation.component;

import net.aesten.simulation.data.Position;

import java.awt.Color;

public class Obstacle extends Component {
    private static int dec = -1;
    public Obstacle(int x, int y, Color color) {
        super(dec--, new Position(x, y), color);
    }
}
