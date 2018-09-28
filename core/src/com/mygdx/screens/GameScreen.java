package com.mygdx.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

public class GameScreen implements Screen {

    private final PledgeGame game;
    public OrthographicCamera camera, camera2;
    private TiledMapRenderer tiledMapRenderer;
    private Difficulty difficulty;
    private Sprite sprite;
    private float x;
    private float y;
    public Stage stage;
    private AbstractMap map;
    private Label revCounter;
    private PlayerController playerController;
    private Player player;
    private boolean expertModeOn;
    private boolean tutorialFlag;
    private TextButton textButton;
    private TextButton resetButton;
    private MapEnum mapEnum;
    private ClickListener clickListener;

    public GameScreen(final PledgeGame game, MapEnum mapEnum, boolean tutorialFlag) {
        this.game = game;
        map = mapEnum.getMap(game, this);
        this.mapEnum = mapEnum;
        this.tutorialFlag = tutorialFlag;
        difficulty = map.getDifficulty();
    }

    public GameScreen(final PledgeGame game, MapEnum mapEnum, int diff, int x, int y) {
        this.game = game;
        switch (diff) {
            case 1:
                difficulty = new DifficultyEasy();
                break;
            case 2:
                difficulty = new DifficultyMedium();
                break;
            case 3:
                difficulty = new DifficultyHigh();
                break;
            case 4:
                difficulty = new DifficultyExpert();
                expertModeOn = true;
                break;
            default:
                difficulty = new DifficultyTutorial();
        }

        map = mapEnum.getMap(game, this);

        if(x != 0 && y != 0) {
            map.setStartX(x*32);
            map.setStartY(y*32);
        }
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

        if(!tutorialFlag) {
            camera.position.x = player.getX();
            camera.position.y = player.getY();
            Gdx.input.setInputProcessor(playerController);
            revCounter = new Label(String.valueOf(player.getRevCounter()), game.uiSkin);
            revCounter.setPosition(sprite.getX(), sprite.getY() - 320);
            revCounter.setFontScale(1.5f);
            //setDifficulty(map.getDifficulty());
        }

        if(tutorialFlag) {
            Gdx.input.setInputProcessor(stage);
            camera.translate(-64,-64);
            revCounter = new Label(String.valueOf(player.getRevCounter()), game.uiSkin);
            revCounter.setPosition(sprite.getX(), sprite.getY() - 320);
            textButton = new TextButton("Schritt", game.uiSkin);
            textButton.setPosition(camera.position.x, camera.position.y);

            resetButton = new TextButton("Reset", game.uiSkin);
            resetButton.setPosition(camera.position.x + 64, camera.position.y);

            stage.addActor(textButton);
            stage.addActor(resetButton);
            stage.addActor(map.getWindow());
            clickListener = map.getPlayButton();
            textButton.addListener(clickListener);

            resetButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(game, mapEnum, true));
                    dispose();
                }
            });
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); //blue 0.4f
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        camera2.update();
        map.message();

        if(!expertModeOn) {
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
        }

        game.spriteBatch.begin();

        game.spriteBatch.setProjectionMatrix(camera.combined);
        player.getSprite().draw(game.spriteBatch);
        x = player.getAnimX();
        y = player.getAnimY();
        sprite.setPosition(x, y);
        if(!tutorialFlag) {
            camera.position.x = x;
            camera.position.y = y;
        }
        if(difficulty.hasTexture())
            game.spriteBatch.draw(difficulty.getFOVTexture(), x - 334, y - 334);

        game.spriteBatch.setProjectionMatrix(camera2.combined);
        revCounter.setText(String.valueOf(player.getRevCounter()));
        revCounter.draw(game.spriteBatch, 1);
        playerController.update(delta, expertModeOn);
        map.showInstructions();

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
    }

    public void rotateCamera(String dir) {
        if (dir.equals("left")) {
            //camera.rotate(270f);
            sprite.rotate(90f);
        }
        if (dir.equals("right")) {
            //camera.rotate(90f);
            sprite.rotate(-90f);
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
    public AbstractMap getMap() {
        return map;
    }
    public Player getPlayer() {return player;}

    public void setDifficulty(Difficulty diff) {
        difficulty = diff;
    }
    public void setButtonListener(ClickListener clickListener) {
        textButton.removeListener(this.clickListener);
        textButton.addListener(clickListener);
    }
}
