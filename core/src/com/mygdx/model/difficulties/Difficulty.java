package com.mygdx.model.difficulties;

import com.badlogic.gdx.graphics.Texture;

public interface Difficulty {
    Texture getFovTexture();
    void createMap(int size);
    boolean hasTexture();
}
