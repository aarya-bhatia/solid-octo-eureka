package com.aarya.rain.level;

import com.aarya.rain.graphics.Sprite;
import com.aarya.rain.level.tile.*;

public class Tiles {
    public static final Tile GRASS_1 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.1"));
    public static final Tile GRASS_2 = new GrassTile(Sprite.SPRITES.get("basic.tiles.grass.2"));
    public static final Tile ROCK = new RockTile(Sprite.SPRITES.get("basic.tiles.rocks.1"));
    public static final Tile SHRUBS = new GrassTile(Sprite.SPRITES.get("basic.tiles.shrubs.1"));
    public static final Tile VOID = new VoidTile(Sprite.SPRITES.get("void"));
    public static final Tile TREE_1 = new TreeTile(Sprite.SPRITES.get("basic.tiles.tree.1"));
    public static final Tile TREE_2 = new TreeTile(Sprite.SPRITES.get("basic.tiles.tree.2"));
    public static final Tile WATER = new WaterTile(Sprite.SPRITES.get("water"));
}

/*
Grass 1 0x4CD62C
Rocks 0x838383
Tree 1 0x26DD95
Grass 2 0xABF42E
Water 0x2C7CD6
Void
 */