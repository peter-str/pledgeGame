package com.mygdx.model.maps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.TutorialTexts;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyTutorial;
import com.mygdx.screens.GameScreen;
import com.mygdx.game.PledgeGame;
import com.mygdx.enums.MapEnum;

public class TutMap1 extends AbstractMap {

    private Label textArea;
    private Window window;

    public TutMap1(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/TutorialMap1.tmx");
        spriteBatch2 = new SpriteBatch();
        textArea = new Label(TutorialTexts.LEVEL1, game.uiSkin);
        window = new Window("Anfang des Algorithmus", game.uiSkin);
        window.add(textArea);
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
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if(!gameScreen.getPlayer().isTop()) {
                    gameScreen.getPlayer().move(32);
                }
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
