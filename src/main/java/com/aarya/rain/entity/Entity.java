package com.aarya.rain.entity;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.level.Level;

import java.util.Random;

public abstract class Entity {

    public int x,y;
    private boolean removed = false;
    protected Level level;
    protected final static Random random = new Random();

    public abstract void update();

    public abstract void render(Screen screen);

    public void remove() {
        removed = true;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isRemoved() {
        return removed;
    }
}
