package tilegame.entities.creatures;

import tilegame.Game;
import tilegame.Handler;
import tilegame.gfx.Animation;
import tilegame.gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Animation animDown, animUp, animLeft, animRight;
    private Animation animDownIdle, animUpIdle, animLeftIdle, animRightIdle;
    private float xLastMove, yLastMove;

    public Player(Handler handler, float x, float y){
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATE_HEIGHT);

        bounds.x = 16;
        bounds.y = 32;
        bounds.width = 32;
        bounds.height = 32;

        animDown = new Animation(250, Assets.player_down);
        animUp = new Animation(250, Assets.player_up);
        animLeft = new Animation(250, Assets.player_left);
        animRight = new Animation(250, Assets.player_right);

        animDownIdle = new Animation(250, Assets.player_idle_down);
        animUpIdle = new Animation(250, Assets.player_idle_up);
        animLeftIdle = new Animation(250, Assets.player_idle_left);
        animRightIdle = new Animation(250, Assets.player_idle_right);
    }

    @Override
    public void tick() {
        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();
        animDownIdle.tick();
        animUpIdle.tick();
        animLeftIdle.tick();
        animRightIdle.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }

    public void getInput(){
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), (int)(y - handler.getGameCamera().getyOffset()), width, height, null);

        //g.setColor(Color.red);
        //g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0) {
            xLastMove = xMove;
            return animRight.getCurrentFrame();
        }
        if(xMove > 0){
            xLastMove = xMove;
            return animLeft.getCurrentFrame();
        }
        if(yMove > 0) {
            return animDown.getCurrentFrame();
        }
        if(yMove < 0) {
            return animUp.getCurrentFrame();
        }

        if (xMove == 0 && xLastMove < 0) {
            return animRightIdle.getCurrentFrame();
        }
        if (xMove == 0 && xLastMove > 0) {
            return animLeftIdle.getCurrentFrame();
        }
        return animDownIdle.getCurrentFrame();
    }

}
