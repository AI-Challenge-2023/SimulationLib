package net.aesten.simulation.data;

import net.aesten.simulation.component.Component;
import net.aesten.simulation.component.NonBlockingComponent;
import net.aesten.simulation.component.Robot;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;

public class SimpleMovementAction extends Action {
    private final GridEnvironment env;
    private final int x_to;
    private final int y_to;

    public SimpleMovementAction(GridEnvironment env, int x_to, int y_to) {
        this.env = env;
        this.x_to = x_to;
        this.y_to = y_to;
        super.condition = this::facingCellIsFree;
        super.action = this::moveForward;
    }

    public boolean facingCellIsFree() {
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

    public void moveForward(Robot robot, GridEnvironment env) {
        robot.setCurrentCellComponent(env.moveComponent(robot, x_to, y_to, robot.getCurrentCellComponent()));
    }
}

