package net.aesten.simulation;

import net.aesten.simulation.component.Component;
import net.aesten.simulation.component.Robot;
import net.aesten.simulation.component.Obstacle;
import net.aesten.simulation.environment.Cell;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;
import net.aesten.simulation.visual.EnvVisualizer;

import java.util.ArrayList;
import java.util.List;

public class SimpleSimulation extends Simulation{
    private final List<Robot> robots;
    private final List<Obstacle> obstacles;
    public SimpleSimulation(GridEnvironment env, EnvVisualizer window) {
        super(env, window);
        super.window.init();
        robots = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    public void addComponent(Component component) throws SimulationException {
        env.addComponent(component);
        if (component instanceof Robot robot) {
            robots.add(robot);
        } else if (component instanceof Obstacle obstacle) {
            obstacles.add(obstacle);
        }
    }

    public void removeComponent(Component component) {

    }

    public void step() {
        robots.forEach( robot -> {
            List<Cell> cells = robot.perceive(env);
            cells.stream().map(Cell::getComponent).forEach( component -> {
                if (component instanceof Robot other && other.getId() != robot.getId())
                    System.out.println("Robot " + robot.getId() + " perceived robot " + other.getId());
            });
            int i = 0;
            while (i < 4) {
                if (robot.checkNextCell(env)) {
                    robot.moveForward(env);
                    i = 4;
                } else {
                    robot.rotateCW();
                    i++;
                }
            }
        });
        window.refresh();
    }
}
