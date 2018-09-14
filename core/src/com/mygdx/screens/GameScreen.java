package com.mygdx.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
    private int playerDirection = 360;
    private boolean drawTop = false;
    private boolean drawRight = false;
    private boolean drawBottom = false;
    private boolean drawLeft = false;
    private int revCounter = 0;
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
        playerController = new PlayerController(game, player, this, map);

        camera = new OrthographicCamera();
        camera2 = new OrthographicCamera();
        camera.setToOrtho(false, 640f, 640f);
        camera2.setToOrtho(false, 640f, 640f);
        stage = new Stage();


        //texturePlayer = new Texture(Gdx.files.internal("core/assets/player.png"));
        //textureAtlas = new TextureAtlas(Gdx.files.internal("texturePlayer/spritesheet.atlas"));
        //TextureAtlas.AtlasRegion region = textureAtlas.findRegion("0001");



        //sprite = new Sprite(texturePlayer);
        sprite = player.getSprite();
        //sprite.translate(player.getX(), player.getY());


        point = new Texture(Gdx.files.internal("core/assets/point.png"));
        sprite.translate(map.getStartX(), map.getStartY());
        camera.translate(sprite.getX() - 320, sprite.getY() - 320);
        camera2.translate(sprite.getX() - 320, sprite.getY() - 320);
        camera.update();
        camera2.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(map.getTiledMap());
        // if(mapEnum == MapEnum.MAP_1)
        //    collisonLayer = (TiledMapTileLayer) map.getTiledMap().getLayers().get(1);
        //if(mapEnum == MapEnum.TUTORIALMAP_1)

        Gdx.input.setInputProcessor(playerController);

        label = new Label(String.valueOf(revCounter), game.uiSkin);
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
        label.setText(String.valueOf(revCounter));
        label.draw(game.spriteBatch, 1);
        map.showControls();
        map.showInstructions();
        game.spriteBatch.end();


       /* map.blueDots(sprite.getX(), sprite.getY());
        if(!zielErreicht((int) sprite.getX(), (int) sprite.getY()))
            checkSurroundings((int) sprite.getX(),(int) sprite.getY());
        */
        stage.act();
        stage.draw();


        playerController.update(delta);
        player.update(delta);
        if(player.getMoveCameraUpBool())
            moveCamera(1);
        if(player.getMoveCameraDownBool())
            moveCamera(-1);
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

    public void moveCamera(int dir) {
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

    /*
    public boolean zielErreicht(int x, int y) {
        if(collisonLayer.getCell(x/32, y/32).getTile().getProperties().containsKey("Ziel")) {
            map.zielErreicht(map.getNextMap());
            return true;
        }
        return false;
    }

    public void checkSurroundings(int x, int y) {
        drawTop = collisonLayer.getCell(x/32, y/32 +1).getTile().getProperties().containsKey("Wand");
        drawRight = collisonLayer.getCell(x/32 +1, y/32).getTile().getProperties().containsKey("Wand");
        drawBottom = collisonLayer.getCell(x/32, y/32 -1).getTile().getProperties().containsKey("Wand");
        drawLeft = collisonLayer.getCell(x/32 -1, y/32).getTile().getProperties().containsKey("Wand");
    }*/

    /*
    public void overlay() {
        //WÄNDE (OBEN, RECHTS, UNTEN, LINKS)

        if(drawTop && !drawLeft && !drawRight) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[0], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[0], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[0], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[0], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        if(drawRight && !drawTop && !drawBottom) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[1], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[1], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[1], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[1], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        if(drawBottom && !drawLeft && !drawRight) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[2], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[2], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[2], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[2], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        if(drawLeft && !drawTop && !drawBottom) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[3], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[3], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[3], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[3], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        //ECKEN

        if(drawTop && drawRight) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[4], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[4], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[4], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[4], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        if(drawTop && drawLeft) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[5], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[5], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[5], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[5], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        if(drawBottom && drawRight) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[6], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[6], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[6], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[6], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }

        if(drawBottom && drawLeft) {
            switch(playerDirection) {
                case 360: game.spriteBatch.draw(game.overlay[7], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case 90: game.spriteBatch.draw(game.overlay[7], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 180: game.spriteBatch.draw(game.overlay[7], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case 270: game.spriteBatch.draw(game.overlay[7], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }
    }*/
}
