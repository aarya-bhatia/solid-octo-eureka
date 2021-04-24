package com.aarya.rain;

import com.aarya.rain.graphics.Renderer;

public interface AbstractGame {
    void update(Game game, float dt);
    void render(Game game, Renderer r);
    void dispose();
}
