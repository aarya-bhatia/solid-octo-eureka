package com.aarya.rain.level.tile;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;

public class VoidTile extends Tile {
    public VoidTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x, y, this);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
