package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;

import static com.mygdx.game.ResourcePaths.INTRODUCTIONMAP1;

public class IntroductionMap1 extends AbstractMap {
    private int flag = 0;

    public IntroductionMap1(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(INTRODUCTIONMAP1);
    }

    @Override
    public int getStartX() {
        return 32 * 12;
    }

    @Override
    public int getStartY() {
        return 32 * 13;
    }

    @Override
    public boolean getTutorialFlagOfNextLevel() {
        return false;
    }

    @Override
    public void showInstructions(int x, int y) {
        if(flag == 0) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Auf ins Abenteuer!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Du moechtest dich auf eine Expedition begeben. \n" +
                    "Dummerweise hast du deinen Kompass verloren... \n" +
                    "Du erinnerst dich, dass du ihn das letzte Mal am Stadtbrunnen hattest. \n" +
                    "Du beschliesst zum Brunnen zu gehen.(Benutze die Pfeiltasten) ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }

        if(x == 15*32 && y == 21*32 && flag == 1) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Beruehrungsanzeige", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Beobachte die Anzeige im unteren linken Rand. \n" +
                    "Diese zeigt dir an, ob vor, hinter, links oder rechts von dir ein Hindernis ist. \n" +
                    "Das wirst du spaeter noch benutzen muessen. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }

        if((x == 23*32 || x == 26*32) && y == 18*32 && flag == 2) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Kompass gefunden!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Du hast deinen Kompass auf dem Rand des Brunnens wiedergefunden. \n" +
                    "Prima! \n" +
                    "Nun kannst du dich auf die Expedition begeben. \n" +
                    "Verlasse die Stadt. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial();
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, false);
    }
}
