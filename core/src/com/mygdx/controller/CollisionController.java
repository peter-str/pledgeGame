package com.mygdx.controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.game.PledgeGame;
import com.mygdx.model.AbstractMap;
import com.mygdx.model.Player;

class CollisionController {

    private PledgeGame game;
    private Player player;
    private AbstractMap map;
    private TiledMapTileLayer collisionLayer;
    private int texPosX;
    private int texPosY;


    CollisionController(PledgeGame game, Player player, AbstractMap map) {
        this.game = game;
        this.player = player;
        this.map = map;
        collisionLayer = (TiledMapTileLayer) map.getTiledMap().getLayers().get(0);
        texPosX = player.getX();
        texPosY = player.getY();
    }

    private boolean checkCollision(int x, int y) {
        return collisionLayer.getCell(x/32,y/32).getTile().getProperties().containsKey("Wand");
    }

    void checkSurroundings() {
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

    boolean zielErreicht(int x, int y) {
        if(collisionLayer.getCell(x/32, y/32).getTile().getProperties().containsKey("Ziel")) {
            map.zielErreicht(map.getNextMap());
            return true;
        }
        return false;
    }


    public void overlay() {

        //Neutral(keine Hindernisse in der Umgebung)
        game.spriteBatch.draw(game.overlay[0], texPosX - 320, texPosY - 320);

        //Wände werden eingeblendet (OBEN, RECHTS, UNTEN, LINKS)
        if(player.isTop()) {
            game.spriteBatch.draw(game.overlay[1], texPosX - 288, texPosY - 256);
        }

        if(player.isRight()) {
            game.spriteBatch.draw(game.overlay[1], texPosX - 256, texPosY - 288);
        }

        if(player.isBottom()) {
            game.spriteBatch.draw(game.overlay[1], texPosX - 288, texPosY - 320);
        }

        if(player.isLeft()) {
            game.spriteBatch.draw(game.overlay[1], texPosX - 320, texPosY - 288);
        }

        /*if(player.isTop() && !player.isLeft() && !player.isRight()) {
            switch(player.getDirection()) {
                case NORTH: game.spriteBatch.draw(game.overlay[0], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
                case EAST: game.spriteBatch.draw(game.overlay[0], sprite.getX() - Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case SOUTH: game.spriteBatch.draw(game.overlay[0], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - 96 + Gdx.graphics.getHeight() / 2f);
                    break;
                case WEST: game.spriteBatch.draw(game.overlay[0], sprite.getX() - 96 + Gdx.graphics.getWidth() / 2f, sprite.getY() - Gdx.graphics.getHeight() / 2f);
                    break;
            }
        }*/
/*
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
        }*/
    }

    public void setMapRendererBool() {
        map.setMapRendererBool();
    }
}
