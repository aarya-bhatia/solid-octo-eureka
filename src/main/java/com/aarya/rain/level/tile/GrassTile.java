package com.aarya.rain.level.tile;

import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.graphics.Sprite;

public class GrassTile extends Tile {

    public GrassTile(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void render(int x, int y, Renderer renderer) {
        renderer.renderTile(x, y, this);
    }

    @Override
    public boolean solid() {
        return false;
    }

}
