package com.aarya.rain;

import com.aarya.rain.entity.mob.Player;
import com.aarya.rain.graphics.Window;
import com.aarya.rain.input.Keyboard;
import com.aarya.rain.level.Level;
import com.aarya.rain.level.SpawnLevel;

public class Game implements Runnable {

    private final int WIDTH = 300;
    private final int HEIGHT = (WIDTH / 16 * 9);
    private final int SCALE = 3;
    private final String TITLE = "Rain";
    private final double ns = (1e9) / 60.0;

    public static boolean debug = false;

    private volatile boolean running = false;
    private final Player player;
    private final Level level;
    private final Window window;
    private Thread thread;

    public Game() {
        window = new Window(this);
        level = new SpawnLevel("/textures/level.png", 16, 16);
        player = new Player(0, HEIGHT / 2, Keyboard.INSTANCE);
        player.setLevel(level);
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public int getWidthScaled() {
        return WIDTH * SCALE;
    }

    public int getHeightScaled() {
        return HEIGHT * SCALE;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return TITLE;
    }

    public Level getLevel() {
        return level;
    }

    public Window getWindow() {
        return window;
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this, "Game");
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

            window.render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer = System.currentTimeMillis();
                window.getJFrame().setTitle(TITLE + " | FPS: " + frames + " | UPS: " + updates);
                updates = frames = 0;
            }
        }
        stop();
    }

    public void update() {
        player.update();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

}