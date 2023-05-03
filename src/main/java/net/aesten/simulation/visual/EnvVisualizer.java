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
                int cellWidth = width / env.getX_length();
                int cellHeight = height / env.getY_length();
                int cellSize = Math.min(cellWidth, cellHeight);

                super.paintComponent(g);

                for (int row = 0; row < env.getY_length(); row++) {
                    for (int col = 0; col < env.getX_length(); col++) {
                        try {
                            g.setColor(env.getComponentAt(col, row).getColor());
                            g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
                        } catch (SimulationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
        };
        this.window = new JFrame(title);
        this.window.setSize(width, height + height / env.getX_length() + 22);
        this.window.setLocation(window_x, window_y);
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.add(panel);
        this.window.setVisible(true);
    }

    public void refresh() {
        this.window.validate();
        this.window.repaint();
    }
}
