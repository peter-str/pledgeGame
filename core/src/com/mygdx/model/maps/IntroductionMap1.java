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
        if (flag == 0) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Aufbruch!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Ein neuer Tag, eine neue Expedition. \n" +
                    "Dummerweise hast du deinen Kompass verloren, den du auf deinen Reisen \n" +
                    "unbedingt benötigst. Du kannst dich erinnern, ihn das letzte Mal am Stadtbrunnen \n" +
                    "benutzt zu haben. Möglicherweise liegt er noch dort... \n" +
                    "(Benutze die Pfeiltasten) ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }

        if (x == 15 && y == 21 && flag == 1) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Berührungsanzeige", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Beobachte die Anzeige am unteren linken Rand. \n" +
                    "Diese zeigt dir an, ob in deiner unmittelbaren Umgebung ein Hindernis ist. \n" +
                    "Wird ein rotes X angezeigt, so ist der Weg nicht frei. \n" +
                    "In deiner aktuellen Position (Blickrichtung) ist der Weg links und rechts nicht frei, \n" +
                    "nach vorne und hinten jedoch schon. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
            dialog.setPosition((Gdx.graphics.getWidth() - dialog.getWidth()) / 2f, Gdx.graphics.getHeight() - dialog.getHeight() * 2f);
        }

        if ((x == 23 || x == 26) && y == 18 && flag == 2) {
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
                    "Verlasse die Stadt über den Weg im Norden. ");
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
