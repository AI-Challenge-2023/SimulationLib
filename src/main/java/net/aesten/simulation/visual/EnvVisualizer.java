package net.aesten.simulation.visual;

import net.aesten.simulation.environment.GridEnvironment;
import net.aesten.simulation.exceptions.SimulationException;

import javax.swing.*;
import java.awt.*;

public class EnvVisualizer {
    private final GridEnvironment env;
    private final int width;
    private final int height;
    private final int window_x;
    private final int window_y;
    private final String title;
    private JFrame window;

    public EnvVisualizer(GridEnvironment env, String title, int window_x, int window_y, int width, int height) {
        this.env = env;
        this.window_x = window_x;
        this.window_y = window_y;
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public EnvVisualizer(GridEnvironment env, VisualizerParameters params) {
        this(env, params.title(), params.window_x(), params.window_y(), params.width(), params.height());
    }

    public void init() {
        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                int x_ratio = width / EnvVisualizer.this.env.getRows();
                int y_ratio = height / EnvVisualizer.this.env.getColumns();

                super.paintComponent(g);

                for (int row = 0; row < EnvVisualizer.this.env.getRows(); ++row) {
                    for (int col = 0; col < EnvVisualizer.this.env.getColumns(); ++col) {
                        try {
                            g.setColor(EnvVisualizer.this.env.getCellAt(row, col).getComponent().getColor());
                            g.fillRect(col * x_ratio, row * y_ratio, x_ratio, y_ratio);
                        } catch (SimulationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            public Dimension getPreferredSize() {
                return new Dimension(EnvVisualizer.this.width, EnvVisualizer.this.height);
            }
        };
        this.window = new JFrame(this.title);
        this.window.setSize(this.width, this.height + height / this.env.getColumns() + 22);
        this.window.setLocation(this.window_x, this.window_y);
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.add(panel);
        this.window.setVisible(true);
    }

    public void refresh() {
        this.window.validate();
        this.window.repaint();
    }
}
