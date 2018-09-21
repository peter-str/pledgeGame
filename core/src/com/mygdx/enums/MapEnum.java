package com.mygdx.enums;

import com.mygdx.game.PledgeGame;
import com.mygdx.model.*;
import com.mygdx.screens.GameScreen;

public enum MapEnum {

    TUTORIALMAP_1 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap1(game, gameScreen, MAP_1);
        }
    },

    MAP_1 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new Map1(game, gameScreen, MAP_2);
        }
    },

    MAP_2 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new Map2(game, gameScreen, MAP_2);
        }
    },

    ENDLESS {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new EndlessMap(game, gameScreen, MAP_2);
        }
    };

    public abstract AbstractMap getMap(PledgeGame game, GameScreen gameScreen);
}
