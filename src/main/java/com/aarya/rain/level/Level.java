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
        screen.setOffset(xScroll, yScroll); /* like the camera */

        /* corner pins */
        int x0 = xScroll >> Screen.tile_factor;
        int x1 = (xScroll + width + Screen.tile_size) >> Screen.tile_factor;
        int y0 = yScroll >> Screen.tile_factor;
        int y1 = (yScroll + height + Screen.tile_size) >> Screen.tile_factor;

        /* render tiles */
        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x << Screen.tile_factor, y << Screen.tile_factor, screen);
            }
        }
    }

    public abstract Tile getTile(int x, int y);

    // unused
    public void update() {}
}
