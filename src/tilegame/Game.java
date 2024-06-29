package tilegame;

import tilegame.display.Display;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    private Thread thread;
    private boolean running = false;

    private BufferedImage test;
    private SpriteSheet sheet;

    private BufferStrategy bs;
    private Graphics g;

    public int width, height;
    public String title;

    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }

    private void init(){
        display = new Display(title, width, height);
        test = ImageLoader.loadImage("/textures/sheet.png");
        sheet = new SpriteSheet(test);
    }

    private void tick() {

    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null){
            display.getCanvas().createBufferStrategy(3); // maximo que vamos usar
            return;
        }

        g = bs.getDrawGraphics();

        g.clearRect(0,0, width, height);

        g.drawImage(sheet.crop(0, 0,32, 32), 5, 5, null);
        g.drawImage(sheet.crop(32, 0,32, 32), 40, 40, null);

        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        while (running) {
            tick();
            render();
        }

        stop();
    }

    public synchronized void start(){
        if (running)
            return;

        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (!running)
            return;

        running = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
