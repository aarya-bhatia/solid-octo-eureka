package com.aarya.rain.graphics;

public class Sprite {

    private final int size;
    private int x,y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static final Sprite grass = new Sprite(128, 0, 0, SpriteSheet.sheet1);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.size = size;
        this.x = x * size;
        this.pixels = new int[size*size];
    }

    /* extracting sprite from the sprite sheet */
    private void load() {
        for(int y = 0; y < size; y++) {
            for(int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.pixels[ (x + this.x) +
                        (y + this.y) * sheet.size ];
            }
        }
    }
}
