package com.mygdx.model.tutorialStrategies.level_2;

import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy2C implements TutorialStrategy {
    @Override
    public void algorithm(GameScreen gameScreen) {
        if(!gameScreen.getPlayer().isTop())
            gameScreen.getPlayer().move(32);
        if(gameScreen.getPlayer().isTop()) {
           gameScreen.getPlayer().move(-32);
        }
    }
}
