package com.mygdx.model.tutorialStrategies.level_1;

import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy1A implements TutorialStrategy {
    @Override
    public void algorithm(GameScreen gameScreen) {
        if(!gameScreen.getPlayer().isTop()) {
            gameScreen.getPlayer().move(32);
        }
    }
}
