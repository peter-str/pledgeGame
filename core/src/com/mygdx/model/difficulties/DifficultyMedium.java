package com.mygdx.model.difficulties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

import static com.mygdx.game.ResourcePaths.MEDIUM;

public class DifficultyMedium implements Difficulty {

    private Texture fieldOfView_medium;

    public DifficultyMedium() {
        fieldOfView_medium = new Texture(Gdx.files.internal(MEDIUM));
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return fieldOfView_medium;
    }

    @Override
    public void createMap() {
        new MazeCreatorClass(7, 7);
    }

    @Override
    public boolean hasTexture() {
        return true;
    }
}
