package com.aarya.rain.level;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.level.tile.Tile;

public class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }

    public Level(String path) {
        loadLevel(path);
    }

    protected void loadLevel(String path) {
    }

    protected void generateLevel() {
    }

    public void update() {
    }

    public void render(int xScroll, int yScroll, Screen screen) {
        screen.setOffset(xScroll, yScroll);
        /* corner pins */
        int x0 = xScroll >> screen.TILE_SIZE;
        int x1 = (xScroll + screen.width) >> screen.TILE_SIZE;
        int y0 = yScroll >> screen.TILE_SIZE;
        int y1 = (yScroll + screen.height) >> screen.TILE_SIZE;

        for(int y = y0; y < y1; y++) {
            for(int x = x0; x < x1; x++) {
                getTile(x, y).render(x << screen.TILE_SIZE, y << screen.TILE_SIZE, screen);
            }
        }
    }

    public Tile getTile(int x, int y) {
        if(tiles[x + y * width] == 0) {
            return Tile.grassTile;
        }
        return Tile.voidTile;
    }

    public void time() {
    }


}
