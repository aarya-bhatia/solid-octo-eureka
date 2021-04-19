package com.aarya.rain;

import com.aarya.rain.graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window extends Canvas {

    private JFrame frame = new JFrame();

    /* the buffer stores the pixels in memory to render in the next frame */
    private BufferStrategy bufferStrategy = getBufferStrategy();

    private BufferedImage bfImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

    private int[] pixels = ((DataBufferInt) bfImage.getRaster().getDataBuffer()).getData();

    private Screen screen = new Screen(Game.width, Game.height);

    public Window() {
        setPreferredSize(new Dimension(Game.width * Game.scale,Game.height * Game.scale));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(Game.title);
        frame.add(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /* Rendering happens as fast as possible */
    public void render() {
        if(bufferStrategy == null) {
            /* Triple buffering for speed improvement */
            createBufferStrategy(3);
            return;
        }

        screen.render();

        Graphics context = bufferStrategy.getDrawGraphics();
        context.setColor(new Color(80,40,100));
        context.fillRect(0, 0, getWidth(), getHeight());
        context.dispose(); // clear graphics

        bufferStrategy.show(); // buffer swapping
    }
}
