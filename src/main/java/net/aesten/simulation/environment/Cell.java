package net.aesten.simulation.environment;

import net.aesten.simulation.component.Component;

public class Cell {
    private Component component;

    public Cell(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
