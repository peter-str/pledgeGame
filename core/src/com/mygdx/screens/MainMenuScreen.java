package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;

public class MainMenuScreen implements Screen {

    private final PledgeGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private boolean endlessModeSetupMenue;


    public MainMenuScreen(final PledgeGame game, boolean endlessModeSetupMenue) {
        this.game = game;
        this.endlessModeSetupMenue = endlessModeSetupMenue;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640f, 640f);
        camera.update();

        stage = new Stage();
    }

    @Override
    public void show() {
        if(!endlessModeSetupMenue) {
            Gdx.input.setInputProcessor(stage);

            Label infoLabel = new Label("Info: Mit der Escape-Taste kommst du jederzeit ins Hauptmenue zurueck", game.uiSkin);
            infoLabel.setPosition(60, 185);
            infoLabel.setColor(Color.RED);

            Label difficultyLabel = new Label("Schwierigkeit: ", game.uiSkin);
            difficultyLabel.setPosition(60, 220);

            Table mainTable = new Table();
            mainTable.setFillParent(true);
            mainTable.center();

            Label headline = new Label("Willkommen zum Pledge-Algorithmus-Spiel", game.uiSkin);
            Label breakLabel = new Label("", game.uiSkin);
            TextButton tutorialButton = new TextButton("Einfuehrung", game.uiSkin);
            TextButton endlessButton = new TextButton("Endlos-Modus", game.uiSkin);
            TextButton levelButton = new TextButton("Levelauswahl", game.uiSkin);
            TextButton exitButton = new TextButton("Exit", game.uiSkin);
            TextButton creditsButton = new TextButton("Credits", game.uiSkin);

            tutorialButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game, MapEnum.INTRODUCTION_1, false));
                    dispose();
                }
            });

            endlessButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new MainMenuScreen(game, true));
                    //game.setScreen(new GameScreen(game, MapEnum.ENDLESS_MAZE, selectBox.getSelectedIndex()-1));
                    dispose();
                }
            });

            levelButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new LevelScreen(game));
                    dispose();
                }
            });

            exitButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });

            creditsButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new CreditsScreen(game));
                    dispose();
                    //Gdx.net.openURI("https://creativecommons.org/licenses/by/3.0/");
                }
            });

            mainTable.add(headline);
            mainTable.row();
            mainTable.add(breakLabel);
            mainTable.row();
            mainTable.add(tutorialButton);
            mainTable.row();
            mainTable.add(endlessButton);
            mainTable.row();
            mainTable.add(levelButton);
            mainTable.row();
            mainTable.add(exitButton);
            mainTable.row();
            mainTable.add(creditsButton);
            mainTable.row();

            stage.addActor(mainTable);
            stage.addActor(infoLabel);
        }

        if(endlessModeSetupMenue) {
            Gdx.input.setInputProcessor(stage);

            Label headline = new Label("Endlos-Modus-Menue", game.uiSkin);
            Label infoLabel = new Label ("Jedes Level das du hier startest wird neu generiert. \n" +
                    "Du spielst also niemals zweimal das gleiche Level hintereinander. ", game.uiSkin);
            Label breakLabel = new Label("", game.uiSkin);
            Label difficultyLabel = new Label("Schwierigkeit einstellen: ", game.uiSkin);
            Label mapSizeLabel = new Label("Kartengroesse einstellen: ", game.uiSkin);

            Table mainTable = new Table();
            mainTable.setFillParent(true);
            mainTable.center();

            TextButton playButton = new TextButton("Start", game.uiSkin);
            TextButton backButton = new TextButton("Zurueck", game.uiSkin);
            final TextButton showAlgoWindowButton = new TextButton("Algorithmus anzeigen", game.uiSkin, "toggle");

            String[] difficultyStrings = {"Leicht", "Mittel", "Schwer", "Experte"};
            final SelectBox<String> selectBox = new SelectBox<>(game.uiSkin);
            selectBox.setItems(difficultyStrings);

            String[] mapSizeStrings = {"Klein", "Mittel", "Gross"};
            final SelectBox<String> mapSizeSelectBox = new SelectBox<>(game.uiSkin);
            mapSizeSelectBox.setItems(mapSizeStrings);

            playButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game, selectBox.getSelectedIndex(), mapSizeSelectBox.getSelectedIndex(), showAlgoWindowButton.isChecked()));
                    dispose();
                }
            });

            backButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new MainMenuScreen(game, false));
                    dispose();
                }
            });

            mainTable.add(headline);
            mainTable.row();
            mainTable.add(breakLabel);
            mainTable.row();
            mainTable.add(infoLabel);
            mainTable.row();
            mainTable.add(breakLabel);
            mainTable.row();
            mainTable.add(difficultyLabel);
            mainTable.row();
            mainTable.add(selectBox);
            mainTable.row();
            mainTable.add(mapSizeLabel);
            mainTable.row();
            mainTable.add(mapSizeSelectBox);
            mainTable.row();
            mainTable.add(breakLabel);
            mainTable.row();
            mainTable.add(showAlgoWindowButton);
            mainTable.row();
            mainTable.add(breakLabel);
            mainTable.row();
            mainTable.add(playButton);
            mainTable.row();
            mainTable.add(breakLabel);
            mainTable.row();
            mainTable.add(backButton);

            stage.addActor(mainTable);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
