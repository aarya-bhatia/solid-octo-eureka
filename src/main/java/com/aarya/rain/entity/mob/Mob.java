package com.aarya.rain.entity.mob;

import com.aarya.rain.entity.Entity;
import com.aarya.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;

    public void move() {}

    public void update() {}

    /* Todo */
    private boolean collision(){
        return false;
    }
}
