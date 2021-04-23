package com.aarya.rain.game;

import com.aarya.rain.AbstractGame;
import com.aarya.rain.Game;
import com.aarya.rain.entity.mob.Player;
import com.aarya.rain.graphics.GfxImage;
import com.aarya.rain.graphics.Renderer;
import com.aarya.rain.input.Keyboard;
import com.aarya.rain.input.Mouse;
import com.aarya.rain.level.Level;

public class GameManager implements AbstractGame {

    private Player player;
    private Level level;

    private GfxImage image;

    public GameManager() {
//        level = new SpawnLevel("/textures/level.png", 16, 16);
//        player = new Player(0, HEIGHT / 2, Keyboard.INSTANCE, this);
//        player.setLevel(level);
        image = new GfxImage("/textures/test-image-1.png");
    }

    @Override
    public void update(Game game, float dt) {
//        player.update();
//        level.update();
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

//        r.drawRect(Mouse.INSTANCE.getX(), Mouse.INSTANCE.getY(), 16, 16, -1);

        r.drawImage(image, Mouse.INSTANCE.getX(), Mouse.INSTANCE.getY());

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
