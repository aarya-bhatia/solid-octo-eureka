package com.aarya.rain.graphics;

import java.util.Random;

public class Screen {

    private static final short MAP_SIZE = 6;
    private static final short TILE_SIZE = 4;
    private int width,height;
    private int[] pixels;
    public int[] tiles = new int[64 * 64];
    private Random random = new Random();

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
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void render() {
        /* iterate over all pixels in screen */
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int tileIndex = (x >> TILE_SIZE) + ((y >> TILE_SIZE) << MAP_SIZE);
                int idx = getIndex(x,y);
                pixels[idx] = tiles[tileIndex];
            }
        }
    }
}
