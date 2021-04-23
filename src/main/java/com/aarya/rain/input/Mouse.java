package com.aarya.rain.input;

import com.aarya.rain.Game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    /* singleton */
    public static final Mouse INSTANCE = new Mouse();

    public static final short LEFT_CLICK = 1;
    public static final short RIGHT_CLICK = 3;
    public static final int CLICK_RATE = 20;

    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseButton = -1;

    private Mouse() {}

    public int getX() { return mouseX; }
    public int getY() { return mouseY; }
    public int getButton() { return mouseButton; }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseButton = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseButton = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX()/Game.scale;
        mouseY = e.getY()/Game.scale;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX()/Game.scale;
        mouseY = e.getY()/Game.scale;
    }
}
