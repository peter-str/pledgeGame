package com.mygdx.model.difficulties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

import static com.mygdx.game.ResourcePaths.MEDIUM;

public class DifficultyMedium implements Difficulty {

    private Texture fieldOfView_medium;
    private MazeCreatorClass mazeCreator;

    public DifficultyMedium() {
        fieldOfView_medium = new Texture(Gdx.files.internal(MEDIUM));
    }

    public DifficultyMedium(int size) {
        fieldOfView_medium = new Texture(Gdx.files.internal(MEDIUM));
        createMap(size);
    }

    @Override
    public Texture getFovTexture() {
        return fieldOfView_medium;
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
