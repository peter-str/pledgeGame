package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.*;
import com.mygdx.screens.MainMenuScreen;

import static com.mygdx.game.ResourcePaths.INTRODUCTIONMAP3;

public class IntroductionMap3 extends AbstractMap {
    private int flag = 0;
    private Label algoText;
    private Label algoText2;
    private Label algoText3;
    private boolean showAlgoWindow;

    public IntroductionMap3(final PledgeGame game, MapEnum nextMap, boolean showAlgoWindow) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(INTRODUCTIONMAP3);
        this.showAlgoWindow = showAlgoWindow;
        if(showAlgoWindow) {
            algoText = new Label("S-1: Laufe geradeaus. \nWenn links frei und Kompass < 0, dann S-2, wenn Weg versperrt, dann S-3 ", game.uiSkin);
            algoText2 = new Label("S-2: Nach links drehen und Kompass + 1 und weiter mit S-1 ", game.uiSkin);
            algoText3 = new Label("S-3: Nach rechts drehen und Kompass - 1 und weiter mit S-1 ", game.uiSkin);
            algoText.setFontScale(0.9f);
            algoText2.setFontScale(0.9f);
            algoText3.setFontScale(0.9f);

            algoWindow = new Window(TutorialTexts.ALGO_WINDOW_HEADLINE, game.uiSkin);
            algoWindow.add(algoText);
            algoWindow.row();
            algoWindow.add(algoText2);
            algoWindow.row();
            algoWindow.add(algoText3);
            algoWindow.pack();
            algoWindow.setPosition(100, 0);
        }

        if(!showAlgoWindow) {

        }
    }

    @Override
    public int getStartX() {
        return 96;
    }

    @Override
    public int getStartY() {
        return 32;
    }

    @Override
    public Window getAlgoWindow() {
        return algoWindow;
    }

    @Override
    public boolean getTutorialFlagOfNextLevel() {
        return false;
    }

    @Override
    public void showInstructions(int x, int y) {
        if(flag == 0 && !showAlgoWindow) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Verlaufen!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Nach einiger Zeit hast du dich in der dunklen Hoehle verlaufen. \n" +
                    "Du kannst dennoch probieren, den Ausgang zu finden. \n" +
                    "Oder druecke F1, um zu erfahren, wie du hier dennoch entkommen kannst. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }

        if(flag == 0 && showAlgoWindow) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Finde den Ausgang", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Jetzt bist du wieder dort, wo du dich am Anfang verlaufen hast. \n" +
                    "Schaffst du es nun mithilfe des Algorithmus nach draussen? ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());
        }
    }

    @Override
    public void showWinningMessage() {
        if (finished) {
            finished = false;
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Ziel erreicht", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    if (obj.equals("menu")) {
                        game.setScreen(new MainMenuScreen(game, false));
                        dispose();
                    }
                }
            };
            dialog.text("Super, du hast den Ausgang aus dem dunklen Labyrinth gefunden! \n" +
                    "Du bist nun ein Experte im Umgang mit dem Pledge-Algorithmus! " +
                    "\nIm Endlosmodus kannst du weitere Level ausprobieren. ");
            dialog.button("Hauptmenue", "menu");
            dialog.show(gameScreenObserver.getStage());
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

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, true);
    }
}

