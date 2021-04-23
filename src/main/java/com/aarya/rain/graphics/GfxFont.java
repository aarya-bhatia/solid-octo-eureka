package com.aarya.rain.graphics;

public class GfxFont {

    /**
     * the top row of pixels contains the markers.
     * blue marks the beginning of each letter and
     * yellow marks the end. These positions will be
     * used to get the width and offsets of the letters.
     */

    private GfxImage fontImage;
    private int[] offsets;
    private int[] fw; /* letter widths */
    private final int n = 58; /* number of letters */

    private final int col_blue = 0x0000ff;
    private final int col_yellow = 0xffff00;
    private final int alpha_mask = -1;

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
        int off = 0;

        for(int i = 0; i < fontImage.getW(); i++){
            if((fontImage.getP()[i] & alpha_mask) == col_blue) {
//                offsets[u] = i;
                off = i;
            }
            if((fontImage.getP()[i] & alpha_mask) == col_yellow) {
//                fw[u] = i - offsets[u];
                fw[idx++] = i - off;
            }
        }
    }
}
