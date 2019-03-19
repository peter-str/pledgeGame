package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.model.tutorialStrategies.level_2.Strategy2B;
import com.mygdx.model.tutorialStrategies.level_3.Strategy3A;
import com.mygdx.model.tutorialStrategies.level_3.Strategy3B;

import static com.mygdx.game.ResourcePaths.TUTMAP3;

public class TutMap3 extends AbstractMap {

    public TutMap3(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP3);
        Label textArea = new Label(TutorialTexts.LEVEL3, game.uiSkin);
        window = new Window("Die dritte Regel", game.uiSkin);
        //window.setColor(Color.WHITE);

        final String stepA = "S-2: Wand vor dir, aber links keine: Drehe dich nach links. ";
        final String stepB = "S-2: Wand vor dir, aber links keine: Drehe dich nach rechts. ";
        final Label finalStep = new Label("2: -", game.uiSkin);

        CheckBox checkBoxA = new CheckBox("Wenn eine Wand vor dir ist, aber links keine, drehe dich nach links", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("Wenn eine Wand vor dir ist, aber links keine, drehe dich nach rechts", game.uiSkin);

        checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy3A());
                finalStep.setText(stepA);
                algoWindow.pack();
            }
        });

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy3B());
                finalStep.setText(stepB);
                algoWindow.pack();
            }
        });

        ButtonGroup<CheckBox> buttonGroup = new ButtonGroup<>(checkBoxA, checkBoxB);
        window.add(textArea);
        window.row();
        window.add(checkBoxA);
        window.row();
        window.add(checkBoxB);
        window.row();
        window.pack();
        window.setPosition(64, Gdx.graphics.getHeight());

        algoWindow = new Window(TutorialTexts.ALGO_WINDOW_HEADLINE, game.uiSkin);
        Label algoText = new Label("S-1: Laufe einen Schritt geradeaus", game.uiSkin);
        Label algoText2 = new Label("S-3: Wand vor dir: Drehe dich nach rechts ", game.uiSkin);
        algoWindow.add(algoText);
        algoWindow.row();
        algoWindow.add(finalStep);
        algoWindow.row();
        algoWindow.add(algoText2);
        algoWindow.pack();
        algoWindow.setPosition(Gdx.graphics.getWidth() / algoWindow.getWidth() + 64, Gdx.graphics.getHeight()/2f);
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
        return new Strategy2B();
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
