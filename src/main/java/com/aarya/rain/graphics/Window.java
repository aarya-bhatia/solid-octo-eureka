package com.aarya.rain.graphics;

import com.aarya.rain.Game;
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
    private final Game game;
    private final Renderer renderer;

    public Window(Game game) {
        this.game = game;

        this.canvas = new Canvas();
        this.canvas.setPreferredSize(new Dimension(game.getWidthScaled(), game.getHeightScaled()));

        this.image = new BufferedImage(game.getWidth(), game.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.renderer = new Renderer(image, game.getWidth(), game.getHeight());

        this.canvas.addKeyListener(Keyboard.INSTANCE);
        this.canvas.addMouseListener(Mouse.INSTANCE);
        this.canvas.addMouseMotionListener(Mouse.INSTANCE);
        this.canvas.requestFocus();

        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle(game.getTitle());
        this.frame.add(this.canvas);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        if (this.canvas.getBufferStrategy() == null) {
            this.canvas.createBufferStrategy(3);
        }
    }

    public void render() {
        BufferStrategy buffer = this.canvas.getBufferStrategy();

        renderer.clear();

        int xScroll = this.game.getPlayer().getX() - this.game.getWidth() / 2;
        int yScroll = this.game.getPlayer().getY() - this.game.getHeight() / 2;

        this.renderer.setXOff(xScroll);
        this.renderer.setYOff(yScroll);

        this.game.getLevel().render(xScroll, yScroll, this.renderer);
//        this.game.getPlayer().render(renderer);

        Graphics g = buffer.getDrawGraphics();

        g.setFont(new Font("Verdana", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

        g.setColor(Color.WHITE);
        g.drawString(String.format("Map X: %d, Y: %d", this.game.getPlayer().getX(), this.game.getPlayer().getY()), 500, 400);

        g.setColor(Color.white);
        g.fillRect(Mouse.getX() - 8, Mouse.getY() - 8, 16, 16);

        g.drawString(String.format("MOUSE X:%d Y:%d Btn:%d",
                Mouse.getX(), Mouse.getY(), Mouse.getButton()), 50, 50);

        g.dispose();

        buffer.show();
    }

    public JFrame getJFrame() {
        return this.frame;
    }
}
