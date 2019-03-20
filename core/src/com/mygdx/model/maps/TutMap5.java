package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.model.tutorialStrategies.level_4.Strategy4B;
import com.mygdx.model.tutorialStrategies.level_5.Strategy5A;
import com.mygdx.model.tutorialStrategies.level_5.Strategy5B;
import com.mygdx.model.tutorialStrategies.level_5.Strategy5C;

import static com.mygdx.game.ResourcePaths.TUTMAP5;

public class TutMap5 extends AbstractMap {

    public TutMap5(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP5);
        Label textArea = new Label(TutorialTexts.LEVEL5, game.uiSkin);
        window = new Window("Algorithmus finalisieren", game.uiSkin);

        final Label algoText = new Label("S-1: Laufe einen Schritt geradeaus.\n         Wenn links frei und Kompass < 0, dann weiter mit S-2,\n         wenn der Weg nach vorne nicht frei ist, dann weiter mit S-3 ", game.uiSkin);
        final Label algoText2 = new Label("S-2: Nach links drehen, dann S-1 ", game.uiSkin);
        final Label algoText3 = new Label("S-3: Nach rechts drehen, dann S-1 ", game.uiSkin);

        CheckBox checkBoxA = new CheckBox("Maximal genauso oft wie die Anzahl der Rechtsdrehungen", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("Weniger als die Anzahl der Rechtsdrehungen", game.uiSkin);
        CheckBox checkBoxC = new CheckBox("Öfter als die Anzahl der Rechtsdrehungen", game.uiSkin);

        checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy5A());
                algoText.setText("S-1: Laufe einen Schritt geradeaus. \n         Wenn links frei und Kompass < 0, dann weiter mit S-2,\n         wenn der Weg nach vorne nicht frei ist, dann weiter mit S-3 ");
                algoText2.setText("S-2: Nach links drehen (Kompass um 1 erhöhen) und weiter mit S-1 ");
                algoText3.setText("S-3: Nach rechts drehen (Kompass um 1 verringern) und weiter mit S-1 ");
                algoWindow.pack();
            }
        });

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy5B());
                algoText.setText("S-1: Laufe einen Schritt geradeaus. Wenn Weg nach vorne nicht frei, dann S-3 ");
                algoText2.setText("S-2: Nach links drehen (Kompass um 1 erhöhen) und weiter mit S-1 ");
                algoText3.setText("S-3: Nach rechts drehen (Kompass um 1 verringern) und weiter mit S-1 ");
                algoWindow.pack();
            }
        });

        checkBoxC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy5C());
                algoText.setText("S-1: Wenn links frei, dann S-2, wenn nicht, dann S-3 ");
                algoText2.setText("S-2: Nach links drehen und weiter mit S-1 ");
                algoText3.setText("S-3: Geradeaus laufen, wenn Weg versperrt, dann S-2 ");
                algoWindow.pack();
            }
        });

        ButtonGroup<CheckBox> buttonGroup = new ButtonGroup<>(checkBoxA, checkBoxB, checkBoxC);
        window.add(textArea);
        window.row();
        window.add(checkBoxA);
        window.row();
        window.add(checkBoxB);
        window.row();
        window.add(checkBoxC);
        window.pack();
        window.setPosition(64, Gdx.graphics.getHeight());

        algoWindow = new Window(TutorialTexts.ALGO_WINDOW_HEADLINE, game.uiSkin);
        algoWindow.add(algoText).align(Align.left);
        algoWindow.row();
        algoWindow.add(algoText2).align(Align.left);
        algoWindow.row();
        algoWindow.add(algoText3).align(Align.left);
        algoWindow.pack();
        algoWindow.setPosition(Gdx.graphics.getWidth() / algoWindow.getWidth() + 64, Gdx.graphics.getHeight()/2f);
        //Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight()/2f - algoWindow.getHeight()
    }

    @Override
    public int getStartX() {
        return 3*32;
    }

    @Override
    public int getStartY() {
        return 32;
    }

    @Override
    public TutorialStrategy getStartStrategy() {
        return new Strategy4B();
    }

    @Override
    public Window getWindow() {
        return window;
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
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void triggerGoalAchievedMethod() {
        super.goalAchieved(nextMap, false);
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial();
    }
}
