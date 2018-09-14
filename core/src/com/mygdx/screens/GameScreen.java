package com.mygdx.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.controller.PlayerController;
import com.mygdx.enums.MapEnum;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.AbstractMap;
import com.mygdx.model.Player;

public class GameScreen implements Screen {

    private final PledgeGame game;
    public OrthographicCamera camera, camera2;
    private TiledMapRenderer tiledMapRenderer;
    public Texture point;
    private Sprite sprite;
    private boolean drawTop = false;
    private boolean drawRight = false;
    private boolean drawBottom = false;
    private boolean drawLeft = false;
    public Stage stage;
    private AbstractMap map;
    private Label label;
    private boolean mapRendererEnabled = true;
    private PlayerController playerController;
    private Player player;

    public GameScreen(final PledgeGame game, MapEnum mapEnum) {
        this.game = game;
        map = mapEnum.getMap(game, this);
        player = new Player(map.getStartX(), map.getStartY());
        playerController = new PlayerController(game, player, this);
        camera = new OrthographicCamera();
        camera2 = new OrthographicCamera();
        camera.setToOrtho(false, 640f, 640f);
        camera2.setToOrtho(false, 640f, 640f);
        stage = new Stage();
        sprite = player.getSprite();
        point = new Texture(Gdx.files.internal("core/assets/point.png"));
        sprite.translate(map.getStartX(), map.getStartY());
        camera.translate(sprite.getX() - 320, sprite.getY() - 320);
        camera2.translate(sprite.getX() - 320, sprite.getY() - 320);
        camera.update();
        camera2.update();
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        Gdx.input.setInputProcessor(playerController);
        label = new Label(String.valueOf(player.getRevCounter()), game.uiSkin);
        label.setPosition(sprite.getX(), sprite.getY() - 320);
        label.setFontScale(1.5f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.4f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        camera2.update();

        if (mapRendererEnabled) {
            tiledMapRenderer.setView(camera);
            tiledMapRenderer.render();
        }

        game.spriteBatch.begin();
        game.spriteBatch.setProjectionMatrix(camera.combined);
        player.getSprite().draw(game.spriteBatch);
        sprite.setPosition(player.getAnimX(), player.getAnimY());
        //overlay();

        game.spriteBatch.setProjectionMatrix(camera2.combined);
        label.setText(String.valueOf(player.getRevCounter() + "            FPS: " + Gdx.graphics.getFramesPerSecond()));
        label.draw(game.spriteBatch, 1);
        playerController.update(delta);
        map.showControls();
        map.showInstructions();
        game.spriteBatch.end();
        stage.act();
        stage.draw();

        player.update(delta);
        if(player.getMoveCameraUpBool())
            moveCamera(1);
        if(player.getMoveCameraDownBool())
            moveCamera(-1);
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
        point.dispose();
    }

    public void rotateCamera(String dir) {
        if (dir.equals("left")) {
            camera.rotate(270f);
            sprite.rotate(90f);
        }
        if (dir.equals("right")) {
            camera.rotate(90f);
            sprite.rotate(-90f);
        }
    }

    private void moveCamera(int dir) {
        switch (player.getDirection()) {
            case NORTH:
                camera.translate(0, dir);
                break;
            case EAST:
                camera.translate(dir, 0);
                break;
            case SOUTH:
                camera.translate(0, -dir);
                break;
            case WEST:
                camera.translate(-dir, 0);
                break;
        }
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
    public AbstractMap getMap() {
        return map;
    }


    /*
    public void checkSurroundings(int x, int y) {
        drawTop = collisonLayer.getCell(x/32, y/32 +1).getTile().getProperties().containsKey("Wand");
        drawRight = collisonLayer.getCell(x/32 +1, y/32).getTile().getProperties().containsKey("Wand");
        drawBottom = collisonLayer.getCell(x/32, y/32 -1).getTile().getProperties().containsKey("Wand");
        drawLeft = collisonLayer.getCell(x/32 -1, y/32).getTile().getProperties().containsKey("Wand");
    }*/
}
