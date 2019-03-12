package com.mygdx.enums;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.maps.*;

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
            return new IntroductionMap3(game, TUTORIALMAP_1, false);
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
            return new TutMap6(game, TUTORIALMAP_7);
        }
    },

    TUTORIALMAP_7 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap7(game, TUTORIALMAP_8);
        }
    },

    TUTORIALMAP_8 {
        public AbstractMap getMap(PledgeGame game) {
            return new TutMap8(game, INTRODUCTION_3_2);
        }
    },

    INTRODUCTION_3_2{
        public AbstractMap getMap(PledgeGame game) {
            return new IntroductionMap3(game, null, true);
        }
    },

    ENDLESS_MAZE {
        public AbstractMap getMap(PledgeGame game) {
            return new EndlessMazeMap(game, null, null);
        }
    };

    public abstract AbstractMap getMap(PledgeGame game);
}
