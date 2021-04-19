package com.aarya.rain.graphics;

import com.aarya.rain.Game;

public class Sprite {

    public final int size;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static final Sprite grass = new Sprite(16, 0, 0, SpriteSheet.sheet1);

    static {
        if(Game.debug) {
            System.out.println(grass.size);
        }
    }

    public Sprite(int size, int x, int y, SpriteSheet sheet) {
        this.size = size;
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        this.pixels = new int[size * size];
        load();
    }

    /* extracting sprite from the sprite sheet */
    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.pixels[(x + this.x) +
                        (y + this.y) * sheet.size];
            }
        }
    }
}
