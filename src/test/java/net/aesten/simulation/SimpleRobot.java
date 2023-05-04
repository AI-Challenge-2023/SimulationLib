package net.aesten.simulation;

import net.aesten.simulation.component.Robot;
import net.aesten.simulation.data.Orientation;

import java.awt.*;

public class SimpleRobot extends Robot {
    private static Color robotColor = Color.GREEN;
    public SimpleRobot(int x, int y) {
        super(x, y, robotColor, 2);
    }

    public static Color getRobotColor() {
        return robotColor;
    }

    public static void setRobotColor(Color robotColor) {
        SimpleRobot.robotColor = robotColor;
    }

    public void rotateCW() {
        switch (super.orientation) {
            case UP -> orientation = Orientation.RIGHT;
            case RIGHT -> orientation = Orientation.DOWN;
            case DOWN -> orientation = Orientation.LEFT;
            case LEFT -> orientation = Orientation.UP;
        }
    }
}
