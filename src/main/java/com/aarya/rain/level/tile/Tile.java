package com.aarya.rain.level.tile;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;

import java.util.HashMap;

public abstract class Tile {

    protected int x, y;
    public Sprite sprite;

    public static final Tile grassTile1 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.1"));
    public static final Tile grassTile2 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.2"));
//    public static final Tile treeTile1 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.2"));
    public static final Tile voidTile = new VoidTile(Sprite.SPRITES.get("void"));

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void render(int x, int y, Screen screen);

    public boolean solid() { return false; }
}
