package com.mygdx.enums;

import com.mygdx.game.PledgeGame;
import com.mygdx.model.maps.*;
import com.mygdx.screens.GameScreen;

public enum MapEnum {

    INTRODUCTION_1 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new IntroductionMap1(game, gameScreen, INTRODUCTION_2);
        }
    },

    INTRODUCTION_2 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new IntroductionMap2(game, gameScreen, INTRODUCTION_3);
        }
    },

    INTRODUCTION_3 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new IntroductionMap3(game, gameScreen, TUTORIALMAP_1);
        }
    },

    TUTORIALMAP_1 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap1(game, gameScreen, TUTORIALMAP_2);
        }
    },

    TUTORIALMAP_2 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap2(game, gameScreen, TUTORIALMAP_3);
        }
    },

    TUTORIALMAP_3 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap3(game, gameScreen, TUTORIALMAP_4);
        }
    },

    TUTORIALMAP_4 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap4(game, gameScreen, TUTORIALMAP_5);
        }
    },

    TUTORIALMAP_5 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap5(game, gameScreen, TUTORIALMAP_6);
        }
    },

    TUTORIALMAP_6 {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new TutMap6(game, gameScreen, MAP_1);
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
    },

    ENDLESS_MAZE {
        public AbstractMap getMap(PledgeGame game, GameScreen gameScreen) {
            return new EndlessMazeMap(game, gameScreen, ENDLESS_MAZE);
        }
    };

    public abstract AbstractMap getMap(PledgeGame game, GameScreen gameScreen);
}
