package com.aarya.rain.entity.mob;

import com.aarya.rain.graphics.Screen;
import com.aarya.rain.graphics.Sprite;
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

    public void render(Screen screen) {
        switch(dir) {
            case 0: {
                // North
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.north.1"));
                break;
            }
            case 1: {
                // East
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.east.1"));
                break;
            }
            case 2: {
                // South
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.south.1"));
                break;
            }
            case 3: {
                // West
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.west.1"));
                break;
            }
            default: {
                screen.renderPlayer(x, y, Sprite.SPRITES.get("characters.boy.south.1"));
                break;
            }
        }
    }
}
