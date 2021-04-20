package com.aarya.rain.entity.mob;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;
import com.aarya.rain.input.Keyboard;

public class Player extends Mob {

    private Keyboard input;
    private int animation = 0;
    private boolean walking = false;

    private static final short ANIMATION_SPEED = 40;

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
            walking = true;
        }
        else {
            walking = false;
        }
    }

    /* Cycle the sprite 2, 1, 2, 3, ... */
    private short nextAnimationFrame() {
        animation = (animation + 1) % 9999;

        short tmp = 2;

        if(walking) {
            if(animation % ANIMATION_SPEED > (ANIMATION_SPEED >> 2)
                    && animation % 40 < (ANIMATION_SPEED >> 1)) {
                tmp = 1;
            }
            else if(animation % ANIMATION_SPEED >
                    ((ANIMATION_SPEED >> 1) + (ANIMATION_SPEED >> 2))
                        && animation % ANIMATION_SPEED < ANIMATION_SPEED) {
                tmp = 3;
            }
        }

        return tmp;
    }

    public void render(Screen screen) {
        short tmp = nextAnimationFrame();

        switch (dir) {
            case 0: {
                // North
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.north." + tmp));
                break;
            }
            case 1: {
                // East
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.east." + tmp));
                break;
            }
            case 2: {
                // South
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.south." + tmp));
                break;
            }
            case 3: {
                // West
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.west." + tmp));
                break;
            }
        }
    }
}
