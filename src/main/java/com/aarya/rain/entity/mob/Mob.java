package com.aarya.rain.entity.mob;

import com.aarya.rain.entity.Entity;
import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected boolean moving = false;
    protected int dir = 0;

    public void move(int dx, int dy) {
        if(dx > 0) dir = 1;
        if(dx < 0) dir = 3;
        if(dy > 0) dir = 2;
        if(dy < 0) dir = 0;

        if(!collision(dx, dy)) {
            x += dx;
            y += dy;
        }
    }

    protected boolean collision(int dx, int dy){
        return level.getTile((x + dx) >> Screen.tile_factor, (y + dy) >> Screen.tile_factor).solid();
    }
}
