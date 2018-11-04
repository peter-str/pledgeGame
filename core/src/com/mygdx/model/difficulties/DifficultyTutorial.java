package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

public class DifficultyTutorial implements Difficulty {

    public DifficultyTutorial() {

    }

    public DifficultyTutorial(int size) {
        createMap(size);
    }

    @Override
    public Texture getFovTexture() {
        return null;
    }

    @Override
    public void createMap(int size) {
        new MazeCreatorClass(3, 3);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }
}
