package com.aarya.rain.graphics;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    private static final short TILE_SIZE = 4;
    private static final short MAP_FACTOR = 3;
    private static final short MAP_SIZE = 1 << MAP_FACTOR;
    private static final short MAP_SIZE_MASK = MAP_SIZE - 1;

    private final int width;
    private final int height;
    private final int[] pixels;
    private final int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private final static Random random = new Random();

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

    /* converts 2d to 1d index */
    private int getIndex(int x, int y) {
        return x + y * width;
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
            for(int x = 0; x < width; x++) {
                int xx = x + xOff;
                int tileIndex = getTileIndex(x, xx, y, yy);
                int idx = getIndex(x,y);
//                pixels[idx] = tiles[tileIndex];

                Sprite s;
                int tileNum = random.nextInt(4);

                if(tileNum == 0) {
                    s = Sprite.grass;
                }
                else if(tileNum == 1) {
                    s = Sprite.grass1;
                }
                else if(tileNum == 2) {
                    s = Sprite.grass2;
                }
                else {
                    s = Sprite.grass3;
                }

                int ss = s.size;
                pixels[idx] = s.pixels[(x & (ss-1)) + (y & (ss-1)) * ss];
            }
        }
    }

    public void showTile() {

    }
}
