package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;

public class LevelScreen implements Screen {

    private PledgeGame game;
    private OrthographicCamera camera;
    private Stage stage;

    public LevelScreen(final PledgeGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640f, 640f);
        camera.update();

        stage = new Stage();
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        Label headline = new Label("Levelübersicht", game.uiSkin);
        Label breakLabel = new Label("", game.uiSkin);

        TextButton introductionLevel1 = new TextButton("Einführungslevel 1", game.uiSkin);
        TextButton introductionLevel2 = new TextButton("Einführungslevel 2", game.uiSkin);
        TextButton introductionLevel3 = new TextButton("Einführungslevel 3", game.uiSkin);

        TextButton tutorialLevel1 = new TextButton("Tutoriallevel 1", game.uiSkin);
        TextButton tutorialLevel2 = new TextButton("Tutoriallevel 2", game.uiSkin);
        TextButton tutorialLevel3 = new TextButton("Tutoriallevel 3", game.uiSkin);
        TextButton tutorialLevel4 = new TextButton("Tutoriallevel 4", game.uiSkin);
        TextButton tutorialLevel5 = new TextButton("Tutoriallevel 5", game.uiSkin);
        TextButton tutorialLevel6 = new TextButton("Tutoriallevel 6", game.uiSkin);
        TextButton tutorialLevel7 = new TextButton("Tutoriallevel 7", game.uiSkin);
        TextButton tutorialLevel8 = new TextButton("Tutoriallevel 8", game.uiSkin);

        TextButton finalLevel = new TextButton("Endlevel", game.uiSkin);
        TextButton backButton = new TextButton("Zurück", game.uiSkin);


        introductionLevel1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.INTRODUCTION_1, false));
                dispose();
            }
        });

        introductionLevel2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.INTRODUCTION_2, false));
                dispose();
            }
        });

        introductionLevel3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.INTRODUCTION_3, false));
                dispose();
            }
        });

        tutorialLevel1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_1, true));
                dispose();
            }
        });

        tutorialLevel2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_2, true));
                dispose();
            }
        });

        tutorialLevel3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_3, true));
                dispose();
            }
        });

        tutorialLevel4.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_4, true));
                dispose();
            }
        });

        tutorialLevel5.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_5, true));
                dispose();
            }
        });

        tutorialLevel6.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_6, false));
                dispose();
            }
        });

        tutorialLevel7.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_7, false));
                dispose();
            }
        });

        tutorialLevel8.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.TUTORIALMAP_8, false));
                dispose();
            }
        });

        finalLevel.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game, MapEnum.INTRODUCTION_3_2, false));
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
        mainTable.add(introductionLevel1).size(150,25);
        mainTable.row();
        mainTable.add(introductionLevel2).size(150,25);
        mainTable.row();
        mainTable.add(introductionLevel3).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel1).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel2).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel3).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel4).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel5).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel6).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel7).size(150,25);
        mainTable.row();
        mainTable.add(tutorialLevel8).size(150,25);
        mainTable.row();
        mainTable.add(finalLevel).size(150,25);
        mainTable.row();
        mainTable.add(breakLabel);
        mainTable.row();
        mainTable.add(backButton).size(150,25);

        stage.addActor(mainTable);
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
