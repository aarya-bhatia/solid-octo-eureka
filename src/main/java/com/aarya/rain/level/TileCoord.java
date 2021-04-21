package com.aarya.rain.level;

public class TileCoord {

    private int x,y;
    private final static int tile_size = 16;

    public TileCoord(int x, int y) {
        this.x = x * tile_size;
        this.y = y * tile_size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] xy() {
        return new int[] {x, y};
    }
}
