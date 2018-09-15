package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.screens.GameScreen;

public class Map2 extends AbstractMap {

    public int messageCounter = 0;

    public Map2 (final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/map5.tmx");
        super.mapRendererBool = false;
    }

    @Override
    public void zielErreicht(MapEnum nextMap) {
        super.zielErreicht(nextMap);
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
    public void showControls() {

    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    public void message() {
        if(messageCounter == 0) {
            messageCounter = 1;
            Gdx.input.setInputProcessor(gameScreen.stage);
            dotNr = 1;
            dialog = new Dialog("Kommst du wieder raus?", game.uiSkin, "dialog") {
                public void result(Object obj) {
                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                }
            };
            dialog.text("Du hast dich in der dunklen Hoehle verlaufen. Aber Halt! " +
                    "\nDa faellt dir ein das dein Grossvater dir einen guten Algorithmus " +
                    "\nerklaert hat, um hier zu entkommen.");
            dialog.button("Okay");
            dialog.show(gameScreen.stage);
        }
    }

}
