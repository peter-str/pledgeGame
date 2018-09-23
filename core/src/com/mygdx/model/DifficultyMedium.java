package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class DifficultyMedium implements Difficulty {

    private Texture fieldOfView_medium;

    public DifficultyMedium() {
        fieldOfView_medium = new Texture(Gdx.files.internal("core/assets/fov_medium.png"));
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return fieldOfView_medium;
    }

    @Override
    public void createMap() {
        new LabyrinthErstellen2(7, 7);
    }

    @Override
    public boolean hasTexture() {
        return true;
    }
}
