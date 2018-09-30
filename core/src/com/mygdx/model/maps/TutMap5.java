package com.mygdx.model.maps;

import com.badlogic.gdx.Game;
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
import com.mygdx.model.tutorialStrategies.level_4.Strategy4C;
import com.mygdx.model.tutorialStrategies.level_5.Strategy5A;
import com.mygdx.model.tutorialStrategies.level_5.Strategy5B;
import com.mygdx.model.tutorialStrategies.level_5.Strategy5C;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;

import static com.mygdx.game.ResourcePaths.TUTMAP5;

public class TutMap5 extends AbstractMap{

    public TutMap5(final PledgeGame game, final GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP5);
        Label textArea = new Label(TutorialTexts.LEVEL5, game.uiSkin);
        window = new Window("Algorithmus finalisieren", game.uiSkin);

        final Label algoText = new Label("1: Laufe geradeaus ", game.uiSkin);
        final Label algoText2 = new Label("2: Wand vor dir, aber links keine: Drehe dich nach links ", game.uiSkin);
        final Label algoText3 = new Label("3: Wand vor dir: Drehe dich nach rechts ", game.uiSkin);
        algoText.setFontScale(0.9f);
        algoText2.setFontScale(0.9f);
        algoText3.setFontScale(0.9f);

        CheckBox checkBoxA = new CheckBox("Genauso oft wie die Anzahl der Rechtsdrehungen", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("Weniger als die Anzahl der Rechtsdrehungen", game.uiSkin);
        CheckBox checkBoxC = new CheckBox("Oefter als die Anzahl der Rechtsdrehungen", game.uiSkin);

        checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy5A());
                algoText2.setText("2: Wand vor dir, aber links keine und Zaehler < 0:\nDrehe dich nach links und Zaehler + 1 ");
                algoText3.setText("3: Wand vor dir: Drehe dich nach rechts und Zaehler - 1 ");
                algoWindow.pack();
            }
        });

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy5B());
            }
        });

        checkBoxC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy5C());
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
        algoWindow.add(algoText);
        algoWindow.row();
        algoWindow.add(algoText2);
        algoWindow.row();
        algoWindow.add(algoText3);
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
        return new Strategy4C();
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
        ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial();
    }
}
