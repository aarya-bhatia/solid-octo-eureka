package com.aarya.rain.graphics;

import java.util.Arrays;

public class GfxFont {

    public static final GfxFont standard = new GfxFont("/fonts/standard.png");

    /**
     * the top row of pixels contains the markers.
     * blue marks the beginning of each letter and
     * yellow marks the end. These positions will be
     * used to get the width and offsets of the letters.
     */

    private GfxImage fontImage;
    private int[] offsets;
    private int[] fw; /* letter widths */
    private final int n = 60; /* number of letters */

    public static final int col_blue = 0xff0000ff; // 0xFF1D00FF;
    public static final int col_yellow = 0xffffff00; // 0xFFFEFF00;
    public static final int col_white = 0xffffffff; // 0xFFFFFFFF;

    public GfxImage getFontImage() {
        return fontImage;
    }

    public void setFontImage(GfxImage fontImage) {
        this.fontImage = fontImage;
    }

    public int[] getOffsets() {
        return offsets;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    public int[] getFw() {
        return fw;
    }

    public void setFw(int[] fw) {
        this.fw = fw;
    }

    public GfxFont(String path) {
        fontImage = new GfxImage(path);
        offsets = new int[n];
        fw = new int[n];

        int idx = 0;
//        printColArray(fontImage.getP());

        for(int i = 0; i < fontImage.getW(); i++){
            if(fontImage.getP()[i] == col_blue) {
                offsets[idx] = i;
            }
            if(fontImage.getP()[i] == col_yellow) {
                fw[idx] = i - offsets[idx];
                idx++;
            }
        }

//        printColArray(offsets);
//        printColArray(fw);
    }

    private void printColArray(int[] p) {
        System.out.println("START");
        for(int c: p) {
            System.out.print(Integer.toHexString(c) + " ");
        }
        System.out.println("\nEND");
    }
}
