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
            return Tiles.VOID;
        }

        switch(tiles[i] & 0xffffff) {
            case 0x4CD62C: {
                return Tiles.GRASS_1;
            }
            case 0xABF42E: {
                return Tiles.GRASS_2;
            }
            case 0x26DD95: {
                return Tiles.TREE_1;
            }
            case 0x838383: {
                return Tiles.ROCK;
            }
            case 0x2C7CD6: {
                return Tiles.WATER;
            }
            default: {
                return Tiles.VOID;
            }
        }

    }
}
