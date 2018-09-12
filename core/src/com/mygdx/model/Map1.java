package com.mygdx.model;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.screens.GameScreen;
import com.mygdx.game.PledgeGame;
import com.mygdx.enums.MapEnum;

public class Map1 extends AbstractMap {
    public Map1(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/map.tmx");
    }

    @Override
    public int getStartX() {
        return 2*32;//20
    }

    @Override
    public int getStartY() {
        return 2*32;//14
    }

    @Override
    public void zielErreicht(MapEnum nextMap) {
        super.zielErreicht(nextMap);
    }

    @Override
    public void showControls() {

    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }
}
