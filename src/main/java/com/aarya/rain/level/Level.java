package com.aarya.rain.level;

import com.aarya.rain.graphics.Screen;

public class Level {
    
    protected int width, height;
    protected int[] tiles;
    
    public Level(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new int[width * height];
        generateLevel();
    }
    
    public Level(String path) {
        loadLevel(path);
    }

    protected void loadLevel(String path) {
    }

    protected void generateLevel() {
    }

    public void update() {
    }

    public void render(int xScroll, int yScroll, Screen screen) {
    }

    public void time() {
    }

}
