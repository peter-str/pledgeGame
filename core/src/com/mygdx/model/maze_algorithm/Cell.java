package com.mygdx.model.maze_algorithm;

public class Cell {
    int x;
    int y;
    int[] walls = {1, 1, 1, 1};

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean checkWalls() {
        if (walls[0] == 1 && walls[1] == 1 && walls[2] == 1 && walls[3] == 1)
            return true;
        return false;
    }
}
