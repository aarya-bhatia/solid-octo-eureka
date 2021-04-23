package com.aarya.rain.entity.projectile;

import com.aarya.rain.entity.Entity;
import com.aarya.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final int xOrigin;
    protected final int yOrigin;
    protected Sprite sprite;
    protected float nx, ny;
    protected double angle;
    protected float speed;
    protected float rateOfFire;
    protected float range;
    protected float damage;

    public Projectile(int xo, int yo, double dir) {
        xOrigin = xo;
        yOrigin = yo;
    }
}
