package com.mygdx.model.maps;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.screens.GameScreen;

import static com.mygdx.game.ResourcePaths.MAZE;

public class EndlessMazeMap extends AbstractMap {
    private int startX;
    private int startY;

    public EndlessMazeMap(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(MAZE);
    }

    @Override
    public int getStartX() {
        return 32;
    }

    @Override
    public int getStartY() {
        return 32;
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
        return nextMap;
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }
}
