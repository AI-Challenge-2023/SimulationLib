package net.aesten.simulation.data;

import net.aesten.simulation.component.Robot;
import net.aesten.simulation.environment.GridEnvironment;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public abstract class Action {
    protected Supplier<Boolean> condition;
    protected BiConsumer<Robot, GridEnvironment> action;

    public void executeActionIfPossible(Robot robot, GridEnvironment env) {
        if (condition == null) {
            if (action != null) {
                action.accept(robot, env);
                return;
            }
            return;
        }
        if (condition.get() && action != null) {
            action.accept(robot, env);
        }
    }
}
