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
        if(input.up) {
            y--;
        }
        else if(input.down) {
            y++;
        }
        if(input.right) {
            x++;
        }
        else if(input.left) {
            x--;
        }
    }

    public void render() {

    }
}
