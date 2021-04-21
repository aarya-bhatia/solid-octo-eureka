package com.aarya.rain.graphics;

import com.aarya.rain.Game;

import java.util.HashMap;

public class Sprite {

    public static final int SIZE = 16;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static final HashMap<String, Sprite> SPRITES = new HashMap<>();

    static {

        SPRITES.put("void", new Sprite(0x311432));
        SPRITES.put("water", new Sprite(0x1B87E0));

        SPRITES.put("basic.tiles.grass.1", new Sprite(0, 8, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.grass.2", new Sprite(1, 8, SpriteSheet.BASIC_TILES));

        SPRITES.put("characters.boy.north.1", new Sprite(3, 3, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.north.2", new Sprite(4, 3, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.north.3", new Sprite(5, 3, SpriteSheet.CHARACTERS));

        SPRITES.put("characters.boy.east.1", new Sprite(3, 2, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.east.2", new Sprite(4, 2, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.east.3", new Sprite(5, 2, SpriteSheet.CHARACTERS));

        SPRITES.put("characters.boy.west.1", new Sprite(3, 1, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.west.2", new Sprite(4, 1, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.west.3", new Sprite(5, 1, SpriteSheet.CHARACTERS));

        SPRITES.put("characters.boy.south.1", new Sprite(3, 0, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.south.2", new Sprite(4, 0, SpriteSheet.CHARACTERS));
        SPRITES.put("characters.boy.south.3", new Sprite(5, 0, SpriteSheet.CHARACTERS));

        SPRITES.put("mobs.ghost.south.1", new Sprite(6, 4, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.south.2", new Sprite(7, 4, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.south.3", new Sprite(8, 4, SpriteSheet.CHARACTERS));

        SPRITES.put("mobs.ghost.west.1", new Sprite(6, 5, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.west.2", new Sprite(7, 5, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.west.3", new Sprite(8, 5, SpriteSheet.CHARACTERS));

        SPRITES.put("mobs.ghost.east.1", new Sprite(6, 6, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.east.2", new Sprite(7, 6, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.east.3", new Sprite(8, 6, SpriteSheet.CHARACTERS));

        SPRITES.put("mobs.ghost.north.1", new Sprite(6, 7, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.north.2", new Sprite(7, 7, SpriteSheet.CHARACTERS));
        SPRITES.put("mobs.ghost.north.3", new Sprite(8, 7, SpriteSheet.CHARACTERS));

        SPRITES.put("basic.tiles.wall.cold.1", new Sprite(0, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.cold.2", new Sprite(1, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.cold.3", new Sprite(2, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.cold.4", new Sprite(3, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.hot.1", new Sprite(4, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.hot.2", new Sprite(5, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.hot.3", new Sprite(6, 0, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.wall.hot.4", new Sprite(7, 0, SpriteSheet.BASIC_TILES));

        SPRITES.put("basic.tiles.grass.plain", new Sprite(3,1, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.tree.1", new Sprite(6,4, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.tree.2", new Sprite(4,9, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.rocks.1", new Sprite(2,7, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.shrubs.1", new Sprite(4,2, SpriteSheet.BASIC_TILES));
        SPRITES.put("basic.tiles.water", new Sprite(5,2, SpriteSheet.BASIC_TILES));

        if(Game.debug) {
            System.out.println("# sprites : " + SPRITES.size());
            System.out.println("grass sprite size: " + SPRITES.get("grass.1").SIZE);
        }

    }

    public Sprite(int x, int y, SpriteSheet sheet) {
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        this.pixels = new int[SIZE * SIZE];
        load();
    }

    public Sprite(int color) {
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {
        for(int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }

    /* extracting sprite from the sprite sheet */
    private void load() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                pixels[x + y * SIZE] = sheet.pixels[(x + this.x) +
                        (y + this.y) * sheet.w];
            }
        }
    }
}
