package com.mygdx.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.PlayerController;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;

public interface GameScreenInterface {
    void updateStrategy(TutorialStrategy strategy);
    PlayerController getPlayerController();
    Stage getStage();
}
