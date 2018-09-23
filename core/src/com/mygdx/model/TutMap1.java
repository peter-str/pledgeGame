package com.mygdx.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.screens.GameScreen;
import com.mygdx.game.PledgeGame;
import com.mygdx.enums.MapEnum;

public class TutMap1 extends AbstractMap {

    private boolean enabled = true;
    private Label instruction1 = new Label("Schritt 1: Laufe geradeaus", game.uiSkin);
    private Label instruction2 = new Label("Schritt 2: (Zaehler <= 0 und Front versperrt) => Gehe nach rechts ", game.uiSkin);
    //private Label instruction2b = new Label("Schritt 2b: (Zaehler < 0 und Front und links versperrt) => Gehe nach rechts ", game.uiSkin);
    private Label instruction3 = new Label("Schritt 3: (Zaehler < 0 und Weg nach links frei) => Gehe nach links ", game.uiSkin);

    public TutMap1(final PledgeGame game, GameScreen gameScreen, MapEnum nextMap) {
        super(game, gameScreen, nextMap);
        tiledMap = new TmxMapLoader().load("core/assets/maps/randomMap.tmx");//map3
        spriteBatch2 = new SpriteBatch();
        controls = new Label("Steuerung" + "\nPfeiltaste hoch: Geradeaus" +
                "\nPfeiltaste runter: Zurueck" + "\nF1: Linksdrehen" + "\nF2: Rechtsdrehen", game.uiSkin);
        controls.setPosition(getStartX() + 120, getStartY() - 250);
        instruction1.setPosition(getStartX() - 320, getStartY() + 299);
        instruction2.setPosition(getStartX() - 320, getStartY() + 279);
        //instruction2b.setPosition(getStartX() - 320, getStartY() + 259);
        instruction3.setPosition(getStartX() - 320, getStartY() + 259);
    }

    @Override
    public int getStartX() {
        return 7*32;
    }

    @Override
    public int getStartY() {
        return 6*32;
    }

    public void showControls() {
        controls.draw(game.spriteBatch, 1);
    }

    public void showInstructions() {
        instruction1.draw(game.spriteBatch, 1);
        instruction2.draw(game.spriteBatch, 1);
        //instruction2b.draw(game.spriteBatch, 1);
        instruction3.draw(game.spriteBatch, 1);
    }

    @Override
    public MapEnum getNextMap() {
        return nextMap;
    }

    @Override
    public void zielErreicht(MapEnum nextMap) {
        super.zielErreicht(nextMap);
    }

    @Override
    public Difficulty getDifficulty() {
        return null;
    }
}
