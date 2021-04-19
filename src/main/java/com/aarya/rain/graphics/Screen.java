package com.aarya.rain.graphics;

/**
 * class to write to the pixels
 */
public class Screen {

    private int width, height;
    public int[] pixels;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    /* converts 2d to 1d index */
    private int getIndex(int x, int y) {
        return x + y * width;
    }

    public void render() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixels[getIndex(x, y)] = 0xff00ff;
            }
        }
    }

}
