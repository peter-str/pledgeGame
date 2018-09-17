package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class DifficultyEasy implements Difficulty {

    private Texture fieldOfView_high;

    public DifficultyEasy() {
        fieldOfView_high = new Texture(Gdx.files.internal("core/assets/fov_high.png"));
    }

    @Override
    public Texture getFOVTexture() {
        return fieldOfView_high;
    }

    @Override
    public TiledMap createMap() {
        return null;
    }
}
