package net.aesten.simulation.component;

import net.aesten.simulation.data.Position;

import java.awt.Color;

public abstract class NonBlockingComponent extends Component{
    public NonBlockingComponent(int x, int y, Color color) {
        super(0, new Position(x, y), color);
    }
}
