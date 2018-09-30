package com.mygdx.model.tutorialStrategies.level_5;

import com.mygdx.model.Player;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;

public class Strategy5A implements TutorialStrategy {
    private boolean turnedLeft = false;
    private boolean firstStep = false;
    private int revCounter = 0;

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
            gameScreen.getPlayer().move(32);
        } else if(!gameScreen.getPlayer().isLeft() && isCurrentlyStanding(gameScreen) && revCounter < 0) {
            turnLeft(gameScreen);
        } else if(!gameScreen.getPlayer().isTop()) {
            gameScreen.getPlayer().move(32);
        } else if(isCurrentlyStanding(gameScreen)) {
            turnRight(gameScreen);
            revCounter--;
        }
    }

    private void turnLeft(GameScreen gameScreen) {
        if(!turnedLeft) {
            gameScreen.getPlayer().rotateLeft();
            gameScreen.rotateCamera("left");
        }
        if(turnedLeft) {
            gameScreen.getPlayer().move(32);
            revCounter++;
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
