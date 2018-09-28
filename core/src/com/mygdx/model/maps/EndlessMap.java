package com.mygdx.model.maps;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.screens.GameScreen;

public class EndlessMap extends AbstractMap {

    private int startX;
    private int startY;

    public EndlessMap(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/newfile.tmx");
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    public void setStartX(int x) {
        startX = x;
    }

    public void setStartY(int y) {
        startY = y;
    }

    @Override
    public boolean getTutorialFlag() {
        return false;
    }

    @Override
    public MapEnum getNextMap() {
        return null;
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }
}
