package com.mygdx.model.tutorialStrategies.level_4;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.model.Player;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy4A implements TutorialStrategy {
    private boolean justTurned;

    @Override
    public void algorithm(GameScreen gameScreen) {
        if(!gameScreen.getPlayer().isTop()) {

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameScreen.getPlayer().move(32);
                    justTurned = false;
                }
            }, 0.2f);
        }
        else if(!gameScreen.getPlayer().isLeft() && gameScreen.getPlayer().getState() == Player.Player_State.STANDING) {
            if (justTurned) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameScreen.getPlayer().rotateLeft();
            gameScreen.rotateCamera("left");
            justTurned = true;
        }
        else if(gameScreen.getPlayer().isTop() && gameScreen.getPlayer().getState() == Player.Player_State.STANDING) {
            gameScreen.getPlayer().rotateLeft();
            gameScreen.rotateCamera("left");
            justTurned = true;
        }
    }
}
