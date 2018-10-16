package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.model.tutorialStrategies.level_1.Strategy1A;
import com.mygdx.model.tutorialStrategies.level_2.Strategy2A;
import com.mygdx.model.tutorialStrategies.level_2.Strategy2B;
import com.mygdx.model.tutorialStrategies.level_2.Strategy2C;
import com.mygdx.screens.GameScreen;

import static com.mygdx.game.ResourcePaths.TUTMAP2;

public class TutMap2 extends AbstractMap {

    public TutMap2(final PledgeGame game, final GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP2);
        Label textArea = new Label(TutorialTexts.LEVEL2, game.uiSkin);
        window = new Window("Die zweite Regel", game.uiSkin);

        final String stepA = "2: Wenn eine Wand vor dir ist, drehe dich nach links ";
        final String stepB = "2: Wenn eine Wand vor dir ist, drehe dich nach rechts ";
        final String stepC = "2: Wenn eine Wand vor dir ist, drehe dich nach unten ";
        final Label finalStep = new Label("", game.uiSkin);
        finalStep.setFontScale(0.9f);

        CheckBox checkBoxA = new CheckBox("Links", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("Rechts", game.uiSkin);
        CheckBox checkBoxC = new CheckBox("Unten", game.uiSkin);

        checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy2A());
                finalStep.setText(stepA);
                algoWindow.pack();
            }
        });

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy2B());
                finalStep.setText(stepB);
                algoWindow.pack();
            }
        });

        checkBoxC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy2C());
                finalStep.setText(stepC);
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
        Label algoText = new Label("1: Laufe geradeaus", game.uiSkin);
        algoText.setFontScale(0.9f);
        algoWindow.add(algoText);
        algoWindow.row();
        algoWindow.add(finalStep);
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
        return new Strategy1A();
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
    public boolean getTutorialFlag() {
        return true;
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void zielErreicht(MapEnum nextMap, boolean tutorialFlag) {
        super.zielErreicht(nextMap, true);
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial(false);
    }
}
