package com.aarya.rain;

public class Game implements Runnable {

    public static int width = 300;
    public static int height = width / 16 * 9;
    public static float scale = 3.0f;

    /* We will render only (w x h) pixels but the screen size will be scaled up */

    private Thread thread;
    private volatile boolean running = false;

    public Game() {

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
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while(running) {

        }
    }
}
