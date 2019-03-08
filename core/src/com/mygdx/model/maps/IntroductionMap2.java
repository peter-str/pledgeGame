package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;

import static com.mygdx.game.ResourcePaths.INTRODUCTIONMAP2;

public class IntroductionMap2 extends AbstractMap {
    private int flag = 0;

    public IntroductionMap2(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(INTRODUCTIONMAP2);
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
    public boolean getTutorialFlagOfNextLevel() {
        return false;
    }

    @Override
    public void showInstructions(int x, int y) {
        if((x == 15 || x == 16) && y == 5 && flag == 0) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Strasse gesperrt!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Du kannst hier nicht weiter, finde einen anderen Weg! \n" +
                    "Moeglicherweise gibt es einen Weg durch den Wald... ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }

        if(x == 12 && (y == 5 || y == 6) && flag == 1) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Kompass beobachten!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Beobachte deinen Kompass (die Zahl), am linken Rand. \n" +
                    "Kannst du herausfinden, wie er funktioniert? \n" +
                    "Drehe dich nach links und rechts, um es herauszufinden. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }

        if(x == 2 && y == 16 && flag == 2) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Ein Hoehleneingang!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                    gameScreenObserver.getPlayerController().keyDown(Input.Keys.UP);
                }
            };
            dialog.text("Du entdeckst einen Hoehleneingang und entscheidest dich hineinzugehen. ");
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

