package tilegame.entities.creatures;

import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.Entity;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

import java.awt.*;

public abstract class Creature extends Entity {

    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        moveX();
        moveY();
    }

    public void moveX() {
        if (xMove > 0){
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && (!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width - 0.5f;
            }
        } else if (xMove < 0) {
            int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;
            if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT) && (!collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT))){
                x += xMove;
            } else {
                x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
            }
        }
    }

    public void moveY() {
        if (yMove < 0){ //cima
            int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) ((x + bounds.x) / Tile.TILEHEIGHT), ty) && (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEHEIGHT, ty))){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
            }
        } else if (yMove > 0) { //baixo
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
            if (!collisionWithTile((int) ((x + bounds.x) / Tile.TILEWIDTH), ty) && (!collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))){
                y += yMove;
            } else {
                y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 0.5f;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
