package com.aarya.rain.entity.mob;

import com.aarya.rain.entity.Entity;
import com.aarya.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 0;
    protected boolean moving = false;

    public void move(int dx, int dy) {
        /*
         * North = 0, East = 1, South = 2, West = 3
         */
        if(dx > 0) dir = 1;
        if(dx < 0) dir = 3;
        if(dy > 0) dir = 2;
        if(dy < 0) dir = 0;

        if(!collision()) {
            x += dx;
            y += dy;
        }
    }

    public void update() {}

    private boolean collision(){
        return false;
    }

    public void render() {
    }
}
