package net.aesten.simulation.data;

public enum Orientation {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    Orientation(int x_dir, int y_dir) {
        this.x_dir = x_dir;
        this.y_dir = y_dir;
    }

    public final int x_dir;
    public final int y_dir;
}
