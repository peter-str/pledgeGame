package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.mygdx.enums.Direction;

public class Player {

    private int x;
    private int y;
    private boolean top, right, left, bottom;
    private Direction direction;
    private float animX, animY;
    private int srcX, srcY;
    private int destX, destY;
    private float animTimer;
    private float ANIM_TIME = 0.5f;
    private TextureAtlas textureAtlas;
    private Sprite sprite;
    private float walkTimer;
    private Animation walking;
    private TextureRegion standing;
    private boolean moveRequestThisFrame;
    private Player_State state;
    private int revCounter = 0;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.animX = x;
        this.animY = y;
        this.direction = Direction.NORTH;
        this.state = Player_State.STANDING;

        textureAtlas = new TextureAtlas(Gdx.files.internal("core/assets/player/textures.atlas"));
        standing = textureAtlas.findRegion("standing_north");
        this.sprite = new Sprite(standing);

        walking = new Animation(0.5f/2f, textureAtlas.findRegions("walking_north"), PlayMode.LOOP_PINGPONG);
    }

    public enum Player_State {
        STANDING,
        WALKING;
    }

    public void move(int dir) {
        if(state == Player_State.WALKING) {
            moveRequestThisFrame = true;
            return;
        }

        switch (direction) {
            case NORTH:
                initializeMove(0, dir);
                y += dir;
                break;
            case EAST:
                initializeMove(dir, 0);
                x += dir;
                break;
            case SOUTH:
                initializeMove(0, -dir);
                y -= dir;
                break;
            case WEST:
                initializeMove(-dir, 0);
                x -= dir;
                break;
        }
    }

    private void initializeMove(int dx, int dy) {
        this.srcX = x;
        this.srcY = y;
        this.destX = x + dx;
        this.destY = y + dy;
        this.animX = x;
        this.animY = y;
        animTimer = 0f;
        state = Player_State.WALKING;
    }

    private void finishMove() {
        state = Player_State.STANDING;
        this.animX = destX;
        this.animY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;
    }

    public void update(float delta) {
        if(state == Player_State.WALKING) {
            animTimer += delta;
            walkTimer += delta;
            animX = Interpolation.linear.apply(srcX, destX, animTimer/ANIM_TIME);
            animY = Interpolation.linear.apply(srcY, destY, animTimer/ANIM_TIME);
            if(animTimer > ANIM_TIME) {
                float leftOverTime = animTimer-ANIM_TIME;
                walkTimer -= leftOverTime;
                finishMove();
                if(moveRequestThisFrame)
                    //move(32);
                    return;
                else
                    walkTimer = 0;
            }
        }
        moveRequestThisFrame = false;
    }

    public void rotateLeft() {
        switch (direction) {
            case NORTH:
                direction = Direction.WEST;
                break;
            case EAST:
                direction = Direction.NORTH;
                break;
            case SOUTH:
                direction = Direction.EAST;
                break;
            case WEST:
                direction = Direction.SOUTH;
                break;
        }
        revCounter++;
    }

    public void rotateRight() {
        switch (direction) {
            case NORTH:
                direction = Direction.EAST;
                break;
            case EAST:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.NORTH;
                break;
        }
        revCounter--;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isBottom() {
        return bottom;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public float getAnimX() {
        return animX;
    }

    public float getAnimY() {
        return animY;
    }

    public int getRevCounter() {
        return revCounter;
    }

    public Sprite getSprite() {
        if(state == Player_State.WALKING) {
            sprite.setRegion((TextureRegion) walking.getKeyFrame(walkTimer));
            return sprite;
        }
        else
            sprite.setRegion(standing);
            return sprite;
    }
}
