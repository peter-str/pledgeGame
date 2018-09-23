package com.mygdx.model;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.screens.GameScreen;

public class EndlessMazeMap extends AbstractMap {
    private int startX;
    private int startY;

    public EndlessMazeMap(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/randomMap.tmx");
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
    public void showControls() {

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
