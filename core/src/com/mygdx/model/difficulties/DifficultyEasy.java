package com.mygdx.model.difficulties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.LabyrinthErstellen2;

import static com.mygdx.game.ResourcePaths.EASY;

public class DifficultyEasy implements Difficulty {

    private Texture fieldOfView_high;

    public DifficultyEasy() {
        fieldOfView_high = new Texture(Gdx.files.internal(EASY));
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return fieldOfView_high;
    }

    @Override
    public void createMap() {
        new LabyrinthErstellen2(5, 5);
    }

    @Override
    public boolean hasTexture() {
        return true;
    }
}
