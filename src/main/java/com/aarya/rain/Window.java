package com.aarya.rain;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Window {

    private JFrame frame;
    private Canvas canvas;

    private BufferStrategy bufferStrategy;

    /* an image to draw things on to */
    private BufferedImage bfImage;

    private int[] pixels;

    public Window() {
        init();
    }

    public void init() {
        canvas = new Canvas();

        Dimension size = new Dimension(Game.width * Game.scale,Game.height * Game.scale);
        canvas.setPreferredSize(size);

        /* the buffer stores the pixels in memory to render in the next frame */
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();

        bfImage = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);

        /* we need a raster to manipulate the pixels of the image */
        pixels = ((DataBufferInt) bfImage.getRaster().getDataBuffer()).getData();


        frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(Game.title);
        frame.add(canvas);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    /* Rendering as fast as possible */
    public void render() {

        if(bufferStrategy == null) {
            /* Triple buffering for speed improvement */
            canvas.createBufferStrategy(3);
            return;
        }

        Graphics context = bufferStrategy.getDrawGraphics();
        /* all the graphics happens here */
        context.setColor(new Color(80,40,100));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        /* end */
        context.dispose(); // clear graphics

        bufferStrategy.show(); // buffer swapping
    }
}
