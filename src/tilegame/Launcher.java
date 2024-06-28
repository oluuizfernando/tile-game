package tilegame;

import tilegame.display.Display;

public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("Titulo", 640, 360);
        game.start();
    }

}
