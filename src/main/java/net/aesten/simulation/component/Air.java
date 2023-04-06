package net.aesten.simulation.component;

import java.awt.Color;

public class Air extends NonBlockingComponent {
    private static Color airColor = Color.WHITE;
    public Air(int x, int y) {
        super(x, y, airColor);
    }

    public static Color getAirColor() {
        return airColor;
    }

    public static void setAirColor(Color airColor) {
        Air.airColor = airColor;
    }
}
