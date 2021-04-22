package com.aarya.rain.level;

import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.graphics.Sprite;
import com.aarya.rain.level.tile.*;

public abstract class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new int[width * height];
    }

    public void render(int xScroll, int yScroll, Renderer renderer) {
        int x0 = xScroll >> Sprite.FACTOR;
        int x1 = (xScroll + width + Sprite.SIZE) >> Sprite.FACTOR;
        int y0 = yScroll >> Sprite.FACTOR;
        int y1 = (yScroll + height + Sprite.SIZE) >> Sprite.FACTOR;

        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                Tile tile = this.getTile(x, y);
                tile.render(x << Sprite.FACTOR, y << Sprite.FACTOR, renderer);
            }
        }
    }

    public abstract Tile getTile(int x, int y);
}
