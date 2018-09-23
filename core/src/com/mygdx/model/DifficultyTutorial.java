package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;

public class DifficultyTutorial implements Difficulty {

    public DifficultyTutorial() {
        createMap();
    }

    @Override
    public Texture getFOVTexture() {
        return null;
    }

    @Override
    public void createMap() {
        new LabyrinthErstellen2(3, 3);
    }

    @Override
    public boolean hasTexture() {
        return false;
    }
}
