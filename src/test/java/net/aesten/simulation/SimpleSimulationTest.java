package net.aesten.simulation;

import net.aesten.simulation.component.Obstacle;
import net.aesten.simulation.component.Robot;
import net.aesten.simulation.data.Orientation;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;
import net.aesten.simulation.visual.EnvVisualizer;

import java.awt.*;

public class SimpleSimulationTest {
    public static void main(String[] args) throws SimulationException {
        GridEnvironment env = new GridEnvironment(15, 20);
        EnvVisualizer window = new EnvVisualizer(env, "Simple Simulation", 200, 100, 500, 500);
        SimpleSimulation sim = new SimpleSimulation(env, window);

        Robot r1 = new SimpleRobot(6, 10);
        Robot r2 = new SimpleRobot(3, 13);
        Robot r3 = new SimpleRobot(10, 5);
        Robot r4 = new SimpleRobot(14, 14);

        r1.setOrientation(Orientation.DOWN);
        r2.setOrientation(Orientation.LEFT);

        r1.setColor(Color.CYAN);

        Obstacle o1 = new Obstacle(14,12, Color.YELLOW);
        Obstacle o2 = new Obstacle(3,5, Color.BLACK);
        Obstacle o3 = new Obstacle(8,6, Color.RED);
        Obstacle o4 = new Obstacle(5,7, Color.GRAY);
        Obstacle o5 = new Obstacle(7,14, Color.PINK);
        Obstacle o6 = new Obstacle(12,4, Color.BLACK);

        sim.addComponent(r1);
        sim.addComponent(r2);
        sim.addComponent(r3);
        sim.addComponent(r4);
        sim.addComponent(o1);
        sim.addComponent(o2);
        sim.addComponent(o3);
        sim.addComponent(o4);
        sim.addComponent(o5);
        sim.addComponent(o6);

        for (int i = 3 ; i < 10 ; i++) {
            Obstacle o = new Obstacle(i, 2, Color.CYAN);
            sim.addComponent(o);
        }

        sim.simulate(30, 1000);
    }
}
