package net.aesten.simulation;

import net.aesten.simulation.component.Component;
import net.aesten.simulation.component.Obstacle;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;
import net.aesten.simulation.visual.EnvVisualizer;

import java.util.ArrayList;
import java.util.List;

public class SimpleSimulation extends Simulation{
    private final List<SimpleRobot> robots;
    private final List<Obstacle> obstacles;
    public SimpleSimulation(GridEnvironment env, EnvVisualizer window) {
        super(env, window);
        robots = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    @Override
    public void addComponent(Component component) throws SimulationException {
        env.addComponent(component);
        if (component instanceof SimpleRobot robot) {
            robots.add(robot);
        } else if (component instanceof Obstacle obstacle) {
            obstacles.add(obstacle);
        }
    }

    @Override
    public void removeComponent(Component component) {
    }

    @Override
    public void step() {
        robots.forEach( robot -> {
            List<Component> components = robot.perceive(env);
            components.forEach( component -> {
                if (component instanceof SimpleRobot other && other.getId() != robot.getId())
                    System.out.println("Robot " + robot.getId() + " perceived robot " + other.getId());
            });
            int i = 0;
            while (i < 4) {
                if (robot.nextCellIsFree(env)) {
                    robot.planDestination(env, robot.getOrientation());
                    i = 4;
                } else {
                    robot.rotateCW();
                    i++;
                }
            }
        });
        robots.forEach(robot -> robot.executePlan(env));
    }
}
