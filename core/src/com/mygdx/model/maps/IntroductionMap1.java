package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;
import com.mygdx.screens.GameScreen;

public class IntroductionMap1 extends AbstractMap {
    private int flag = 0;

    public IntroductionMap1(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/Introduction1.tmx");
    }

    @Override
    public int getStartX() {
        return 32 * 3;
    }

    @Override
    public int getStartY() {
        return 32 * 4;
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
        if(flag == 0) {
            flag++;
            gameScreen.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Auf ins Abenteuer!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                }
            };
            dialog.text("Du moechtest dich auf eine Expedition begeben. \n" +
                    "Dummerweise hast du deinen Kompass verloren... \n" +
                    "Du erinnerst dich, dass du ihn das letzte Mal am Stadtbrunnen hattest. \n" +
                    "Du beschliesst zum Brunnen zu gehen. ");
            dialog.button("Okay");
            dialog.show(gameScreen.stage);
        }

        if(x == 6*32 && y == 12*32 && flag == 1) {
            flag++;
            gameScreen.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Beruehrungsanzeige", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                }
            };
            dialog.text("Beobachte die Anzeige im unteren linken Rand. \n" +
                    "Diese zeigt dir an, ob vor, hinter, links oder rechts von dir ein Hindernis ist. \n" +
                    "Das wirst du spaeter noch benutzen muessen. ");
            dialog.button("Okay");
            dialog.show(gameScreen.stage);
        }

        if((x == 14*32 || x == 17*32) && y == 9*32 && flag == 2) {
            flag++;
            gameScreen.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Kompass gefunden!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                }
            };
            dialog.text("Du hast deinen Kompass auf dem Rand des Brunnens wiedergefunden. \n" +
                    "Prima! \n" +
                    "Nun kannst du dich auf die Expedition begeben. \n" +
                    "Verlasse die Stadt. ");
            dialog.button("Okay");
            dialog.show(gameScreen.stage);
        }
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial(false);
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }
}
