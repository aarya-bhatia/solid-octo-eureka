package com.aarya.rain.entity.mob;

import com.aarya.rain.entity.Entity;
import com.aarya.rain.entity.projectile.Projectile;
import com.aarya.rain.entity.projectile.WizardProjectile;
import com.aarya.rain.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public abstract class Mob extends Entity {

    protected Sprite sprite;
    protected boolean moving = false;
    protected int dir = 0;

    protected List<Projectile> projectiles = new ArrayList<>();

    public void move(int dx, int dy) {
        if(dx > 0) dir = 1;
        if(dx < 0) dir = 3;
        if(dy > 0) dir = 2;
        if(dy < 0) dir = 0;

        if(!collision(dx, 0)) { x += dx; }
        if(!collision(0, dy)) { y += dy; }
    }

    protected void shoot(int x, int y, double dir) {
        System.out.println(Math.toDegrees(dir));
        Projectile p = new WizardProjectile(x, y, dir);
        projectiles.add(p);
        level.add(p);
    }

    protected boolean collision(int dx, int dy){
        for(int c = 0; c < 4; c++) {
            int xt = ((x + dx) + c % 2);
            int yt = ((y + dy) + c / 2 * 12 + 3);
            if(level.getTile(xt >> Sprite.FACTOR, yt >> Sprite.FACTOR).solid()) {
                return true;
            }
        }
        return false;
    }
}
