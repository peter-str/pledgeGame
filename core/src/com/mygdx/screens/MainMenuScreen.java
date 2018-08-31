package com.mygdx.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.PledgeGame;

public class MainMenuScreen implements Screen {

    private final PledgeGame game;
    private OrthographicCamera camera;
    private Stage stage;



    public MainMenuScreen(final PledgeGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640f, 640f);
        camera.update();

        stage = new Stage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Label infoLabel = new Label("Info: Mit der Escape-Taste kommst du jederzeit ins Hauptmenue zurueck", game.uiSkin);
        infoLabel.setPosition(60, 200);
        infoLabel.setColor(Color.RED);


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        Label headline = new Label("Willkommen zum Pledge-Algorithmus-Spiel", game.uiSkin);
        TextButton tutorialButton = new TextButton("Tutorial", game.uiSkin);
        TextButton playButton = new TextButton("Start", game.uiSkin);
        TextButton levelButton = new TextButton("Levelauswahl", game.uiSkin);
        TextButton exitButton = new TextButton("Exit", game.uiSkin);

        tutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_1));
                dispose();
            }
        });

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, MapEnum.MAP_1));
                dispose();
            }
        });

        levelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new LevelScreen(game));
                dispose();
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        mainTable.add(headline);
        mainTable.row();
        mainTable.add(tutorialButton);
        mainTable.row();
        mainTable.add(playButton);
        mainTable.row();
        mainTable.add(levelButton);
        mainTable.row();
        mainTable.add(exitButton);

        stage.addActor(mainTable);
        stage.addActor(infoLabel);
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
