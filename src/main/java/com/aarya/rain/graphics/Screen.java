package com.aarya.rain.graphics;

import com.aarya.rain.level.tile.Tile;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    public static final short TILE_SIZE = 4;
    private static final short MAP_FACTOR = 3;
    private static final short MAP_SIZE = 1 << MAP_FACTOR;
    private static final short MAP_SIZE_MASK = MAP_SIZE - 1;

    public final int width;
    public final int height;
    private final int[] pixels;
    private final int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private final Random random = new Random();

    private int xOff = 0, yOff = 0;

    public Screen(int w, int h) {
        width = w;
        height = h;
        pixels = new int[w*h];

        for(int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void update(int dx, int dy) {
        xOff += dx;
        yOff += dy;
    }

    private int getTileIndex(int x, int xx, int y, int yy) {
        int xTmp = (xx >> TILE_SIZE) & MAP_SIZE_MASK;
        int yTmp = (yy >> TILE_SIZE) & MAP_SIZE_MASK;
        yTmp = yTmp << MAP_FACTOR;
        return xTmp + yTmp;
    }

    public void render() {
        /* iterate over all pixels in screen */
        for(int y = 0; y < height; y++) {
            int yy = y + yOff;
            if(yy < 0 | yy >= height) { continue; }
            for(int x = 0; x < width; x++) {
                int xx = x + xOff;
                if(xx < 0 || xx >= width) { continue; }

//                int tileIndex = getTileIndex(x, xx, y, yy);
//                int idx = getIndex(x,y);
//                pixels[idx] = tiles[tileIndex];

                int ms = Sprite.grass.size - 1;
                int ss = Sprite.grass.size;
                pixels[xx + yy * width] = Sprite.grass.pixels[(x & ms) + (y & ms) * ss];
            }
        }
    }




    public void renderTile(int tileX, int tileY, Tile tile) {
        for(int y = 0; y < tile.sprite.size; y++) {
            int yAbs = tileY + yOff;
            for(int x = 0; x < tile.sprite.size; x++) {
                int xAbs = tileX + xOff;
                if(!valid(xAbs, yAbs)) {
                    break;
                }
                pixels[xAbs + yAbs * width] = tile.sprite.pixels[x + y * tile.sprite.size];
            }
        }
    }

    private boolean valid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }









}
