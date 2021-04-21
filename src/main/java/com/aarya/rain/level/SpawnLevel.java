package com.aarya.rain.level;

import com.aarya.rain.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

    private int[] pixels;

    public SpawnLevel(String path, int w, int h) {
        super(w, h);
        loadLevel(path);
    }

    protected void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            pixels = new int[w * h];
            image.getRGB(0, 0, w, h, pixels, 0, w);
            System.out.println("Level loaded successfully! ");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not load level file!");
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        int i = x + y * width;

        if (i < 0 || i >= 256) {
            return Tiles.VOID;
        }

        switch(pixels[i] & 0xffffff) {
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
