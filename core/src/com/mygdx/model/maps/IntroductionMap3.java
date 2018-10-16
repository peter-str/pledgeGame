package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyEasy;
import com.mygdx.model.difficulties.DifficultyExpert;
import com.mygdx.screens.GameScreen;

public class IntroductionMap3 extends AbstractMap {
    private int flag = 0;

    public IntroductionMap3(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/Introduction3.tmx");
    }

    @Override
    public int getStartX() {
        return 32;
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
        super.zielErreicht(nextMap, true);
    }

    @Override
    public void showInstructions(int x, int y) {
        if(flag == 0) {
            flag++;
            gameScreen.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Verlaufen!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                }
            };
            dialog.text("Nach einiger Zeit hast du dich in der dunklen Hoehle verlaufen. \n" +
                    "Du kannst es dennoch probieren, den Ausgang zu finden. ");
            dialog.button("Okay");
            dialog.show(gameScreen.stage);
        }
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyExpert();
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }
}

