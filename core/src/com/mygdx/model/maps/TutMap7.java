package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyMedium;
import com.mygdx.model.difficulties.DifficultyTutorial;


import static com.mygdx.game.ResourcePaths.TUTMAP7;

public class TutMap7 extends AbstractMap {
    private Label algoText;
    private Label algoText2;
    private Label algoText3;
    private boolean justTurned = false;
    private boolean go = false;
    private int flag;
    private int revC;
    private int flag2;

    public TutMap7(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP7);
        //Label textArea = new Label(TutorialTexts.LEVEL6, game.uiSkin);
        //window = new Window("Algorithmus fertiggestellt", game.uiSkin);

        algoText = new Label("S-1: Laufe geradeaus. \nWenn links frei und Kompass < 0, dann S-2, wenn Weg versperrt, dann S-3 ", game.uiSkin);
        algoText2 = new Label("S-2: Nach links drehen und Kompass + 1 und weiter mit S-1 ", game.uiSkin);
        algoText3 = new Label("S-3: Nach rechts drehen und Kompass - 1 und weiter mit S-1 ", game.uiSkin);
        algoText.setFontScale(0.9f);
        algoText2.setFontScale(0.9f);
        algoText3.setFontScale(0.9f);


        //window.add(textArea);
        //window.pack();
        //window.setPosition(64, Gdx.graphics.getHeight());

        algoWindow = new Window(TutorialTexts.ALGO_WINDOW_HEADLINE, game.uiSkin);
        algoWindow.add(algoText);
        algoWindow.row();
        algoWindow.add(algoText2);
        algoWindow.row();
        algoWindow.add(algoText3);
        algoWindow.pack();
        algoWindow.setPosition(100, 0);
    }

    @Override
    public int getStartX() {
        return 32 * 20;
    }

    @Override
    public int getStartY() {
        return 32 * 19;
    }

    @Override
    public Window getWindow() {
        return null;
    }

    @Override
    public Window getAlgoWindow() {
        return algoWindow;
    }

    @Override
    public boolean getTutorialFlagOfNextLevel() {
        return true;
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, false);
    }

    @Override
    public void showInstructions(int x, int y) {
        if (x == 24 && y == 24 && flag == 0) {
            flag++;
            gameScreenObserver.getPlayerController().keyUp(Input.Keys.UP);
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Du musst umkehren!", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Du weisst zwar, dass du nach links weiter musst, aber in einem dunklen \n" +
                    "Labyrinth wirst du es nicht zwangslaeufig wissen. \n" +
                    "Merke: Der Kompass darf nie einen Wert ueber 0 erreichen! \n" +
                    "Deshalb musst du hier wieder zurueck laufen. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());

        }
    }

    @Override
    public void showInstructions(int revCounter, boolean up, boolean right, boolean bottom, boolean left) {

        if(revCounter == 0 && !up) {
            algoText.setColor(Color.RED);
            algoText2.setColor(Color.WHITE);
            algoText3.setColor(Color.WHITE);
        } else if(revCounter < 0 && !left) {
            if(flag2 == 0)
                test(revCounter);
            algoText.setColor(Color.WHITE);
            algoText2.setColor(Color.RED);
            algoText3.setColor(Color.WHITE);
            if(revCounter-1 == revC) {
                algoText.setColor(Color.RED);
                algoText2.setColor(Color.WHITE);
                algoText3.setColor(Color.WHITE);
            }
        } else if((revCounter < 0 && left && up) || (revCounter == 0 & up)) {
            algoText.setColor(Color.WHITE);
            algoText2.setColor(Color.WHITE);
            algoText3.setColor(Color.RED);
        } else if(revCounter < 0 && left && !up) {
            algoText.setColor(Color.RED);
            algoText2.setColor(Color.WHITE);
            algoText3.setColor(Color.WHITE);
            flag2 = 0;
        }
    }

    public void test(int counter) {
        flag2++;
        revC = counter;
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial();
    }
}
