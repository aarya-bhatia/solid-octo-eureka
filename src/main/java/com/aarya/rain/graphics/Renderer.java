package com.aarya.rain.graphics;

import com.aarya.rain.Game;
import com.aarya.rain.level.tile.Tile;
import com.aarya.rain.level.tile.VoidTile;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Renderer {

    private final int width;
    private final int height;
    private final int[] pixels;

    private int xOff = 0;
    private int yOff = 0;

    private static final int hiddenColor = Tile.GRASS_1.sprite.pixels[0];

    public Renderer(BufferedImage image, int w, int h) {
        this.width = w;
        this.height = h;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }

    public int getXOff() {
        return xOff;
    }

    public int getYOff() {
        return yOff;
    }

    public void setXOff(int xOff) {
        this.xOff = xOff;
    }

    public void setYOff(int yOff) {
        this.yOff = yOff;
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    private void setPixel(int x, int y, int col) {
        if(x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }
        this.pixels[x + y * width] = col;
    }

    public void renderTile(int X, int Y, Tile tile) {
        for (int y = 0; y < Sprite.SIZE; y++) {
            int yAbs = Y - yOff + y;
            for (int x = 0; x < Sprite.SIZE; x++) {
                int xAbs = X - xOff + x;
                if (!valid(xAbs, yAbs)) { break; }
                if (xAbs < 0) xAbs = 0;
                int col = tile.sprite.getPixel(x, y);
                if (!hideColor(col)) {
                    setPixel(xAbs, yAbs, col);
                }
                else {
                    setPixel(xAbs, yAbs, hiddenColor);
                }
            }
        }
    }

    public void renderPlayer(int X, int Y, Sprite sprite) {
        for (int y = 0; y < Sprite.SIZE; y++) {
            int yAbs = Y - yOff + y;
            for (int x = 0; x < Sprite.SIZE; x++) {
                int xAbs = X - xOff + x;
                if (!valid(xAbs, yAbs)) { break; }
                if (xAbs < 0) xAbs = 0;
                int col = sprite.getPixel(x, y);
                if (!hideColor(col)) {
                    setPixel(xAbs, yAbs, col);
                }
            }
        }
    }

    private boolean hideColor(int col) {
        return switch (col & 0xffffff) {
            case 0, 0xffffff, 0xc0c0c0, 0x76715f -> true;
            default -> false;
        };
    }

    private boolean valid(int x, int y) {
        return x >= -(Sprite.SIZE) && x < width && y >= 0 && y < height;
    }
}