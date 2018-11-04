package com.mygdx.model.tutorialStrategies.level_4;

import com.badlogic.gdx.utils.Timer;
import com.mygdx.model.Player;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy4B implements TutorialStrategy {

    private boolean turnedLeft = false;
    private boolean firstStep = false;

    @Override
    public void algorithm(GameScreen gameScreen) {

        if(!firstStep && !gameScreen.getPlayer().isTop()) {
            gameScreen.getPlayer().move(32);
            firstStep = true;
        }
        else {
            firstStep = true;
        }

        if(!gameScreen.getPlayer().isTop() && gameScreen.getPlayer().isLeft()) {
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    gameScreen.getPlayer().move(32);
                }
            }, 0.2f);
        } else if(!gameScreen.getPlayer().isLeft() && isCurrentlyStanding(gameScreen)) {
            turnLeft(gameScreen);
        } else if(isCurrentlyStanding(gameScreen)) {
            turnRight(gameScreen);
        }
    }

    private void turnLeft(GameScreen gameScreen) {
        if(!turnedLeft) {
            gameScreen.getPlayer().rotateLeft();
            gameScreen.rotateCamera("left");
        }
        if(turnedLeft) {
            gameScreen.getPlayer().move(32);
        }
        turnedLeft = !turnedLeft;
    }

    private void turnRight(GameScreen gameScreen) {
        gameScreen.getPlayer().rotateRight();
        gameScreen.rotateCamera("right");
    }

    private boolean isCurrentlyStanding(GameScreen gameScreen) {
        return gameScreen.getPlayer().getState() == Player.Player_State.STANDING;
    }
}


/*if(!gameScreen.getPlayer().isTop() && gameScreen.getPlayer().isLeft()) {
        gameScreen.getPlayer().move(32);
        walked = false;
        }
        else if(!gameScreen.getPlayer().isTop() && !gameScreen.getPlayer().isLeft() && turned) {
        gameScreen.getPlayer().move(32);
        turned = false;
        walked = true;
        }
        else if(!gameScreen.getPlayer().isLeft() && !turned && !walked) {
        gameScreen.getPlayer().rotateLeft();
        gameScreen.rotateCamera("left");
        turned = true;
        }
        else if(gameScreen.getPlayer().isTop()) {
        gameScreen.getPlayer().rotateRight();
        gameScreen.rotateCamera("right");
        }
*/