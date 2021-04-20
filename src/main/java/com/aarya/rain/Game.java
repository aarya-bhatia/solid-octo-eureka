package com.aarya.rain;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.input.Keyboard;
import com.aarya.rain.level.Level;
import com.aarya.rain.level.RandomLevel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    public static boolean debug = false;

    public final static int width = 300;
    public final static int height = width / 16 * 9;
    public final static int scale = 3;
    public final static String title = "Rain";

    private Thread thread;
    private JFrame frame;
    private boolean running = false;
    private Screen screen = new Screen(width, height);
    private Keyboard key;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // main view
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    private Level level;

    public Game() {
        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        key = new Keyboard();
        addKeyListener(key);

        frame = new JFrame();
        requestFocus();

        level = new RandomLevel(64, 64);
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        final double ns = (1e9) / 60.0;
        long timer = System.currentTimeMillis();
        double delta = 0;
        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1) {
                update();
                delta--;
                updates++;
            }

            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer = System.currentTimeMillis();
                frame.setTitle(title + " | FPS: " + frames + " | UPS: " + updates);
                updates = frames = 0;
            }
        }
        stop();
    }

    public void update() {
        if(key.up) {
            screen.update(0, -1);
        }
        else if(key.down) {
            screen.update(0, 1);
        }
        if(key.right) {
            screen.update(1, 0);
        }
        else if(key.left) {
            screen.update(-1, 0);
        }
        key.update();
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();
        level.render(screen.xOff, screen.yOff, screen);

        System.arraycopy(screen.getPixels(), 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.frame.setResizable(false);
        game.frame.setTitle(title);
        game.frame.add(game);
        game.frame.pack();
        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);
        game.start();
    }
}