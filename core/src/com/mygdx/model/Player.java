package com.mygdx.model;

public class Player {

    private float x;
    private float y;
    private Direction direction;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        direction = Direction.NORTH;
    }

    public enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }

    public void move(int dir) {
        switch(direction) {
            case NORTH:
                y += dir;
                break;
            case EAST:
                x += dir;
                break;
            case SOUTH:
                y -= dir;
                break;
            case WEST:
                x -= dir;
                break;
        }
    }

    public void rotateLeft() {
        switch(direction) {
            case NORTH: direction = Direction.WEST;
            break;
            case EAST: direction = Direction.NORTH;
            break;
            case SOUTH: direction = Direction.EAST;
            break;
            case WEST: direction = Direction.SOUTH;
            break;
        }
    }

    public void rotateRight() {
        switch(direction) {
            case NORTH: direction = Direction.EAST;
                break;
            case EAST: direction = Direction.SOUTH;
                break;
            case SOUTH: direction = Direction.WEST;
                break;
            case WEST: direction = Direction.NORTH;
                break;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
