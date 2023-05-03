package net.aesten.simulation.data;

public enum Orientation {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    Orientation(int x_dir, int y_dir) {
        this.x_dir = x_dir;
        this.y_dir = y_dir;
    }

    public final int x_dir;
    public final int y_dir;
}
