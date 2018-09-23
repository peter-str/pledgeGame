package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;

public class DifficultyExpert implements Difficulty {

    public DifficultyExpert() {
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return null;
    }

    @Override
    public void createMap() {
        new LabyrinthErstellen2(12, 12);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }
}
