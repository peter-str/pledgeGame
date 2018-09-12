package com.mygdx.enums;

import com.mygdx.game.PledgeGame;
import com.mygdx.model.AbstractMap;
import com.mygdx.model.Map1;
import com.mygdx.model.TutMap1;
import com.mygdx.screens.GameScreen;

public enum MapEnum {

    TUTORIALMAP_1 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap1(game, gameScreen, MAP_1);
        }
    },

    MAP_1 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new Map1(game, gameScreen, MAP_1);
        }
    };

    public abstract AbstractMap getMap(PledgeGame game, GameScreen gameScreen);
}
