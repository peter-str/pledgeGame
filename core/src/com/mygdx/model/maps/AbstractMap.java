package com.mygdx.model.maps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.model.difficulties.Difficulty;
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


    public AbstractMap(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        this.game = game;
        this.gameScreen = gameScreen;
        this.nextMap = nextMap;
    }

    public void blueDots(float x, float y){    }
    public void message(){}
    public abstract int getStartX();
    public abstract int getStartY();
    public void setStartX(int x){};
    public void setStartY(int x){};
    public void showInstructions(){}
    public ClickListener getPlayButton(){return null;}
    public Window getWindow(){return null;}
    public abstract boolean getTutorialFlag();

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public abstract MapEnum getNextMap();

    public void zielErreicht(final MapEnum nextMap, final boolean tutorialFlag) {
        if (finished) {
            finished = false;
            Gdx.input.setInputProcessor(gameScreen.stage);
            dialog = new Dialog("Ziel erreicht", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    if (obj.equals("menu")) {
                        Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
                        dispose();
                    } else if (obj.equals("level") && !tutorialFlag) {
                        Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, nextMap, false));
                        dispose();
                    } else if (obj.equals("level") && tutorialFlag) {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, nextMap, true));
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
