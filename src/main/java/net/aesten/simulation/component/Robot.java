package net.aesten.simulation.component;

import net.aesten.simulation.data.Orientation;
import net.aesten.simulation.data.Position;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;

import java.awt.Color;
import java.util.List;

public class Robot extends Component {
    private static int inc = 1;
    private final int perceptionRadius;
    private Orientation orientation;
    private Component currentCellComponent = null;
    private int[] plannedDestination;

    public Robot(int x, int y, Color color, int perceptionRadius) {
        super(inc++, new Position(x, y), color);
        this.orientation = Orientation.UP;
        this.perceptionRadius = perceptionRadius;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setCurrentCellComponent(Component component) {
        this.currentCellComponent = component;
    }

    public Component getCurrentCellComponent() {
        return currentCellComponent;
    }

    public void rotateCW() {
        switch (orientation) {
            case UP -> orientation = Orientation.RIGHT;
            case RIGHT -> orientation = Orientation.DOWN;
            case DOWN -> orientation = Orientation.LEFT;
            case LEFT -> orientation = Orientation.UP;
        }
    }

    public boolean canMoveForward(GridEnvironment env) {
        if (env.isInGrid(position.x() + orientation.x_dir, position.y() + orientation.y_dir)) {
            try {
                Component component = env.getComponentAt(position.x() + orientation.x_dir, position.y() + orientation.y_dir);
                if (component instanceof NonBlockingComponent) return true;
            } catch (SimulationException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void moveForward(GridEnvironment env) {
        int x_to = position.x() + orientation.x_dir;
        int y_to = position.y() + orientation.y_dir;
        currentCellComponent = env.moveComponent(this, x_to, y_to, currentCellComponent);
    }

    public void planDestination(Orientation orientation) {
        planDestination(position.x() + orientation.x_dir, position.y() + orientation.y_dir);
    }

    public void planDestination(int x_to, int y_to) {
        plannedDestination = new int[]{x_to, y_to};
    }

    public void executePlan(GridEnvironment env) {
        if (plannedDestination != null) {
            currentCellComponent = env.moveComponent(this, plannedDestination[0], plannedDestination[1], currentCellComponent);
            plannedDestination = null;
        }
    }

    public List<Component> perceive(GridEnvironment env) {
        return perceive(env, perceptionRadius);
    }

    public List<Component> perceive(GridEnvironment env, int radius) {
        return env.getComponentsAround(position.x(), position.y(), radius);
    }
}
