package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyEasy;
import com.mygdx.model.difficulties.DifficultyTutorial;
import com.mygdx.screens.GameScreen;

public class IntroductionMap2 extends AbstractMap {
    private int flag = 0;

    public IntroductionMap2(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/Introduction2.tmx");
    }

    @Override
    public int getStartX() {
        return 32 * 15;
    }

    @Override
    public int getStartY() {
        return 32;
    }

    @Override
    public boolean getTutorialFlag() {
        return false;
    }

    @Override
    public void zielErreicht(MapEnum nextMap, boolean tutorialFlag) {
        super.zielErreicht(nextMap, false);
    }

    @Override
    public void showInstructions(int x, int y) {
        /*if((x == 14*32 || x == 17*32) && y == 9*32 && flag == 0) {
            flag++;
            gameScreen.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Kompass gefunden!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                }
            };
            dialog.text("Du hast deinen Kompass auf dem Rand des Brunnens wiedergefunden. \n" +
                    "Prima! ");
            dialog.button("Okay");
            dialog.show(gameScreen.stage);
        }*/
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial();
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }
}

