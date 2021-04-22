package com.aarya.rain.graphics;

import com.aarya.rain.Game;
import com.aarya.rain.input.Keyboard;
import com.aarya.rain.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window extends Canvas {

    private final JFrame frame;
    private final BufferedImage image;
    private final int[] pixels;
    private final Screen screen;
    private final Game game;

    public Window(Game game) {
        this.game = game;
        this.screen = new Screen(game.getWidth(), game.getHeight());

        this.setPreferredSize(new Dimension(game.getWidthScaled(), game.getHeightScaled()));

        this.image = new BufferedImage(game.getWidth(), game.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

        this.addKeyListener(Keyboard.INSTANCE);
        this.addMouseListener(Mouse.INSTANCE);
        this.addMouseMotionListener(Mouse.INSTANCE);
        this.requestFocus();

        this.frame = new JFrame();
        this.frame.setResizable(false);
        this.frame.setTitle(game.getTitle());
        this.frame.add(this);
        this.frame.pack();
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        int xScroll = this.game.getPlayer().getX() - this.game.getWidth() / 2;
        int yScroll = this.game.getPlayer().getY() - this.game.getHeight() / 2;

        this.game.getLevel().render(xScroll, yScroll, screen);
        this.game.getPlayer().render(screen);

        System.arraycopy(screen.getPixels(), 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();

        g.setFont(new Font("Verdana", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        if (Game.displayDebugOnWindow) {
            g.setColor(Color.WHITE);
            g.drawString(String.format("Map X: %d, Y: %d", this.game.getPlayer().getX(), this.game.getPlayer().getY()), 500, 400);
        }

//        g.setColor(Color.white);
//        g.fillRect(Mouse.getX() - 8, Mouse.getY() - 8, 16, 16);
//
//        g.drawString(String.format("MOUSE X:%d Y:%d Btn:%d",
//                Mouse.getX(), Mouse.getY(), Mouse.getButton()), 50, 50);

        g.dispose();
        bs.show();
    }

    public JFrame getJFrame() {
        return this.frame;
    }
}
