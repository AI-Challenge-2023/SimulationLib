package net.aesten.simulation;

import net.aesten.simulation.component.Robot;

import java.awt.*;

public class SimpleRobot extends Robot {
    private static Color robotColor = Color.GREEN;
    public SimpleRobot(int x, int y) {
        super(x, y, robotColor);
    }

    public static Color getRobotColor() {
        return robotColor;
    }

    public static void setRobotColor(Color robotColor) {
        SimpleRobot.robotColor = robotColor;
    }
}
