package com.aarya.rain.input;

import com.aarya.rain.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private static final int NUM_KEYS = 120;
    private final boolean[] keys = new boolean[NUM_KEYS];
    public boolean up, down, left, right;

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];

        if(Game.debug) {
            boolean flag = true;
            for(int i = 0; i < keys.length; i++) {
                if(keys[i]) {
                    if(flag) {
                        System.out.print("KEY: ");
                        flag = false;
                    }
                    System.out.print(KeyEvent.getKeyText(i) + "\t");
                }
            }
            System.out.println();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
}
