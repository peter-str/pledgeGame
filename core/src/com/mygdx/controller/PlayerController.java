package com.mygdx.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.Player;
import com.mygdx.screens.GameScreen;
import com.mygdx.screens.MainMenuScreen;

public class PlayerController extends InputAdapter {

    private PledgeGame game;
    private Player player;
    private GameScreen gameScreen;
    private CollisionController collisionController;
    private boolean up, down;

    public PlayerController(PledgeGame game, Player player, GameScreen screen) {
        this.game = game;
        this.player = player;
        this.gameScreen = screen;
        this.collisionController = new CollisionController(this.game, this.player, gameScreen.getMap());
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
        if(keycode == Input.Keys.ESCAPE) {
            game.setScreen(new MainMenuScreen(game));
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
        if(collisionController.zielErreicht(player.getX(), player.getY()))
            return;

        collisionController.checkSurroundings();
        collisionController.overlay();

        if(up && !player.isTop())
            player.move(32);

        if(down && !player.isBottom())
            player.move(-32);
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }
}
