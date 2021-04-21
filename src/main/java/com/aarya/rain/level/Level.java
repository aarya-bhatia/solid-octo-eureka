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
        int x0 = xScroll >> screen.tile_factor;
        int x1 = (xScroll + screen.width + screen.tile_size) >> screen.tile_factor;
        int y0 = yScroll >> screen.tile_factor;
        int y1 = (yScroll + screen.height + screen.tile_size) >> screen.tile_factor;

        /* render tiles */
        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x << screen.tile_factor, y << screen.tile_factor, screen);
            }
        }
    }

    protected abstract Tile getTile(int x, int y);

    public void update() {}
}
