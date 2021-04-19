package com.aarya.rain;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static int scale = 3;
    public static String title = "Rain";

    /* We will render only (w x h) pixels but the screen size will be scaled up */

    private Thread thread;
    private volatile boolean running = false;

    private Window window;

    public Game() {
    }

    public Window getWindow() {
        return window;
    }

    public synchronized void start() {
        running = true;

        window = new Window();

        thread = new Thread(this, "Display");
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(running) {

            update();

            window.render();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* 60 times per sec to ensure consistency */
    public void update() {

    }

    public static void main(String[] args) {
        Game g = new Game();
        g.start();
    }
}
