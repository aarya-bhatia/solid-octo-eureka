package com.aarya.rain.graphics;

import com.aarya.rain.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

    private final String path;
    public int[] pixels;
    public final int w;
    public final int h;

    public static final SpriteSheet BASIC_TILES = new SpriteSheet("/textures/basictiles.png", 128, 240);
    public static final SpriteSheet CHARACTERS = new SpriteSheet("/characters/characters.png", 192, 128);

    public SpriteSheet(String path, int size) {
        this(path, size, size);
    }

    public SpriteSheet(String path, int w, int h) {
        this.w = w;
        this.h = h;
        this.path = path;
        this.pixels = new int[w*h];
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() throws IOException {
        URL imagePath = SpriteSheet.class.getResource(path);
        if(Game.debug) {
            System.out.println("Loading sprite sheet: " + imagePath.getPath());
        }
        BufferedImage image = ImageIO.read(imagePath);
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
    }
}
