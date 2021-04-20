package com.aarya.rain.entity.mob;

import com.aarya.rain.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;

    public Player(Keyboard input) {
        this.input = input;
    }

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    public void update() {

        int dx = 0, dy = 0;

        if(input.up) {
            dy--;
        }
        else if(input.down) {
            dy++;
        }
        if(input.right) {
            dx++;
        }
        else if(input.left) {
            dx--;
        }

        if(dx != 0 || dy != 0) {
            move(dx, dy);
        }
    }

    public void render() {

    }
}
