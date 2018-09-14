package com.mygdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.AbstractMap;
import com.mygdx.model.Player;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;

public class PlayerController extends InputAdapter {

    private PledgeGame game;
    private Player player;
    private GameScreen gameScreen;
    private AbstractMap map;
    private TiledMapTileLayer collisonLayer;
    private boolean up, down;

    public PlayerController(PledgeGame game, Player player, GameScreen screen, AbstractMap map) {
        this.game = game;
        this.player = player;
        this.gameScreen = screen;
        this.map = map;
        collisonLayer = (TiledMapTileLayer) map.getTiledMap().getLayers().get(0);
    }

    public boolean checkCollision(float x, float y) {
      return collisonLayer.getCell((int) x/32, (int) y/32).getTile().getProperties().containsKey("Wand");
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.UP) {
            up = true;
            return false;
        }
            //player.move(32);

        if(keycode == Input.Keys.DOWN)
            down = true;
            //player.move(-32);

        if(keycode == Input.Keys.LEFT) {
            player.rotateLeft();
            gameScreen.rotateCamera("left");
        }
        if(keycode == Input.Keys.RIGHT) {
            player.rotateRight();
            gameScreen.rotateCamera("right");
        }
        if(keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MainMenuScreen(game));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.UP) {
            up = false;
        }
        if(keycode == Input.Keys.DOWN)
            down = false;

        return false;
    }

    public void update(float delta) {
        checkSurroundings();

        if(up && !player.isTop())
            player.move(32);

        if(down && !player.isBottom())
            player.move(-32);
    }

    public void checkSurroundings() {
        switch (player.getDirection()) {
            case NORTH:
                player.setTop(checkCollision(player.getX(), player.getY() + 32));
                player.setRight(checkCollision(player.getX() + 32, player.getY()));
                player.setBottom(checkCollision(player.getX(), player.getY() - 1));
                player.setLeft(checkCollision(player.getX() - 1, player.getY()));
                break;
            case EAST:
                player.setTop(checkCollision(player.getX() + 32, player.getY()));
                player.setRight(checkCollision(player.getX(), player.getY() - 1));
                player.setBottom(checkCollision(player.getX() - 1, player.getY()));
                player.setLeft(checkCollision(player.getX(), player.getY() + 32));
                break;
            case SOUTH:
                player.setTop(checkCollision(player.getX(), player.getY() - 1));
                player.setRight(checkCollision(player.getX() - 1, player.getY()));
                player.setBottom(checkCollision(player.getX(), player.getY() + 32));
                player.setLeft(checkCollision(player.getX() + 32, player.getY()));
                break;
            case WEST:
                player.setTop(checkCollision(player.getX() - 1, player.getY()));
                player.setRight(checkCollision(player.getX(), player.getY() + 32));
                player.setBottom(checkCollision(player.getX() + 32, player.getY()));
                player.setLeft(checkCollision(player.getX(), player.getY() - 1));
                break;
        }
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }
}
