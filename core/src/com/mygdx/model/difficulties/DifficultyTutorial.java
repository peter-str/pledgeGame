package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

public class DifficultyTutorial implements Difficulty {

    public DifficultyTutorial(boolean createMap) {
        if(createMap)
            createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return null;
    }

    @Override
    public void createMap() {
        new MazeCreatorClass(3, 3);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }
}
