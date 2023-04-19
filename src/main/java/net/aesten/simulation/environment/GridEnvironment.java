package net.aesten.simulation.environment;

import net.aesten.simulation.component.Air;
import net.aesten.simulation.component.Component;
import net.aesten.simulation.data.Position;
import net.aesten.simulation.exceptions.SimulationException;

import java.util.ArrayList;
import java.util.List;

public class GridEnvironment {
    private final Component[][] grid;
    private final int rows;
    private final int columns;

    public GridEnvironment(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Component[rows][columns];

        for (int i = 0 ; i < rows ; i++) {
            for (int j = 0 ; j < columns ; j++) {
                grid[i][j] = new Air(i, j);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void addComponent(Component component) throws SimulationException {
        int x = component.getPosition().x();
        int y = component.getPosition().y();

        if (x >= rows || y >= columns || x < 0 || y < 0 || !(grid[x][y] instanceof Air)) {
            throw new SimulationException("Could not add component to simulation env");
        } else {
            grid[x][y] = component;
        }
    }

    public boolean isInGrid(int x, int y) {
        return (x >= 0 && x < rows && y >= 0 && y < columns);
    }

    public void moveComponent(Component component, int x_to, int y_to) {
        if (component.getPosition() == null) return;
        int x = component.getPosition().x();
        int y = component.getPosition().y();
        grid[x][y] = new Air(x, y);
        component.setPosition(new Position(x_to, y_to));
        grid[x_to][y_to] = component;
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
        for (int i = Math.max(0, x - radius) ; i < Math.min(x + radius, rows) ; i++) {
            for (int j = Math.max(0, y - radius) ; i < Math.min(y + radius, columns) ; i++) {
                components.add(grid[i][j]);
            }
        }
        return components;
    }
}
