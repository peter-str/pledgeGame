package com.mygdx.enums;

import com.mygdx.game.PledgeGame;
import com.mygdx.model.maps.*;
import com.mygdx.screens.GameScreen;

public enum MapEnum {

    INTRODUCTION_1 {
        public AbstractMap getMap(PledgeGame game) {
            return new IntroductionMap1(game, INTRODUCTION_2);
        }
    },

    INTRODUCTION_2 {
        public AbstractMap getMap(PledgeGame game) {
            return new IntroductionMap2(game, INTRODUCTION_3);
        }
    },

    INTRODUCTION_3 {
        public AbstractMap getMap(PledgeGame game) {
            return new IntroductionMap3(game, TUTORIALMAP_1);
        }
    },

    TUTORIALMAP_1 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap1(game, TUTORIALMAP_2);
        }
    },

    TUTORIALMAP_2 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap2(game, TUTORIALMAP_3);
        }
    },

    TUTORIALMAP_3 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap3(game, TUTORIALMAP_4);
        }
    },

    TUTORIALMAP_4 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap4(game, TUTORIALMAP_5);
        }
    },

    TUTORIALMAP_5 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap5(game, TUTORIALMAP_6);
        }
    },

    TUTORIALMAP_6 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap6(game, INTRODUCTION_3);
        }
    },

    MAP_1 {
        public AbstractMap getMap(PledgeGame game) {
            return new Map1(game, MAP_2);
        }
    },

    MAP_2 {
        public AbstractMap getMap(PledgeGame game) {
            return new Map2(game, MAP_2);
        }
    },

    ENDLESS {
        public AbstractMap getMap(PledgeGame game) {
            return new EndlessMap(game, MAP_2);
        }
    },

    ENDLESS_MAZE {
        public AbstractMap getMap(PledgeGame game) {
            return new EndlessMazeMap(game, ENDLESS_MAZE);
        }
    };

    public abstract AbstractMap getMap(PledgeGame game);
}
