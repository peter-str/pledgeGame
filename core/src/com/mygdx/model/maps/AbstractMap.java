package com.mygdx.model.maps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;
import com.mygdx.screens.GameScreen;
import com.mygdx.game.PledgeGame;
import com.mygdx.screens.GameScreenInterface;
import com.mygdx.screens.MainMenuScreen;
import com.mygdx.enums.MapEnum;

public abstract class AbstractMap {

    protected final PledgeGame game;
    protected MapEnum nextMap;
    protected TiledMap tiledMap;
    protected Dialog dialog;
    protected boolean finished = true;
    protected Window window;
    protected Window algoWindow;
    protected GameScreenInterface gameScreenObserver;


    public AbstractMap(final PledgeGame game, MapEnum nextMap) {
        this.game = game;
        this.nextMap = nextMap;
    }

    public void register(GameScreenInterface newObserver) {
        gameScreenObserver = newObserver;
    }

    protected void notifyObserver(TutorialStrategy strategy) {
        gameScreenObserver.updateStrategy(strategy);
    }

    public void message(){}
    public abstract int getStartX();
    public abstract int getStartY();
    public void setStartX(int x){};
    public void setStartY(int x){};
    public void showInstructions(){}
    public void showInstructions(int x, int y){}
    public Window getWindow(){return null;}
    public Window getAlgoWindow(){return null;}
    public abstract boolean getTutorialFlag();

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public abstract MapEnum getNextMap();

    public void goalAchieved(final MapEnum nextMap, final boolean tutorialFlag) {
        if (finished) {
            finished = false;
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Ziel erreicht", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    if (obj.equals("menu")) {
                        Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
                        dispose();
                    } else if (obj.equals("level") && !tutorialFlag) {
                        Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
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
            dialog.show(gameScreenObserver.getStage());
        }
    }

    public TutorialStrategy getStartStrategy() {return null;}

    public abstract Difficulty getDifficulty();

    public void dispose() {
        tiledMap.dispose();
    }


}
