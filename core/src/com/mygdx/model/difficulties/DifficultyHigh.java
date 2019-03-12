package com.mygdx.model.difficulties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

import static com.mygdx.game.ResourcePaths.HIGH;

public class DifficultyHigh implements Difficulty {

    private Texture fieldOfView_low;
    private MazeCreatorClass mazeCreator;

    public DifficultyHigh() {
        fieldOfView_low = new Texture(Gdx.files.internal(HIGH));
    }

    public DifficultyHigh(int size) {
        fieldOfView_low = new Texture(Gdx.files.internal(HIGH));
        createMap(size);
    }

    @Override
    public Texture getFovTexture() {
        return fieldOfView_low;
    }

    @Override
    public void createMap(int size) {
        mazeCreator = new MazeCreatorClass(size, size);
    }

    @Override
    public boolean hasTexture() {
        return true;
    }

    @Override
    public TiledMap getTiledMap() {
        return mazeCreator.getTiledMap();
    }
}
