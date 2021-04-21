package com.aarya.rain.level;

import com.aarya.rain.level.tile.Tile;

import java.util.Random;

public class RandomLevel extends Level {

    private final static Random random = new Random();

    public RandomLevel(int width, int height) {
        super(width, height);
        generateLevel();
    }

    protected void generateLevel() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(6);
            }
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || x >= width || y < 0 || y >= height) return Tile.VOID; // out of bounds

        int tmp = x + y * width;

        switch(tiles[tmp]) {
            case 0: {
                return Tile.GRASS_1;
            }
            case 1: {
                return Tile.GRASS_2;
            }
            case 2: {
                return Tile.ROCK;
            }
            case 3: {
                return tmp % 2 == 0 ? Tile.TREE_1 : Tile.TREE_2;
            }
            case 4: {
                return Tile.SHRUBS;
            }
            default: {
                return Tile.VOID;
            }
        }
    }

}
