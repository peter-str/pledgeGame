package com.mygdx.model.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.mygdx.model.tutorialStrategies.level_2.Strategy2A;
import com.mygdx.model.tutorialStrategies.level_2.Strategy2B;
import com.mygdx.model.tutorialStrategies.level_2.Strategy2C;
import com.mygdx.model.tutorialStrategies.level_3.Strategy3A;
import com.mygdx.model.tutorialStrategies.level_3.Strategy3B;
import com.mygdx.model.tutorialStrategies.level_3.Strategy3C;
import com.mygdx.screens.GameScreen;

public class TutMap3 extends AbstractMap{
    private Label textArea;
    private Window window;
    private ButtonGroup<CheckBox> buttonGroup;
    private TutorialStrategy strategy;


    public TutMap3(final PledgeGame game, final GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/TutorialMap3.tmx");
        spriteBatch2 = new SpriteBatch();
        textArea = new Label(TutorialTexts.LEVEL1, game.uiSkin);
        window = new Window("Anfang des Algorithmus", game.uiSkin);
        CheckBox checkBoxA = new CheckBox("A", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("B", game.uiSkin);
        CheckBox checkBoxC = new CheckBox("C", game.uiSkin);

        checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonListener(getPlayButton());
            }
        });

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonListener(getPlayButton());
            }
        });

        checkBoxC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonListener(getPlayButton());
            }
        });

        buttonGroup = new ButtonGroup<>(checkBoxA, checkBoxB, checkBoxC);
        window.add(textArea);
        window.row();
        window.add(checkBoxA);
        window.row();
        window.add(checkBoxB);
        window.row();
        window.add(checkBoxC);
        window.pack();
        window.setPosition(64, 500);
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
    public ClickListener getPlayButton() {
        switch(buttonGroup.getCheckedIndex()) {
            default:
                strategy = new Strategy2B();
                break;
            case 0:
                strategy = new Strategy3A();
                break;
            case 1:
                strategy = new Strategy3B();
                break;
            case 2:
                strategy = new Strategy3C();
                break;
        }

        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                strategy.algorithm(gameScreen);
            }
        };
    }

    @Override
    public Window getWindow() {
        return window;
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
        return new DifficultyTutorial();
    }
}
