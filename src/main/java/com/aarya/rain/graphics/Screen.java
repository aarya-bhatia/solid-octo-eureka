package com.aarya.rain.graphics;

public class Screen {

    private int width,height;
    private int[] pixels;

    public Screen(int w, int h) {
        width = w;
        height = h;
        pixels = new int[w*h];
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
                int idx = getIndex(x,y);
                pixels[idx] = 0xff;
            }
        }
    }
}
