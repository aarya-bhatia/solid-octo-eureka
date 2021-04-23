package com.aarya.rain.graphics;

public class GfxTile extends GfxImage {

    private int tileW, tileH;

    public int getTileW() {
        return tileW;
    }

    public void setTileW(int tileW) {
        this.tileW = tileW;
    }

    public int getTileH() {
        return tileH;
    }

    public void setTileH(int tileH) {
        this.tileH = tileH;
    }

    public GfxTile(String path, int w, int h) {
        super(path);
        tileW = w;
        tileH = h;
    }
}
