package com.aarya.rain.graphics;

import com.aarya.rain.Game;

import java.util.HashMap;

public class Sprite {

    public final int size;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static final HashMap<String, Sprite> SPRITES = new HashMap<>();

    static {
        SPRITES.put("void", new Sprite(16, 0x1B87E0));
        SPRITES.put("basic.tiles.grass.1", new Sprite(16, 0, 8, SpriteSheet.basicTiles));
        SPRITES.put("basic.tiles.grass.2", new Sprite(16, 1, 8, SpriteSheet.basicTiles));
        SPRITES.put("characters.boy.north.1", new Sprite(16, 3, 3, SpriteSheet.characters));
        SPRITES.put("characters.boy.east.1", new Sprite(16, 3, 2, SpriteSheet.characters));
        SPRITES.put("characters.boy.west.1", new Sprite(16, 3, 1, SpriteSheet.characters));
        SPRITES.put("characters.boy.south.1", new Sprite(16, 3, 0, SpriteSheet.characters));

        if(Game.debug) {
            System.out.println("# sprites : " + SPRITES.size());
            System.out.println("grass sprite size: " + SPRITES.get("grass.1").size);
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

    public Sprite(int size, int color) {
        this.size = size;
        pixels = new int[size * size];
        setColor(color);
    }

    private void setColor(int color) {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    /* extracting sprite from the sprite sheet */
    private void load() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                pixels[x + y * size] = sheet.pixels[(x + this.x) +
                        (y + this.y) * sheet.w];
            }
        }
    }
}
