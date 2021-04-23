package com.aarya.rain.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GfxImage {

    private int w,h;
    private int[] p;
    private BufferedImage image;

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int[] getP() {
        return p;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public GfxImage(String path) {
        try {
            image = ImageIO.read(GfxImage.class.getResourceAsStream(path));
            w = image.getWidth();
            h = image.getHeight();
            p = image.getRGB(0,0, w, h, null, 0, w);
            image.flush();
            System.out.println("Image loaded: " + path);
            System.out.printf("w: %d, h: %d, pixels: %d \n",
                    w, h, size());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while loading GfxImage: " + path);
        }
    }

    public int size(){
        return p.length;
    }

    public int getP(int x, int y) {
        if(x < 0 || x >= w || y < 0 || y >= h){
            System.out.println("pixel out of bounds!");
            return 0;
        }
        return p[x + y * w];
    }
}
