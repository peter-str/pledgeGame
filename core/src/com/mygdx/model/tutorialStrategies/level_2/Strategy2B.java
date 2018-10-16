package com.mygdx.model.tutorialStrategies.level_2;

import com.mygdx.model.Player;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy2B implements TutorialStrategy {
    private boolean justTurned;

    @Override
    public void algorithm(GameScreen gameScreen) {
        if(!gameScreen.getPlayer().isTop()) {
            gameScreen.getPlayer().move(32);
            justTurned = false;
        }
        else if(gameScreen.getPlayer().isTop() && gameScreen.getPlayer().getState() == Player.Player_State.STANDING) {
            if(justTurned) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameScreen.getPlayer().rotateRight();
            gameScreen.rotateCamera("right");
            justTurned = true;
        }
    }
}
