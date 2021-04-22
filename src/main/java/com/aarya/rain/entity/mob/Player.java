package com.aarya.rain.entity.mob;

import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.graphics.Sprite;
import com.aarya.rain.input.Keyboard;
import com.aarya.rain.input.Mouse;

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

        if (input.up()) {
            dy--;
        } else if (input.down()) {
            dy++;
        }
        if (input.right()) {
            dx++;
        } else if (input.left()) {
            dx--;
        }

        if (dx != 0 || dy != 0) {
            move(dx, dy);
            moving = true;
        }
        else {
            moving = false;
        }

        updateShooting();
    }

    private void updateShooting() {
        if(Mouse.getButton() == Mouse.LEFT_CLICK) {
//            double dy = Mouse.getY() - (Game.height * Game.scale) / 2;
//            double dx = Mouse.getX() - (Game.width * Game.scale) / 2;
//            shoot(x, y, Math.atan2(dy, dx));
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
    public void render(Renderer renderer) {
        short tmp = nextAnimationFrame();
        switch (dir) {
            case 0 -> renderer.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.north." + tmp));
            case 1 -> renderer.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.east." + tmp));
            case 2 -> renderer.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.south." + tmp));
            case 3 -> renderer.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.west." + tmp));
        }
    }
}
