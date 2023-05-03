package net.aesten.simulation.environment;

import net.aesten.simulation.component.Air;
import net.aesten.simulation.component.Component;
import net.aesten.simulation.component.NonBlockingComponent;
import net.aesten.simulation.data.Position;
import net.aesten.simulation.exceptions.SimulationException;

import java.util.ArrayList;
import java.util.List;

public class GridEnvironment {
    private final Component[][] grid;
    private final int y_length;
    private final int x_length;

    public GridEnvironment(int rows, int columns) {
        this.y_length = rows;
        this.x_length = columns;
        this.grid = new Component[columns][rows];

        for (int i = 0 ; i < rows ; i++) {
            for (int j = 0 ; j < columns ; j++) {
                grid[j][i] = new Air(i, j);
            }
        }
    }

    public int getY_length() {
        return y_length;
    }

    public int getX_length() {
        return x_length;
    }

    public Component addComponent(Component component) throws SimulationException {
        int x = component.getPosition().x();
        int y = component.getPosition().y();

        if (x >= x_length || y >= y_length || x < 0 || y < 0) {
            throw new SimulationException("Tried to add component outside of simulation range");
        } else if (!(grid[x][y] instanceof NonBlockingComponent)) {
            throw new SimulationException("Tried replacing a blocking component");
        } else {
            Component previousComponent = grid[x][y];
            grid[x][y] = component;
            return previousComponent;
        }
    }

    public boolean isInGrid(int x, int y) {
        return (x >= 0 && x < x_length && y >= 0 && y < y_length);
    }

    public Component moveComponent(Component movingComponent, int x_to, int y_to, Component replacedComponent) {
        if (movingComponent.getPosition() == null) return null;
        int x = movingComponent.getPosition().x();
        int y = movingComponent.getPosition().y();
        if (replacedComponent != null) {
            grid[x][y] = replacedComponent;
        }
        movingComponent.setPosition(new Position(x_to, y_to));
        Component previousComponent = grid[x_to][y_to];
        grid[x_to][y_to] = movingComponent;
        return previousComponent;
    }

    public Component getComponentAt(int x, int y) throws SimulationException {
        try {
            return grid[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SimulationException("Tried to fetch position outside simulation range");
        }
    }

    public List<Component> getComponentsAround(int x, int y, int radius) {
        List<Component> components = new ArrayList<>();
        for (int i = Math.max(0, x - radius); i < Math.min(x + radius, x_length) ; i++) {
            for (int j = Math.max(0, y - radius); i < Math.min(y + radius, y_length) ; i++) {
                components.add(grid[i][j]);
            }
        }
        return components;
    }
}
