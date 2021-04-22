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

        if(!collision(dx, 0)) {
            x += dx;
        }

        if(!collision(0, dy)) {
            y += dy;
        }
    }

    protected boolean collision(int dx, int dy){
        for(int c = 0; c < 4; c++) {
            int xt = ((x + dx) + c % 2);
            int yt = ((y + dy) + c / 2 * 12 + 3);
            if(level.getTile(xt >> Screen.tile_factor, yt >> Screen.tile_factor).solid()) {
                return true;
            }
        }
        return false;
    }
}
