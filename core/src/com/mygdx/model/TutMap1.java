package com.mygdx.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
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
        tiledMap = new TmxMapLoader().load("core/assets/TutMap1.tmx");
        spriteBatch2 = new SpriteBatch();
        controls = new Label("Steuerung" + "\nPfeiltaste hoch: Geradeaus" +
                "\nPfeiltaste runter: Zurueck" + "\nF1: Linksdrehen" + "\nF2: Rechtsdrehen", game.uiSkin);
        controls.setPosition(getStartX() + 120, getStartY() - 320);
        instruction1.setPosition(getStartX() - 320, getStartY() + 299);
        instruction2.setPosition(getStartX() - 320, getStartY() + 279);
        //instruction2b.setPosition(getStartX() - 320, getStartY() + 259);
        instruction3.setPosition(getStartX() - 320, getStartY() + 259);
    }

    public void blueDots(float x, float y) {
        switch (dotNr) {
            case 0:
                if(x/32 == 2 && y/32 == 2&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 1;
                    dialog = new Dialog("Erste Regel", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        }
                    };
                    dialog.text("Am Anfang gehst du geradeaus, bis eine Mauer dir den Weg versperrt. " +
                            "\nProbiere es aus, folge den blauen Markierungen. ");
                    dialog.button("Okay");
                    dialog.show(gameScreen.stage);
                }
                break;
            case 1:
                instruction1.setColor(Color.RED);
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 2*32, 5*32);
                spriteBatch2.end();
                if(x/32 == 2 && y/32 == 5&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 2;
                    dialog = new Dialog("Zweiter Schritt", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            dialog = new Dialog("Zweite Regel", game.uiSkin, "dialog") {
                                public void result(Object obj) {
                                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                                }
                            };
                            dialog.text("Ist der Weg nach links blockiert oder der Drehungszaehler = 0, " +
                                    "\nso drehen wir uns nach rechts und gehen weiter. ");
                            dialog.button("Okay");
                            dialog.show(gameScreen.stage);                        }
                    };
                    dialog.text("Super! Nun folgt Schritt 2 des Algorithmus: ");
                    dialog.button("Weiter");
                    dialog.show(gameScreen.stage);
                }
                break;
            case 2:
                instruction1.setColor(Color.WHITE);
                instruction2.setColor(Color.RED);
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 5*32, 5*32);
                spriteBatch2.end();
                if(x/32 == 5 && y/32 == 5&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 3;
                    dialog = new Dialog("Zweiter Schritt", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        }
                    };
                    dialog.text("Nach links koennen wir nicht, also geht es rechts weiter. ");
                    dialog.button("Okay");
                    dialog.show(gameScreen.stage);
                }
                break;

            case 3:
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 5*32, 1*32);
                spriteBatch2.end();
                if(x/32 == 5 && y/32 == 1&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 4;
                    dialog = new Dialog("Dritter Schritt", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            dialog = new Dialog("Dritte Regel", game.uiSkin, "dialog") {
                                public void result(Object obj) {
                                    Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                                }
                            };
                            dialog.text("Ist der Drehungszaehler < 0 und wir kommen an eine Stelle, an der wir nach links " +
                                    "\nweiterlaufen koennen, dann muessen wir diesen Weg nehmen! ");
                            dialog.button("Okay");
                            dialog.show(gameScreen.stage);
                        }
                    };
                    dialog.text("Aufgepasst! Nun wird es spannend ;) " +
                            "\nWir werden diesmal naemlich nach links weiter laufen. " +
                            "\nAber warum?");
                    dialog.button("Weiter zur Erklaerung");
                    dialog.show(gameScreen.stage);
                }
                break;

            case 4:
                instruction2.setColor(Color.WHITE);
                instruction3.setColor(Color.RED);
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 8*32, 1*32);
                spriteBatch2.end();
                if(x/32 == 8 && y/32 == 1&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 5;
                    dialog = new Dialog("Dritter Schritt", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        }
                    };
                    dialog.text("Erneut müssen wir nach links abbiegen, da der Drehungszaehler < 0 ist. ");
                    dialog.button("Okay");
                    dialog.show(gameScreen.stage);
                }
                break;
            case 5:
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 8*32, 3*32);
                spriteBatch2.end();
                if(x/32 == 8 && y/32 == 3&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 6;
                    dialog = new Dialog("Erneut Schritt 1", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        }
                    };
                    dialog.text("Der Weg links ist zwar frei, aber da unser Drehungszaehler 0 ist, gehen wir geradeaus. ");
                    dialog.button("Okay");
                    dialog.show(gameScreen.stage);
                }
                break;
            case 6:
                instruction3.setColor(Color.WHITE);
                instruction1.setColor(Color.RED);
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 8*32, 5*32);
                spriteBatch2.end();
                if(x/32 == 8 && y/32 == 5&& enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 7;
                    dialog = new Dialog("Erneut Schritt 2", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        }
                    };
                    dialog.text("Gleiche Situation wie am Anfang. " +
                            "\nDrehungszaehler ist 0, also nach rechts weiter. ");
                    dialog.button("Okay");
                    dialog.show(gameScreen.stage);
                }
                break;
            case 7:
                instruction1.setColor(Color.WHITE);
                instruction2.setColor(Color.RED);
                spriteBatch2.begin();
                spriteBatch2.setProjectionMatrix(gameScreen.camera.combined);
                spriteBatch2.draw(gameScreen.point, 10*32, 5*32);
                spriteBatch2.end();
                if(x/32 == 10 && y/32 == 5 && enabled) {
                    Gdx.input.setInputProcessor(gameScreen.stage);
                    dotNr = 8;
                    dialog = new Dialog("Erneut Schritt 3", game.uiSkin, "dialog") {
                        public void result(Object obj) {
                            Gdx.input.setInputProcessor(gameScreen.getPlayerController());
                        }
                    };
                    dialog.text("Drehungszaehler negativ und der Weg nach links ist frei. " +
                            "\nDas ist der Ausgang! ");
                    dialog.button("Okay");
                    dialog.show(gameScreen.stage);
                }
                break;
            case 8:
                instruction2.setColor(Color.WHITE);
                instruction3.setColor(Color.RED);
                break;
        }
    }

    @Override
    public int getStartX() {
        return 2*32;
    }

    @Override
    public int getStartY() {
        return 2*32;
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
}
