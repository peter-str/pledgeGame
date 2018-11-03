package com.mygdx.model.difficulties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

import static com.mygdx.game.ResourcePaths.HIGH;

public class DifficultyHigh implements Difficulty {

    private Texture fieldOfView_low;

    public DifficultyHigh(boolean createMap) {
        fieldOfView_low = new Texture(Gdx.files.internal(HIGH));
        if(createMap)
            createMap();
    }

    @Override
    public Texture getFovTexture() {
        return fieldOfView_low;
    }

    @Override
    public void createMap() {
        new MazeCreatorClass(10, 10);
    }

    @Override
    public boolean hasTexture() {
        return true;
    }
}
