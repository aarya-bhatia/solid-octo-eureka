package com.aarya.rain.level.tile;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;

public class RockTile extends Tile {
    public RockTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Screen screen) {
        screen.renderTile(x, y, this);
    }

    public boolean isSolid() {
        return true;
    }
}
