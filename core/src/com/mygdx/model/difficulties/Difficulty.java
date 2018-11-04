package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public interface Difficulty {
    Texture getFovTexture();
    void createMap(int size);
    boolean hasTexture();
}
