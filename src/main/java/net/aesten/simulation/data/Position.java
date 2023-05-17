package net.aesten.simulation.data;

public record Position(int x, int y) {
    public Position addOrientation(Orientation orientation) {
        return new Position(x + orientation.x_dir, y + orientation.y_dir);
    }
}
