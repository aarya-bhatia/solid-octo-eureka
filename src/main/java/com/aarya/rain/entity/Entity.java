package com.aarya.rain.entity;

import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.level.Level;

public abstract class Entity {

    protected int x,y;
    protected Level level;

    public Entity() {}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public abstract void update();

    public abstract void render(Renderer renderer);
}
