package tilegame.states;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.creatures.Player;
import tilegame.tiles.Tile;

import java.awt.Graphics;

import tilegame.worlds.World;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Handler handler){
        super(handler);
        world = new World(handler,"res/worlds/mundo1.txt");
        handler.setWorld(world);
        player = new Player(handler,0, 0);

        handler.getGameCamera().move(100, 200);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
        handler.getGameCamera().move(1, 1);
    }

    @Override
    public void render(Graphics g){
        world.render(g);
        player.render(g);
    }

}
