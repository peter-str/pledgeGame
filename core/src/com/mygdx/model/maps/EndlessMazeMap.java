package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Align;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.screens.MainMenuScreen;

public class EndlessMazeMap extends AbstractMap {
    private Label algoText;
    private Label algoText2;
    private Label algoText3;

    public EndlessMazeMap(final PledgeGame game, MapEnum nextMap, TiledMap map) {
        super(game, nextMap);
        tiledMap = map;
        algoText = new Label("S-1: Laufe einen Schritt geradeaus (in Blickrichtung). \n         Wenn links frei und Kompass < 0, dann weiter mit S-2,\n         wenn der Weg nach vorne nicht frei ist, dann weiter mit S-3 ", game.uiSkin);
        algoText2 = new Label("S-2: Nach links drehen (Kompass um 1 erhöhen) und weiter mit S-1 ", game.uiSkin);
        algoText3 = new Label("S-3: Nach rechts drehen (Kompass um 1 verringern) und weiter mit S-1 ", game.uiSkin);

        algoWindow = new Window(TutorialTexts.ALGO_WINDOW_HEADLINE, game.uiSkin);
        algoWindow.add(algoText).align(Align.left);
        algoWindow.row();
        algoWindow.add(algoText2).align(Align.left);
        algoWindow.row();
        algoWindow.add(algoText3).align(Align.left);
        algoWindow.pack();
        algoWindow.setPosition(100, 0);
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
            dialog.text("Super, du hast den Ausgang aus dem Labyrinth gefunden! \n");
            dialog.button("Hauptmenü", "menu");
            dialog.show(gameScreenObserver.getStage());
        }
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
    public boolean getTutorialFlagOfNextLevel() {
        return false;
    }

    @Override
    public Window getAlgoWindow() {
        return algoWindow;
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, true);
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }
}
