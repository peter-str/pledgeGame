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
import com.mygdx.model.tutorialStrategies.level_3.Strategy3A;
import com.mygdx.model.tutorialStrategies.level_4.Strategy4A;
import com.mygdx.model.tutorialStrategies.level_4.Strategy4B;

import static com.mygdx.game.ResourcePaths.TUTMAP4;

public class TutMap4 extends AbstractMap {

    public TutMap4(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP4);
        Label textArea = new Label(TutorialTexts.LEVEL4, game.uiSkin);
        window = new Window("Algorithmus anpassen", game.uiSkin);

        final Label algoText = new Label("S-1: Laufe einen Schritt geradeaus ", game.uiSkin);
        final Label algoText2 = new Label("S-2: Wand vor dir, aber links keine: Drehe dich nach links ", game.uiSkin);
        final Label algoText3 = new Label("S-3: Wand vor dir: Drehe dich nach rechts ", game.uiSkin);

        final String stepA = "";
        final String stepB = "";
        final String stepC = "";
        final Label finalStep = new Label("", game.uiSkin);

        CheckBox checkBoxA = new CheckBox("Das Drehen nach rechts muss angepasst werden. ", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("Nach jedem Schritt überprüfen, ob links keine Wand ist. ", game.uiSkin);


        checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy4A());
                algoText.setText("S-1: Laufe einen Schritt geradeaus. \n         Wenn links frei, dann S-2, wenn vorne versperrt, dann S-3 ");
                algoText2.setText("S-2: Nach links drehen, dann S-1 ");
                algoText3.setText("S-3: Nach links drehen, dann S-1 ");
                algoWindow.pack();
            }
        });

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy4B());
                algoText.setText("S-1: Laufe einen Schritt geradeaus. \n         Wenn links frei, dann S-2, wenn vorne versperrt, dann S-3 ");
                algoText2.setText("S-2: Nach links drehen, dann S-1 ");
                algoText3.setText("S-3: Nach rechts drehen, dann S-1 ");
                algoWindow.pack();
            }
        });

        ButtonGroup<CheckBox> buttonGroup = new ButtonGroup<>(checkBoxA, checkBoxB);
        window.add(textArea);
        window.row();
        window.add(checkBoxA);
        window.row();
        window.add(checkBoxB);
        window.pack();
        window.setPosition(64, Gdx.graphics.getHeight());

        algoWindow = new Window(TutorialTexts.ALGO_WINDOW_HEADLINE, game.uiSkin);
        algoWindow.add(algoText).align(Align.left);
        algoWindow.row();
        algoWindow.add(algoText2).align(Align.left);
        algoWindow.row();
        algoWindow.add(algoText3).align(Align.left);
        algoWindow.pack();
        algoWindow.setPosition(Gdx.graphics.getWidth() / algoWindow.getWidth() + 64, Gdx.graphics.getHeight() / 2f);
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
    public TutorialStrategy getStartStrategy() {
        return new Strategy3A();
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
        return true;
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
        return new DifficultyTutorial();
    }
}
