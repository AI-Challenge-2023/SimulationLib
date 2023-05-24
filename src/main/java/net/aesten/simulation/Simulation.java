package net.aesten.simulation;

import net.aesten.simulation.component.Component;
import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;
import net.aesten.simulation.visual.EnvVisualizer;
import net.aesten.simulation.visual.VisualizerParameters;

public abstract class Simulation {
    protected final GridEnvironment env;
    protected final EnvVisualizer window;

    public Simulation(GridEnvironment env, EnvVisualizer window) {
        this.env = env;
        this.window = window;
        this.window.init();
    }

    public Simulation(GridEnvironment env, VisualizerParameters params) {
        this(env, new EnvVisualizer(env, params));
    }

    public abstract void addComponent(Component component) throws SimulationException;
    public abstract void removeComponent(Component component);
    public abstract void step(int currentStep);

    public void simulate(int steps, int sleepMillis) {
        for (int i = 0 ; i < steps ; i++) {
            step(i);
            window.refresh();
            try {
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                System.out.println("Failed sleeping for " + sleepMillis + "ms");
            }
        }
    }

    public void closeWindow() {
        window.close();
    }
}
