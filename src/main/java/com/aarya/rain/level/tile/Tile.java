package com.aarya.rain.level.tile;

import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.graphics.Sprite;

public abstract class Tile {

    protected int x, y;
    public Sprite sprite;

    public static final Tile GRASS_1 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.1"));
    public static final Tile GRASS_2 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.2"));
    public static final Tile ROCK = new RockTile(Sprite.SPRITES.get("basic.tiles.rocks.1"));
    public static final Tile SHRUBS = new GrassTile(Sprite.SPRITES.get("basic.tiles.shrubs.1"));
    public static final Tile VOID = new VoidTile(Sprite.SPRITES.get("void"));
    public static final Tile TREE_1 = new TreeTile(Sprite.SPRITES.get("basic.tiles.tree.1"));
    public static final Tile TREE_2 = new TreeTile(Sprite.SPRITES.get("basic.tiles.tree.2"));
    public static final Tile WATER = new WaterTile(Sprite.SPRITES.get("water"));

    public static final int col_grass_1 = 0x4CD62C;
    public static final int col_grass_2 = 0xABF42E;
    public static final int col_rocks = 0x838383;
    public static final int col_tree_1 = 0x26DD95;
    public static final int col_water = 0x2C7CD6;
    public static final int alpha_mask = 0xFFFFFF;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void render(int x, int y, Renderer renderer);

    public abstract boolean solid();
}