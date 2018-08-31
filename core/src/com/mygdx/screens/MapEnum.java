package com.mygdx.screens;

import com.mygdx.game.PledgeGame;
import com.mygdx.maps.AbstractMap;
import com.mygdx.maps.Map1;
import com.mygdx.maps.TutMap1;

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
