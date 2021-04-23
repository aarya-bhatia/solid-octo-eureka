package com.aarya.rain.entity.projectile;

import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.level.tile.Tile;

public class WizardProjectile extends Projectile {

    public WizardProjectile(int xo, int yo, double dir) {
        super(xo, yo, dir);
        range = 200;
        damage = 20;
        rateOfFire = 15;
        speed = 4;
        nx = (float) (Math.cos(dir) * speed);
        ny = (float) (Math.cos(dir) * speed);
    }

    @Override
    public void update() {
        move();
    }

    protected void move() {
        x += nx;
        y += ny;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.renderTile(x, y, Tile.GRASS_1);
    }
}
