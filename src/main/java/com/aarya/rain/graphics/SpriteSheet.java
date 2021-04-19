package com.aarya.rain.graphics;

import com.aarya.rain.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class SpriteSheet {

    private final String path;
    public final int size;
    public int[] pixels;

    public static final SpriteSheet sheet1 = new SpriteSheet("/textures/PathAndObjects.png", 512);

    static {
        if(Game.debug) {
            System.out.println(Integer.toHexString(sheet1.pixels[0]));
        }
    }

    public SpriteSheet(String path, int size) {
        this.path = path;
        this.size = size;
        this.pixels = new int[size*size];
        try {
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() throws IOException {
        URL imagePath = SpriteSheet.class.getResource(path);
        if(Game.debug) {
            System.out.println(imagePath.getPath());
        }
        BufferedImage image = ImageIO.read(imagePath);
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
    }
}
