package com.mygdx.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.model.AbstractMap;
import com.mygdx.model.Player;
import com.mygdx.screens.GameScreen;

public class PlayerController extends InputAdapter {

    private Player player;
    private GameScreen gameScreen;
    private AbstractMap map;
    private TiledMapTileLayer collisonLayer;
    private boolean up, down;

    public PlayerController(Player player, GameScreen screen, AbstractMap map) {
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
        if(keycode == Input.Keys.UP)
            up = true;
        if(keycode == Input.Keys.DOWN)
            down = true;
        if(keycode == Input.Keys.LEFT) {
            player.rotateLeft();
            gameScreen.rotateCamera("left");
        }
        if(keycode == Input.Keys.RIGHT) {
            player.rotateRight();
            gameScreen.rotateCamera("right");
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.UP)
            up = false;
        if(keycode == Input.Keys.DOWN)
            down = false;

        return false;
    }

    public void update(float delta) {
        if(up && !checkCollision(player.getX(), player.getY() + 32)) {
            player.move(1);
        }

        if(down && !checkCollision(player.getX(), player.getY() -1))
            player.move(-1);

    }
}
