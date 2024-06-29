package tilegame;

import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {

    private Display display;
    private Thread thread;
    private boolean running = false;

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
        Assets.init();
    }

    int x = 0;

    private void tick() {
        x += 1;
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();

        if (bs == null){
            display.getCanvas().createBufferStrategy(3); // maximo que vamos usar
            return;
        }

        g = bs.getDrawGraphics();
        g.clearRect(0,0, width, height);

        g.drawImage(Assets.grass, x, 0, null);

        bs.show();
        g.dispose();
    }

    public void run(){
        init();

        int fps = 60;
        // maximum amount of time that we are allow
        // to have to run/tick methods to achieve 60 times per second
        double timePerTickNanoSeconds = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            // basically, now - lastTime we get the amount of time past since we last called this line of code below
            // that way, we divide that amount of time by the maximum amout of time that we have to run tick/render methods
            // and we add it to delta variable
            // this is going to add to delta variable, how much time we have until we call this methos tick and render again
            // tells the computer when and WHEN NOT to call this tick and render (to see if we need to call them or not)
            delta += (now - lastTime) / timePerTickNanoSeconds;
            timer += now - lastTime; //we are going to add the amount of time that past since this block below last ran

            lastTime = now;

            // ou seja, se o delta for maior que 1, tem que tick/render agora
            // isto é tipo como falar, roda agora porque ja estamos na hora de rodar o proximo frame
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                // reseta ele
                delta--;
            }

            if (timer >= 1000000000){
                System.out.println("Frames: " + ticks);
                ticks = 0;
                timer = 0;
            }
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
