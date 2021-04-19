package com.aarya.rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private final String path;
    public final int size;
    public int[] pixels;

    public static final SpriteSheet sheet1 = new SpriteSheet("./textures/PathAndObjects.png", 512);

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
        BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
    }
}
