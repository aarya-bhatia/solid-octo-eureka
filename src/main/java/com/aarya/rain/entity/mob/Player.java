package com.aarya.rain.entity.mob;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;
import com.aarya.rain.input.Keyboard;

public class Player extends Mob {

    private final Keyboard input;
    private short anim = 0;

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
    }

    @Override
    public void update() {
        int dx = 0, dy = 0;

        if (input.up) {
            dy--;
        } else if (input.down) {
            dy++;
        }
        if (input.right) {
            dx++;
        } else if (input.left) {
            dx--;
        }

        if (dx != 0 || dy != 0) {
            move(dx, dy);
            moving = true;
        }
        else {
            moving = false;
        }
    }

    /* Cycle the sprite 2, 1, 2, 3, ... */
    private short nextAnimationFrame() {
        anim = (short) ((anim + 1) % 1000);

        short tmp = 2;

        if(moving) {
            short speed = 40;
            if(anim % speed > (speed >> 2) && anim % 40 < (speed >> 1)) {
                tmp = 1;
            }
            else if(anim % speed > (speed >> 1) + (speed >> 2)) {
                tmp = 3;
            }
        }

        return tmp;
    }

    @Override
    public void render(Screen screen) {
        short tmp = nextAnimationFrame();
        switch (dir) {
            case 0 -> screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.north." + tmp));
            case 1 -> screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.east." + tmp));
            case 2 -> screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.south." + tmp));
            case 3 -> screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.west." + tmp));
        }
    }
}
