package com.aarya.rain.graphics;

import com.aarya.rain.entity.mob.Mob;
import com.aarya.rain.entity.mob.Player;
import com.aarya.rain.level.Tiles;
import com.aarya.rain.level.tile.Tile;

import java.util.Arrays;
import java.util.Random;

public class Screen {

    public static final short TILE_SIZE = 4;
    private static final short MAP_FACTOR = 3;
    private static final short MAP_SIZE = 1 << MAP_FACTOR;
    private static final short MAP_SIZE_MASK = MAP_SIZE - 1;
    public static final short TILE_SIZE_ACTUAL = 1 << TILE_SIZE;

    public final int width;
    public final int height;
    private final int[] pixels;
    private final int[] tiles = new int[MAP_SIZE * MAP_SIZE];
    private final Random random = new Random();
    public int xOff = 0;
    public int yOff = 0;

    private static final int hiddenColor = Tiles.GRASS_1.sprite.pixels[0];

    public Screen(int w, int h) {
        width = w;
        height = h;
        pixels = new int[w * h];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = random.nextInt(0xffffff);
        }
    }

    public int[] getPixels() {
        return pixels;
    }

    public void clear() {
        Arrays.fill(pixels, 0);
    }

    public void update(int dx, int dy) {
        xOff += dx;
        yOff += dy;
    }

    private int getTileIndex(int x, int xx, int y, int yy) {
        int xTmp = (xx >> TILE_SIZE) & MAP_SIZE_MASK;
        int yTmp = (yy >> TILE_SIZE) & MAP_SIZE_MASK;
        yTmp = yTmp << MAP_FACTOR;
        return xTmp + yTmp;
    }

    public void renderTile(int tileX, int tileY, Tile tile) {
        for (int y = 0; y < tile.sprite.size; y++) {
            int yAbs = tileY - yOff + y;
            for (int x = 0; x < tile.sprite.size; x++) {
                int xAbs = tileX - xOff + x;
                if (!valid(xAbs, yAbs, tile.sprite)) {
                    break;
                }
                if (xAbs < 0) xAbs = 0;

                int col = tile.sprite.pixels[x + y * tile.sprite.size];

                if(!hideColor(col)) {
                    pixels[xAbs + yAbs * width] = tile.sprite.pixels[x + y * tile.sprite.size];
                }
                else {
                    pixels[xAbs + yAbs * width] = hiddenColor;
                }
            }
        }
    }

    public void renderMobs(int mobX, int mobY, Mob mob) {

    }

    public void renderPlayer(int playerX, int playerY, Sprite sprite) {
        for (int y = 0; y < sprite.size; y++) {
            int yAbs = playerY - yOff + y;
            for (int x = 0; x < sprite.size; x++) {
                int xAbs = playerX - xOff + x;
                if (!valid(xAbs, yAbs, sprite)) {
                    break;
                }
                if (xAbs < 0) xAbs = 0;

                int col = sprite.pixels[x + y * sprite.size];

                if (!hideColor(col)){
                    pixels[xAbs + yAbs * width] = sprite.pixels[x + y * sprite.size];
                }
            }
        }
    }

    private boolean hideColor(int col) {
        switch (col) {
            case 0:
            case 0xffffff:
            case 0xffc0c0c0:
            case 0xff76715f:
                return true;
            default:
                return false;
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        xOff = xOffset;
        yOff = yOffset;
    }

    private boolean valid(int x, int y, Sprite sprite) {
        return x >= -sprite.size && x < width && y >= 0 && y < height;
    }

}
