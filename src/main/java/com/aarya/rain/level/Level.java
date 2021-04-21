package com.aarya.rain.level;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.level.tile.*;

public abstract class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new int[width * height];
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);

        /* corner pins */
        int x0 = xScroll >> screen.TILE_SIZE_FACTOR;
        int x1 = (xScroll + screen.width + screen.TILE_SIZE_ACTUAL) >> screen.TILE_SIZE_FACTOR;
        int y0 = yScroll >> screen.TILE_SIZE_FACTOR;
        int y1 = (yScroll + screen.height + screen.TILE_SIZE_ACTUAL) >> screen.TILE_SIZE_FACTOR;

        /* render tiles */
        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x << screen.TILE_SIZE_FACTOR, y << screen.TILE_SIZE_FACTOR, screen);
            }
        }
    }

    protected abstract Tile getTile(int x, int y);

    public void update() {}
}
