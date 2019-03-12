package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.model.maze_algorithm.MazeCreatorClass;

public class DifficultyTutorial implements Difficulty {
    private MazeCreatorClass mazeCreator;

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
        mazeCreator = new MazeCreatorClass(size, size);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }

    @Override
    public TiledMap getTiledMap() {
        return mazeCreator.getTiledMap();
    }
}
