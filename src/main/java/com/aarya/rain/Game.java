package com.aarya.rain;

import com.aarya.rain.graphics.Renderer;

import java.awt.*;

public class Game implements Runnable {

    private final int width = 300;
    private final int height = (width / 16 * 9);
    public static final int scale = 3;
    private final String title = "Rain";
    private final double update_cap = (1.0/60.0);
    private volatile boolean running = false;
    private final Window window;
    private final Renderer renderer;
    private final AbstractGame game;
    private Thread thread;
    public static boolean debug = false;

    public Game(AbstractGame game) {
        this.game = game;
        window = new Window(this);
        renderer = new Renderer(this, window);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidthScaled() {
        return width * scale;
    }

    public int getHeightScaled() {
        return height * scale;
    }

    public String getTitle() {
        return title;
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
        double firstTime;
        double lastTime = System.nanoTime()/1.0e9;
        double passedTime;
        double unprocessedTime = 0;
        double frameTime = 0;
        int frames = 0;
        boolean render;
        int fps = 0;

        while (running) {
            render = false;
            firstTime = System.nanoTime()/1.0e9;
            passedTime = firstTime-lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while (unprocessedTime >= update_cap) {
                unprocessedTime -= update_cap;
                render = true;

                /* update game */

                if(frameTime >= 1.0) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                    window.getJFrame().setTitle(title + " | FPS: " + fps);
                }
            }

            /* render game */
            if(render) {
                renderer.clear();
                game.update(this, (float) (1.0/60.0));
                game.render(this, renderer);
                renderer.drawText("FPS: " + fps, 0, 0, 0xff00ffff);
                window.update();
                frames++;
            }
            else {
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        stop();
    }
}