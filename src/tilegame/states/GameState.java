package tilegame.states;

import tilegame.Game;
import tilegame.entities.creatures.Player;
import tilegame.tiles.Tile;

import java.awt.Graphics;

import tilegame.worlds.World;

public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game){
        super(game);
        player = new Player(game,0, 0);
        world = new World(game,"res/worlds/mundo1.txt");

        game.getGameCamera().move(100, 200);
    }

    @Override
    public void tick() {
        world.tick();
        player.tick();
        game.getGameCamera().move(1, 1);
    }

    @Override
    public void render(Graphics g){
        world.render(g);
        player.render(g);
    }

}
