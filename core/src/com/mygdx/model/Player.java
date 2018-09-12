package com.mygdx.model;

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
    private boolean moveCameraUpBool;
    private boolean moveCameraDownBool;

    private Player_State state;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.animX = x;
        this.animY = y;
        this.direction = Direction.NORTH;
        this.state = Player_State.STANDING;
    }

    public enum Player_State {
        STANDING,
        WALKING;
    }

    public void move(int dir) {
        if(state != Player_State.STANDING)
            return;

        switch (direction) {
            case NORTH:
                moveCameraUpBool = dir > 0;
                moveCameraDownBool = dir < 0;
                initializeMove(0, dir);
                y += dir;
                break;
            case EAST:
                moveCameraUpBool = dir > 0;
                moveCameraDownBool = dir < 0;
                initializeMove(dir, 0);
                x += dir;
                break;
            case SOUTH:
                moveCameraUpBool = dir > 0;
                moveCameraDownBool = dir < 0;
                initializeMove(0, -dir);
                y -= dir;
                break;
            case WEST:
                moveCameraUpBool = dir > 0;
                moveCameraDownBool = dir < 0;
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
        moveCameraUpBool = false;
        moveCameraDownBool = false;
        this.animX = destX;
        this.animY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;
    }

    public void update(float delta) {
        System.out.println(y + " " + animY);
        if(state == Player_State.WALKING) {
            animTimer += delta;
            animX = Interpolation.linear.apply(srcX, destX, animTimer/ANIM_TIME);
            animY = Interpolation.linear.apply(srcY, destY, animTimer/ANIM_TIME);
            if(animTimer > ANIM_TIME)
                finishMove();
        }
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getXDivided() {
        return x / 32;
    }

    public float getYDivided() {
        return y / 32;
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

    public boolean getMoveCameraUpBool() {
        return moveCameraUpBool;
    }

    public boolean getMoveCameraDownBool() {
        return moveCameraDownBool;
    }
}
