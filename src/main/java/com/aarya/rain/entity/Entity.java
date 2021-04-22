package com.aarya.rain.entity;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.level.Level;
import com.aarya.rain.level.TileCoord;

public abstract class Entity {

    public int x,y;
//    public TileCoord coord;
    protected Level level;

    public abstract void update();

    public abstract void render(Screen screen);

    public void setLevel(Level level) {
        this.level = level;
    }
}
