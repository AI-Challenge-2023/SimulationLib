package net.aesten.simulation;

import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.visual.EnvVisualizer;

public abstract class Simulation {
    protected final GridEnvironment env;
    protected final EnvVisualizer window;

    public Simulation(GridEnvironment env, EnvVisualizer window) {
        this.env = env;
        this.window = window;
    }
}
