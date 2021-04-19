package com.aarya.rain.level.tile;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;

public abstract class Tile {

    protected int x, y;
    protected Sprite sprite;

    public static Tile grassTile = new GrassTile(Sprite.grass);

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void render(int x, int y, Screen screen);

    public boolean solid() { return false; }
}
