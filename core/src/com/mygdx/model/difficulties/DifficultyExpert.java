package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

public class DifficultyExpert implements Difficulty {

    public DifficultyExpert() {
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return null;
    }

    @Override
    public void createMap() {
        new MazeCreatorClass(12, 12);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }
}
