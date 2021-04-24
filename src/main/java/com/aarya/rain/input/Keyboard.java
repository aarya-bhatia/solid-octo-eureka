package com.aarya.rain.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    public static final Keyboard INSTANCE = new Keyboard();
    private static final int NUM_KEYS = 256;
    private final boolean[] keys = new boolean[NUM_KEYS];

    private Keyboard(){}

    public boolean up() {
        return keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
    }

    public boolean down() {
        return keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
    }

    public boolean right() {
        return keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
    }

    public boolean left() {
        return keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = false;
    }

    public boolean isKey(int k) {
        if(k < 0 || k >= keys.length) { return false; }
        return keys[k];
    }
}
