package com.aarya.rain.level;

import com.aarya.rain.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

    public SpawnLevel(String path, int w, int h) {
        super(w, h);
        loadLevel(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            width = image.getWidth();
            height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
            System.out.println("Level loaded successfully! ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load level file!");
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        int i = x + y * width;

        if (i < 0 || i >= tiles.length) {
            return Tile.VOID;
        }

        switch(tiles[i] & Tile.alpha_mask) {
            case Tile.col_grass_1: {
                return Tile.GRASS_1;
            }
            case Tile.col_grass_2: {
                return Tile.GRASS_2;
            }
            case Tile.col_tree_1: {
                return Tile.TREE_1;
            }
            case Tile.col_rocks: {
                return Tile.ROCK;
            }
            case Tile.col_water: {
                return Tile.WATER;
            }
            default: {
                return Tile.VOID;
            }
        }

    }
}
