package com.aarya.rain.graphics;

import com.aarya.rain.Game;
import com.aarya.rain.Window;
import com.aarya.rain.level.tile.Tile;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import java.util.Locale;

public class Renderer {

    private final int width;
    private final int height;
    private final int[] pixels;

    private GfxFont font = GfxFont.standard;

    private int xOff = 0;
    private int yOff = 0;

    private static final int CLR = 0xffff00ff; /* do not render this color */

    private static final int grassCol = Tile.GRASS_1.sprite.pixels[0];

    public Renderer(Game game, Window window) {
        this.width = game.getWidth();
        this.height = game.getHeight();
        this.pixels = ((DataBufferInt) window.getImage().getRaster().getDataBuffer()).getData();
    }

    public Renderer(BufferedImage image, int w, int h) {
        this.width = w;
        this.height = h;
        this.pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
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
        if(x < 0 || x >= width || y < 0 || y >= height || col == CLR) {
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
                    setPixel(xAbs, yAbs, grassCol);
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

    /**
     * This method renders a simple image to the screen.
     * @param image the image object to render
     * @param offX the x position on screen
     * @param offY the y position on screen
     */
    public void drawImage(GfxImage image, int offX, int offY) {
        /* off screen */
        if(offX < -image.getW() || offY < -image.getH() || offX >= width || offY >= height) { return; }

        int nx = 0, ny = 0, nw = image.getW(), nh = image.getH();

        /* clipping code */
        if(offX < 0) { nx -= offX; }
        if(offY < 0) { ny -= offY; }
        if(nw + offX > width) { nw = width - offX; }
        if(nh + offY > height) { nh = height - offY; }

        for(int y = ny; y < nh; y++) {
            for(int x = nx; x < nw; x++) {
                setPixel(x + offX, y + offY, image.getP(x, y));
            }
        }
    }

    /**
     * this method renders a tile to the screen
     * @param image the sprite sheet containing the tiles
     * @param offX the x position on the screen
     * @param offY the y position on the screen
     * @param tileX the x position of tile in the image
     * @param tileY the y position of tile in the image
     */
    public void drawImageTile(GfxTile image, int offX, int offY, int tileX, int tileY) {
        /* off screen */
        if(offX < -image.getTileW() || offY < -image.getTileH() || offX >= width || offY >= height) { return; }

        int nx = 0, ny = 0, nw = image.getTileW(), nh = image.getTileH();

        /* clipping code */
        if(offX < 0) { nx -= offX; }
        if(offY < 0) { ny -= offY; }
        if(nw + offX > width) { nw = width - offX; }
        if(nh + offY > height) { nh = height - offY; }

        for(int y = ny; y < nh; y++) {
            for(int x = nx; x < nw; x++) {
                setPixel(x + offX, y + offY,
                        image.getP(x + tileX * image.getTileW(),
                                y + tileY * image.getTileH()));
            }
        }
    }

    public void drawText(String text, int offX, int offY, int col) {
//        text = text.toUpperCase();
        int offset = 0;

        for(int i = 0; i < text.length(); i++) {
            int unicode = (int)(Character.toUpperCase(text.charAt(i))) - 32; //(int)' ';

            for(int y = 0; y < font.getFontImage().getH(); y++) {
                for(int x = 0; x < font.getFw()[unicode]; x++) {
                    /* letters are white in image */
                    if(font.getFontImage().getP(x + font.getOffsets()[unicode], y) == GfxFont.col_white) {
                        setPixel(x + offset + offX, y + offY, col);
                    }
                }
            }

            offset += font.getFw()[unicode];
        }
    }
}