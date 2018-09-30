package com.mygdx.model.tutorialStrategies.level_5;

import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy5C implements TutorialStrategy {
    @Override
    public void algorithm(GameScreen gameScreen) {
        if(!gameScreen.getPlayer().isLeft()) {
            gameScreen.getPlayer().rotateLeft();
            gameScreen.rotateCamera("left");
            gameScreen.getPlayer().move(32);
        } else if(gameScreen.getPlayer().isTop()) {
            gameScreen.getPlayer().rotateRight();
            gameScreen.rotateCamera("right");
        }
        if(!gameScreen.getPlayer().isTop()) {
            gameScreen.getPlayer().move(32);
        }
    }
}
