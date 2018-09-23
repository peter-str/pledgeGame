package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public interface Difficulty {
    Texture getFOVTexture();
    void createMap();
    boolean hasTexture();
}
