package com.mygdx.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.controller.PlayerController;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.*;
import com.mygdx.model.difficulties.*;
import com.mygdx.model.maps.AbstractMap;
import com.mygdx.model.maps.EndlessMazeMap;
import com.mygdx.model.tutorialStrategies.TutorialStrategy;

public class GameScreen implements Screen, GameScreenInterface {

    private final PledgeGame game;
    private OrthographicCamera camera, camera2;
    private TiledMapRenderer tiledMapRenderer;
    private Difficulty difficulty;
    private Sprite sprite;
    private Stage stage;
    private AbstractMap map;
    private Label revCounter;
    private PlayerController playerController;
    private Player player;
    private boolean expertModeOn;
    private boolean tutorialFlag;
    private MapEnum mapEnum;
    private TutorialStrategy strategy;
    private boolean playMode;
    private boolean showAlgoWindow;
    private Texture compassBackground;

    public GameScreen(final PledgeGame game, MapEnum mapEnum, boolean tutorialFlag) {
        this.game = game;
        map = mapEnum.getMap(game);
        map.register(this);
        showAlgoWindow = true;
        this.mapEnum = mapEnum;
        this.tutorialFlag = tutorialFlag;
        difficulty = map.getDifficulty();
        if(difficulty.getClass().equals(DifficultyExpert.class))
            expertModeOn = true;
    }

    public GameScreen(final PledgeGame game, int selectedDifficulty, int selectedMapSize, boolean showAlgoWindow) {
        this.game = game;
        int mapSize;
        this.showAlgoWindow = showAlgoWindow;

        switch(selectedMapSize) {
            default:
                mapSize = 3;
                break;
            case 1:
                mapSize = 6;
                break;
            case 2:
                mapSize = 10;
                break;
        }
        switch (selectedDifficulty) {
            case 0:
                difficulty = new DifficultyTutorial(mapSize);
                break;
            case 1:
                difficulty = new DifficultyMedium(mapSize);
                break;
            case 2:
                difficulty = new DifficultyHigh(mapSize);
                break;
            case 3:
                difficulty = new DifficultyExpert(mapSize);
                expertModeOn = true;
                break;
        }

        map = new EndlessMazeMap(game, null, difficulty.getTiledMap());
        map.register(this);
    }

    @Override
    public void show() {
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        player = new Player(map.getStartX(), map.getStartY());
        playerController = new PlayerController(game, player, this);
        camera = new OrthographicCamera();
        camera2 = new OrthographicCamera();
        camera.setToOrtho(false, 640f, 640f);
        camera2.setToOrtho(false, 640f, 640f);
        stage = new Stage();
        sprite = player.getSprite();
        sprite.translate(map.getStartX(), map.getStartY());
        compassBackground = new Texture(Gdx.files.internal("collision/compass_background.png"));

        if(!tutorialFlag) {
            camera.position.x = player.getX();
            camera.position.y = player.getY();
            camera2.position.x = player.getX();
            camera2.position.y = player.getY();
            Gdx.input.setInputProcessor(playerController);
            revCounter = new Label(String.valueOf(player.getRevCounter()), game.uiSkin);
            revCounter.setPosition(player.getX() - 320, player.getY() - 203);
            //revCounter.setFontScale(1.5f);
            if(map.getWindow() != null)
                stage.addActor(map.getWindow());
            if(map.getAlgoWindow() != null && showAlgoWindow)
                stage.addActor(map.getAlgoWindow());
            if(mapEnum == MapEnum.INTRODUCTION_3) {
               Label hilfeLabel = new Label("Drücke 'T', um ins Tutorial zu gelangen.", game.uiSkin);
               hilfeLabel.setPosition(Gdx.graphics.getWidth() - hilfeLabel.getWidth(), 0);
               stage.addActor(hilfeLabel);
            }
        }

        if(tutorialFlag) {
            Gdx.input.setInputProcessor(stage);
            camera.translate(-64,-64);
            TextButton menuButton = new TextButton("Hauptmenü", game.uiSkin);
            menuButton.setPosition(0, 0);
            menuButton.setSize(90, 25);

            TextButton playButton = new TextButton("Play/Pause", game.uiSkin);
            playButton.setPosition(menuButton.getWidth(), 0);
            playButton.setSize(90, 25);

            TextButton stepButton = new TextButton("1 Schritt", game.uiSkin);
            stepButton.setPosition(playButton.getX() + playButton.getWidth(), 0);
            stepButton.setSize(90, 25);

            TextButton resetButton = new TextButton("Reset", game.uiSkin);
            resetButton.setPosition(stepButton.getX() + stepButton.getWidth(), 0);
            resetButton.setSize(90, 25);

            revCounter = new Label(String.valueOf(player.getRevCounter()), game.uiSkin);
            revCounter.setPosition(64, 32);
            //revCounter.setFontScale(1.5f);

            stage.addActor(menuButton);
            stage.addActor(playButton);
            stage.addActor(stepButton);
            stage.addActor(resetButton);
            stage.addActor(map.getWindow());
            stage.addActor(map.getAlgoWindow());
            strategy = map.getStartStrategy();

            menuButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new MainMenuScreen(game, false));
                    dispose();
                }
            });

            playButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    playMode = !playMode;
                }
            });

            stepButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    strategy.algorithm(GameScreen.this);
                }
            });

            resetButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game, mapEnum, true));
                    dispose();
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.4f, 1); //blue 0.4f
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        camera2.update();
        map.message();

        if(!expertModeOn) {
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
        }

        if(playMode) {
            strategy.algorithm(this);
        }

        game.spriteBatch.begin();

        game.spriteBatch.setProjectionMatrix(camera.combined);
        player.getSprite().draw(game.spriteBatch);
        float x = player.getAnimX();
        float y = player.getAnimY();
        sprite.setPosition(x, y);
        if(!tutorialFlag) {
            camera.position.x = x+16;
            camera.position.y = y+16;
        }
        if(difficulty.hasTexture())
            game.spriteBatch.draw(difficulty.getFovTexture(), x - 334, y - 334);

        map.showInstructions(player.getX() / 32, player.getY() / 32);
        map.showInstructions(player.getRevCounter(), player.isTop(), player.isRight(), player.isBottom(), player.isLeft());

        game.spriteBatch.setProjectionMatrix(camera2.combined);
        revCounter.setText("Kompass: " + String.valueOf(player.getRevCounter()));
        if(mapEnum != MapEnum.INTRODUCTION_1) {
            game.spriteBatch.draw(compassBackground, revCounter.getX(), revCounter.getY()-5);
            revCounter.draw(game.spriteBatch, 1);
        }

        playerController.update(delta, expertModeOn);

        game.spriteBatch.end();

        stage.act();
        stage.draw();
        player.update(delta);
    }

    @Override
    public void resize(int width, int height) {    }

    @Override
    public void pause() {    }

    @Override
    public void resume() {    }

    @Override
    public void hide() {    }

    @Override
    public void dispose() {
        stage.dispose();
        map.dispose();
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    @Override
    public Stage getStage() {
        return stage;
    }

    public AbstractMap getMap() {
        return map;
    }
    public Player getPlayer() {return player;}
    public void setPlayMode(boolean playMode) {this.playMode = playMode;}

    public void setDifficulty(Difficulty diff) {
        difficulty = diff;
    }

    @Override
    public void updateStrategy(TutorialStrategy strategy) {
        this.strategy = strategy;
    }
}
