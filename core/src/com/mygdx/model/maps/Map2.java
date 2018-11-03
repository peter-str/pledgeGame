package com.mygdx.model.maps;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.difficulties.Difficulty;
import com.mygdx.model.difficulties.DifficultyHigh;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;

import java.util.Timer;
import java.util.TimerTask;

public class Map2 extends AbstractMap {

    public int messageCounter = 0;

    public Map2 (final PledgeGame game, MapEnum nextMap) {
        super(game, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/map5.tmx");
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    public void message() {
        if(messageCounter == 0) {
            messageCounter++;
            Gdx.input.setInputProcessor(gameScreenObserver.getStage());
            dialog = new Dialog("Kommst du wieder raus?", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                }
            };
            dialog.text("Du hast dich in der dunklen Hoehle verlaufen. ");
            dialog.button("Okay");
            dialog.show(gameScreenObserver.getStage());

        }

        if(messageCounter == 1) {
            messageCounter++;
            final Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    Gdx.input.setInputProcessor(gameScreenObserver.getStage());
                    dialog = new Dialog("Zum Tutorial?", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            if(obj.equals("okay")) {
                                Gdx.input.setInputProcessor(gameScreenObserver.getPlayerController());
                                messageCounter = 1;
                            } else if(obj.equals("tutorial")) {
                                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, nextMap, false));
                            } else if (obj.equals("menue")) {
                                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));
                            }
                        }
                    };
                    dialog.text("Moechtest du noch weiter probieren? " +
                            "\nOder moechtest du das Tutorial starten? " +
                            "\nDieses Fenster erscheint in 30 Sekunden erneut. ");
                    dialog.button("Weiter", "okay");
                    dialog.button("Tutorial", "tutorial");
                    dialog.button("Menue", "menue");
                    dialog.show(gameScreenObserver.getStage());
                    t.cancel();
                }
            }, 10000);
        }
    }

    @Override
    public void goalAchieved(MapEnum nextMap, boolean tutorialFlag) {
        super.goalAchieved(nextMap, false);
    }

    @Override
    public Difficulty getDifficulty() {
        return new DifficultyHigh(false);
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
    public boolean getTutorialFlag() {
        return false;
    }
}
