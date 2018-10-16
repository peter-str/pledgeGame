package com.mygdx.model.maps;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyEasy;
import com.mygdx.screens.GameScreen;

public class IntroductionMap1 extends AbstractMap {
    public IntroductionMap1(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/Introduction1.tmx");
    }

    @Override
    public int getStartX() {
        return 32 * 3;//20
    }

    @Override
    public int getStartY() {
        return 32 * 4;//14
    }

    @Override
    public boolean getTutorialFlag() {
        return false;
    }

    @Override
    public void zielErreicht(MapEnum nextMap, boolean tutorialFlag) {
            super.zielErreicht(nextMap, false);
    }

    @Override
    public void showInstructions(int x, int y) {
        if((x == 14*32 || x == 17*32) && y == 9*32) {

        }
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyEasy();
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }
}
