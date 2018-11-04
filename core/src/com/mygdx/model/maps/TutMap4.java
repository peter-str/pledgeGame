package com.mygdx.model.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.model.tutorialStrategies.level_3.Strategy3A;
import com.mygdx.model.tutorialStrategies.level_4.Strategy4B;
import com.mygdx.model.tutorialStrategies.level_4.Strategy4C;

import static com.mygdx.game.ResourcePaths.TUTMAP4;

public class TutMap4 extends AbstractMap{

    public TutMap4(final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load(TUTMAP4);
        Label textArea = new Label(TutorialTexts.LEVEL4, game.uiSkin);
        window = new Window("Algorithmus anpassen", game.uiSkin);

        final Label algoText = new Label("S-1: Laufe geradeaus ", game.uiSkin);
        final Label algoText2 = new Label("S-2: Wand vor dir, aber links keine: Drehe dich nach links ", game.uiSkin);
        final Label algoText3 = new Label("S-3: Wand vor dir: Drehe dich nach rechts ", game.uiSkin);
        algoText.setFontScale(0.9f);
        algoText2.setFontScale(0.9f);
        algoText3.setFontScale(0.9f);

        final String stepA = "";
        final String stepB = "";
        final String stepC = "";
        final Label finalStep = new Label("", game.uiSkin);

        //CheckBox checkBoxA = new CheckBox("BLA BLA keine Ahnung ", game.uiSkin);
        CheckBox checkBoxB = new CheckBox("Das Drehen nach Rechts muss angepasst werden. ", game.uiSkin);
        CheckBox checkBoxC = new CheckBox("Nach jedem Schritt ueberpruefen, ob links keine Wand ist. ", game.uiSkin);

        /*checkBoxA.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameScreen.setButtonStrategy(new Strategy4A());
            }
        });*/

        checkBoxB.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy4B());
            }
        });

        checkBoxC.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                notifyObserver(new Strategy4C());
                algoText.setText("S-1: Laufe geradeaus, wenn links frei, dann 2, wenn vorne versperrt, dann 3 ");
                algoText2.setText("S-2: Nach links drehen, dann 1 ");
                algoText3.setText("S-3: Nach rechts drehen, dann 1 ");
                algoWindow.pack();
            }
        });

        ButtonGroup<CheckBox> buttonGroup = new ButtonGroup<>(checkBoxB, checkBoxC);
        window.add(textArea);
        window.row();
        //window.add(checkBoxA);
        //window.row();
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
        //Gdx.graphics.getWidth() / algoWindow.getWidth() + 64, Gdx.graphics.getHeight()/2f
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
    public boolean getTutorialFlag() {
        return true;
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void goalAchieved(MapEnum nextMap, boolean tutorialFlag) {
        super.goalAchieved(nextMap, true);
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyTutorial(false);
    }
}
