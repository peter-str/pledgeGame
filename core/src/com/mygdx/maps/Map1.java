package com.mygdx.maps;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.screens.GameScreen;
import com.mygdx.game.PledgeGame;
import com.mygdx.screens.MapEnum;

public class Map1 extends AbstractMap {
    public Map1(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/map.tmx");
    }

    @Override
    public float getStartX() {
        return 25*32;//20
    }

    @Override
    public float getStartY() {
        return 4*32;//14
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
