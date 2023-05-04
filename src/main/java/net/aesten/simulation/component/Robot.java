package net.aesten.simulation.component;

import net.aesten.simulation.data.Action;
import net.aesten.simulation.data.Orientation;
import net.aesten.simulation.data.Position;
import net.aesten.simulation.data.SimpleMovementAction;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;

import java.awt.Color;
import java.util.List;

public abstract class Robot extends Component {
    private static int inc = 1;
    protected final int perceptionRadius;
    protected Orientation orientation;
    protected Component currentCellComponent = null;
    protected Action plannedAction;

    public Robot(int x, int y, Color color, int perceptionRadius) {
        this(x, y, color, Orientation.UP, perceptionRadius);
    }

    public Robot(int x, int y, Color color, Orientation orientation, int perceptionRadius) {
        super(inc++, new Position(x, y), color);
        this.orientation = orientation;
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

    public boolean nextCellIsFree(GridEnvironment env) {
        int x_to = position.x() + orientation.x_dir;
        int y_to = position.y() + orientation.y_dir;
        if (env.isInGrid(x_to, y_to)) {
            try {
                Component component = env.getComponentAt(x_to, y_to);
                if (component instanceof NonBlockingComponent) return true;
            } catch (SimulationException e) {
                return false;
            }
        }
        return false;
    }

    public void planDestination(GridEnvironment env, Orientation orientation) {
        planDestination(env, position.x() + orientation.x_dir, position.y() + orientation.y_dir);
    }

    public void planDestination(GridEnvironment env, int x_to, int y_to) {
        plannedAction = new SimpleMovementAction(env, x_to, y_to);
    }

    public void executePlan(GridEnvironment env) {
        if (plannedAction != null) {
            plannedAction.executeActionIfPossible(this, env);
            plannedAction = null;
        }
    }

    public List<Component> perceive(GridEnvironment env) {
        return perceive(env, perceptionRadius);
    }

    public List<Component> perceive(GridEnvironment env, int radius) {
        return env.getComponentsAround(position.x(), position.y(), radius);
    }

}
