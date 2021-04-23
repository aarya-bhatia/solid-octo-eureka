package com.aarya.rain.game;

import com.aarya.rain.AbstractGame;
import com.aarya.rain.Game;
import com.aarya.rain.graphics.GfxTile;
import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.input.Keyboard;
import com.aarya.rain.input.Mouse;

public class GameManager implements AbstractGame {

//    private Player player;
//    private Level level;

    float tmp = 0.0f;
    float speed = 10.0f;
    boolean play = true;
    float click = 0;

    private GfxTile image;

    public GameManager() {
//        level = new SpawnLevel("/textures/level.png", 16, 16);
//        player = new Player(0, HEIGHT / 2, Keyboard.INSTANCE, this);
//        player.setLevel(level);
        image = new GfxTile("/textures/animation.png", 16, 16);
    }

    @Override
    public void update(Game game, float dt) {
//        player.update();
//        level.update();

        if(click > 0) click--;

        if(Mouse.INSTANCE.getButton() == Mouse.RIGHT_CLICK && click == 0) {
            play = !play;
            click = Mouse.CLICK_RATE;
        }

        if(play) tmp = (tmp + dt * speed) % 2;
    }

    @Override
    public void render(Game game, Renderer r) {
//        int xScroll = this.game.getPlayer().getX() - this.game.getWidth() / 2;
//        int yScroll = this.game.getPlayer().getY() - this.game.getHeight() / 2;
//
//        this.renderer.setXOff(xScroll);
//        this.renderer.setYOff(yScroll);
//
//        this.game.getLevel().render(xScroll, yScroll, this.renderer);
//        this.game.getPlayer().render(renderer);
//
//        g.setColor(Color.WHITE);
//        g.drawString(String.format("Map X: %d, Y: %d", this.game.getPlayer().getX(), this.game.getPlayer().getY()), 500, 400);
//
//        g.setColor(Color.white);
//        g.fillRect(Mouse.getX() - 8, Mouse.getY() - 8, 16, 16);
//
//        g.drawString(String.format("MOUSE X:%d Y:%d Btn:%d",
//                Mouse.getX(), Mouse.getY(), Mouse.getButton()), 50, 50);

        r.drawImageTile(image,
                Mouse.INSTANCE.getX() - image.getTileW()/2,
                Mouse.INSTANCE.getY() - image.getTileH()/2,
                (int)tmp,
                1 - (int)tmp);
    }

    private static void testInput() {
        if(Mouse.INSTANCE.getButton() == Mouse.RIGHT_CLICK || Mouse.INSTANCE.getButton() == Mouse.LEFT_CLICK) {
            System.out.printf("Mouse x: %d, Mouse y: %d \n", Mouse.INSTANCE.getX(), Mouse.INSTANCE.getY());
        }
        if(Keyboard.INSTANCE.down()) {
            System.out.println("Key pressed: down");
        }
        if(Keyboard.INSTANCE.up()) {
            System.out.println("Key pressed: up");
        }
    }

    public static void main(String[] args) {
        Game game = new Game(new GameManager());
        game.start();
    }
}
