package com.aarya.rain.graphics;

import com.aarya.rain.level.tile.Tile;
import com.aarya.rain.level.tile.VoidTile;

import java.util.Arrays;

public class Screen {
    public static final short tile_size = 16;
    public static final short tile_factor = 4;
    public final int width;
    public final int height;
    private final int[] pixels;
    public int xOff = 0;
    public int yOff = 0;

    private static final int hiddenColor = Tile.GRASS_1.sprite.pixels[0];

    public Screen(int w, int h) {
        width = w;
        height = h;
        pixels = new int[w * h];
    }

    public int[] getPixels() {
        return pixels;
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void renderTile(int X, int Y, Tile tile) {
        for (int y = 0; y < tile.sprite.SIZE; y++) {
            int yAbs = Y - yOff + y;
            for (int x = 0; x < tile.sprite.SIZE; x++) {
                int xAbs = X - xOff + x;

                if (!valid(xAbs, yAbs, tile.sprite)) {
                    break;
                }
                if (xAbs < 0) xAbs = 0;

                int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];

                if (!hideColor(col) || tile instanceof VoidTile) {
                    pixels[xAbs + yAbs * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
                } else {
                    pixels[xAbs + yAbs * width] = hiddenColor;
                }
            }
        }
    }

    public void renderPlayer(int X, int Y, Sprite sprite) {
        for (int y = 0; y < sprite.SIZE; y++) {
            int yAbs = Y - yOff + y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xAbs = X - xOff + x;

                if (!valid(xAbs, yAbs, sprite)) {
                    break;
                }
                if (xAbs < 0) xAbs = 0;

                int col = sprite.pixels[x + y * sprite.SIZE];

                if (!hideColor(col)) {
                    pixels[xAbs + yAbs * width] = sprite.pixels[x + y * sprite.SIZE];
                }
            }
        }
    }

    private boolean hideColor(int col) {
        switch (col & 0xffffff) {
            case 0:
            case 0xffffff:
            case 0xc0c0c0:
            case 0x76715f:
                return true;
        }
        return false;
    }

    public void setOffset(int xOff, int yOff) {
        this.xOff = xOff;
        this.yOff = yOff;
    }

    private boolean valid(int x, int y, Sprite sprite) {
        return x >= -(sprite.SIZE) && x < width && y >= 0 && y < height;
    }
}




//    private static final short MAP_FACTOR = 3;
//    private static final short MAP_SIZE = 1 << MAP_FACTOR;
//    private static final short MAP_SIZE_MASK = MAP_SIZE - 1;
//    private int getTileIndex(int x, int xx, int y, int yy) {
//        int xTmp = (xx >> tile_factor) & MAP_SIZE_MASK;
//        int yTmp = (yy >> tile_factor) & MAP_SIZE_MASK;
//        yTmp = yTmp << MAP_FACTOR;
//        return xTmp + yTmp;
//    }

