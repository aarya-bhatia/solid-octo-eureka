package com.aarya.rain.graphics;

import com.aarya.rain.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

    private final String path;
    public int[] pixels;
    public final int width;
    public final int height;

    public static final SpriteSheet BASIC_TILES = new SpriteSheet("/textures/basic.tiles.png", 128, 240);
    public static final SpriteSheet CHARACTERS = new SpriteSheet("/characters/characters.png", 192, 128);

    public SpriteSheet(String path, int width, int height) {
        this.width = width;
        this.height = height;
        this.path = path;
        this.pixels = new int[width * height];
        load();
    }

    private void load() {
        URL imagePath = SpriteSheet.class.getResource(path);
        if(Game.debug) {
            System.out.println("Loading sprite sheet: " + imagePath.getPath());
        }
        try {
            BufferedImage image = ImageIO.read(imagePath);
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
            System.out.println("Successfully loaded sprite sheet : " + imagePath);
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading sprite sheet: " + imagePath);
        }
    }
}
