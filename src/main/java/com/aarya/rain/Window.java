package com.aarya.rain;

import com.aarya.rain.input.Keyboard;
import com.aarya.rain.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private final Canvas canvas;
    private final JFrame frame;
    private final BufferedImage image;
    private BufferStrategy bs;
    private Graphics g;

    public Window(Game game) {
        image = new BufferedImage(game.getWidth(), game.getHeight(), BufferedImage.TYPE_INT_RGB);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(game.getWidthScaled(), game.getHeightScaled()));
        canvas.addKeyListener(Keyboard.INSTANCE);
        canvas.addMouseListener(Mouse.INSTANCE);
        canvas.addMouseMotionListener(Mouse.INSTANCE);
        canvas.requestFocus();

        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setTitle(game.getTitle());
        frame.add(this.canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();

        g.setFont(new Font("Verdana", Font.PLAIN, 30));
    }

    public void update() {
        g = canvas.getBufferStrategy().getDrawGraphics();
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        g.dispose();
        bs.show();
    }

    public JFrame getJFrame() {
        return this.frame;
    }

    public BufferedImage getImage() {
        return image;
    }
}
