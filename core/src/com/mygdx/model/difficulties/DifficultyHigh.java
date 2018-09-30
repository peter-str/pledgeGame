package com.mygdx.model.difficulties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.LabyrinthErstellen2;

import static com.mygdx.game.ResourcePaths.HIGH;

public class DifficultyHigh implements Difficulty {

    private Texture fieldOfView_low;

    public DifficultyHigh() {
        fieldOfView_low = new Texture(Gdx.files.internal(HIGH));
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return fieldOfView_low;
    }

    @Override
    public void createMap() {
        new LabyrinthErstellen2(10, 10);
    }

    @Override
    public boolean hasTexture() {
        return true;
    }
}
