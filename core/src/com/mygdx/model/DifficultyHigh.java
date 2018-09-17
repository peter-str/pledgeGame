package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class DifficultyHigh implements Difficulty {

    private Texture fieldOfView_low;

    public DifficultyHigh() {
        fieldOfView_low = new Texture(Gdx.files.internal("core/assets/fov_low.png"));
    }

    @Override
    public Texture getFOVTexture() {
        return fieldOfView_low;
    }

    @Override
    public TiledMap createMap() {
        return null;
    }
}
