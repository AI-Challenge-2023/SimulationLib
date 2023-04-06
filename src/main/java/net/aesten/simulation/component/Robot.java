package net.aesten.simulation.component;

import net.aesten.simulation.data.Orientation;
import net.aesten.simulation.data.Position;
import net.aesten.simulation.environment.Cell;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;

import java.awt.Color;
import java.util.List;

public class Robot extends Component {
    private static int inc = 1;
    private static int perceptionRadius = 2;
    private Orientation orientation;

    public Robot(int x, int y, Color color) {
        super(inc++, new Position(x, y), color);
        this.orientation = Orientation.UP;
    }

    public static void setPerceptionRadius(int perceptionRadius) {
        Robot.perceptionRadius = perceptionRadius;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void rotateCW() {
        switch (orientation) {
            case UP -> orientation = Orientation.RIGHT;
            case RIGHT -> orientation = Orientation.DOWN;
            case DOWN -> orientation = Orientation.LEFT;
            case LEFT -> orientation = Orientation.UP;
        }
    }

    public boolean checkNextCell(GridEnvironment env) {
        if (env.isInGrid(position.x() + orientation.x_dir, position.y() + orientation.y_dir)) {
            try {
                Cell cell = env.getCellAt(position.x() + orientation.x_dir, position.y() + orientation.y_dir);
                if (cell.getComponent() instanceof NonBlockingComponent) return true;
            } catch (SimulationException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void moveForward(GridEnvironment env) {
        int x_to = position.x() + orientation.x_dir;
        int y_to = position.y() + orientation.y_dir;
        env.moveComponent(this, x_to, y_to);
        System.out.println("Robot " + getId() + " moved to (" + x_to + ", " + y_to + ")");
    }

    public List<Cell> perceive(GridEnvironment env) {
        return env.getCellsAround(position.x(), position.y(), perceptionRadius);
    }
}
