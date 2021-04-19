package com.aarya.rain;

import javax.swing.*;
import java.awt.*;

public class Window {

    private JFrame frame;
    private Canvas canvas;

    public Window() {
        init();
    }

    public void init() {
        canvas = new Canvas();

        Dimension size = new Dimension(Game.width * Game.scale,Game.height * Game.scale);
        canvas.setPreferredSize(size);

        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(Game.title);
        frame.add(canvas);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
