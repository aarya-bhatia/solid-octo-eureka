package com.aarya.rain.entity;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x,y;
    private boolean removed = false;
    protected Level level;
    protected final static Random random = new Random();

    public void update() {

    }

    public void render(Screen screen) {

    }

    public void remove() {
        /* remove from level */
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }
}
