package com.mygdx.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.screens.GameScreen;
import com.mygdx.game.PledgeGame;
import com.mygdx.screens.MainMenuScreen;
import com.mygdx.enums.MapEnum;

public abstract class AbstractMap {

    protected final PledgeGame game;
    protected GameScreen gameScreen;
    protected MapEnum nextMap;
    protected TiledMap tiledMap;
    protected SpriteBatch spriteBatch2;
    protected int dotNr = 0;
    protected Label controls;
    protected Dialog dialog;
    protected boolean finished = true;
    protected boolean mapRendererBool = true;


    public AbstractMap(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        this.game = game;
        this.gameScreen = gameScreen;
        this.nextMap = nextMap;
    }

    public void blueDots(float x, float y){}
    public void message(){}
    public abstract int getStartX();
    public abstract int getStartY();
    public abstract void showControls();
    public void showInstructions(){}

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public abstract MapEnum getNextMap();
    public boolean getMapRendererBool() {
        return mapRendererBool;
    }
    public void setMapRendererBool() {
        if(!mapRendererBool)
            mapRendererBool = true;
        else
            mapRendererBool = false;
    }

    public void zielErreicht(final MapEnum nextMap) {
        if (finished) {
            finished = false;
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Ziel erreicht", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    if (obj.equals("menu")) {
                        Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
                        dispose();
                    } else if (obj.equals("level")) {
                        Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, nextMap));
                        dispose();
                    }
                }
            };
            dialog.text("Super, du hast es geschafft! " +
                    "\nZurueck zum Hauptmenue oder naechstes Level? ");
            dialog.button("Hauptmenue", "menu");
            dialog.button("Naechstes Level", "level");
            dialog.show(gameScreen.stage);
        }
    }

    public abstract Difficulty getDifficulty();

    public void dispose() {
        tiledMap.dispose();
    }


}
