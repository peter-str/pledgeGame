package com.mygdx.model.tutorialStrategies.level_3;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.model.Player;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy3B implements TutorialStrategy {
    private boolean justTurnedLeft;
    private boolean justTurnedRight;

    @Override
    public void algorithm(final GameScreen gameScreen) {

        if (!gameScreen.getPlayer().isTop()) {
            if (!gameScreen.getPlayer().isLeft() && gameScreen.getPlayer().getState() == Player.Player_State.STANDING) {

                if (justTurnedLeft) {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gameScreen.getPlayer().rotateLeft();
                justTurnedLeft = true;
            }


            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameScreen.getPlayer().move(32);
                    justTurnedLeft = false;
                    justTurnedRight = false;
                }
            }, 0.2f);


        } else if (gameScreen.getPlayer().isTop() && gameScreen.getPlayer().getState() == Player.Player_State.STANDING) {
            if (justTurnedRight) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            gameScreen.getPlayer().rotateRight();
            justTurnedRight = true;
        }
    }
}
