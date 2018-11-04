package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

public class DifficultyExpert implements Difficulty {

    public DifficultyExpert() {

    }

    public DifficultyExpert(int size) {
        createMap(size);
    }

    @Override
    public Texture getFovTexture() {
        return null;
    }

    @Override
    public void createMap(int size) {
        new MazeCreatorClass(size, size);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }
}
