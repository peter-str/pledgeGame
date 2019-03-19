package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.PledgeGame;

public class CreditsScreen implements Screen {

    private final PledgeGame game;
    private OrthographicCamera camera;
    private Stage stage;
    private Texture libgdxPNG;
    private Texture myNamePNG;

    public CreditsScreen(final PledgeGame game) {
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
        mainTable.top();
        mainTable.padTop(25);

        Table subTable = new Table();

        Label tilesetHeadline = new Label("Tileset: ", game.uiSkin);
        //tilesetHeadline.setColor(Color.RED);
        Label tilesetTitle = new Label("\"JohtoAllstarMix-Tileset\" ", game.uiSkin);
        tilesetTitle.setColor(Color.valueOf("#0645AD"));
        Label tilesetAuthorLink = new Label("mepotes,", game.uiSkin);
        tilesetAuthorLink.setColor(Color.valueOf("#0645AD"));
        Label tilesetAuthors = new Label("Pokemon Gaia Project, Zetavares and Mapmaster ", game.uiSkin);
        Label tilesetLicense = new Label("CC BY-NC-SA 2.0 DE", game.uiSkin);
        tilesetLicense.setColor(Color.valueOf("#0645AD"));

        tilesetTitle.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://fanart.pokefans.net/tilesets/12572");
            }
        });

        tilesetAuthorLink.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://user.pokefans.net/profile/mepotes");
            }
        });

        tilesetLicense.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://creativecommons.org/licenses/by-nc-sa/2.0/de/");
            }
        });

        Label playerSpriteHeadline = new Label("Player Sprite: ", game.uiSkin);
        Label playerSpriteTitle = new Label("\"RPG Character/MainGuySpriteSheet\" ", game.uiSkin);
        playerSpriteTitle.setColor(Color.valueOf("#0645AD"));
        Label playerSpriteAuthorLink = new Label("Curt", game.uiSkin);
        playerSpriteAuthorLink.setColor(Color.valueOf("#0645AD"));
        Label playerSpriteLicense = new Label("CC BY 3.0", game.uiSkin);
        playerSpriteLicense.setColor(Color.valueOf("#0645AD"));

        playerSpriteTitle.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://opengameart.org/content/rpg-character");
            }
        });

        playerSpriteAuthorLink.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://opengameart.org/users/curt");
            }
        });

        playerSpriteLicense.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://creativecommons.org/licenses/by/3.0/");
            }
        });

        Label fountainTitle = new Label("\"Tile: Fountain\" ", game.uiSkin);
        fountainTitle.setColor(Color.valueOf("#0645AD"));
        Label fountainAuthorLink = new Label("XDinky", game.uiSkin);
        fountainAuthorLink.setColor(Color.valueOf("#0645AD"));

        fountainTitle.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://www.deviantart.com/xdinky/art/Tile-Fountain-203289664");
            }
        });

        fountainAuthorLink.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://www.deviantart.com/xdinky");
            }
        });



        Label assetsTitle = new Label("\"Nature Basic Tile Set\" ", game.uiSkin);
        assetsTitle.setColor(Color.valueOf("#0645AD"));
        Label assetsAuthorLink = new Label("Liz Molnar", game.uiSkin);
        assetsAuthorLink.setColor(Color.valueOf("#0645AD"));

        assetsTitle.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://raventale.itch.io/nature-tile-set");
            }
        });

        assetsAuthorLink.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://raventale.itch.io/");
            }
        });


        TextButton backButton = new TextButton("Zurück", game.uiSkin);
        backButton.setSize(100f,20f);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game, false));
                dispose();
            }
        });

        mainTable.add(new Label("Credits (Externe Verlinkungen sind blau markiert):", game.uiSkin));
        mainTable.row();
        mainTable.add(new Label("", game.uiSkin));
        mainTable.row();
        mainTable.add(tilesetHeadline);
        mainTable.row();
        mainTable.add(tilesetTitle);
        mainTable.row();
        mainTable.add(new Label("by", game.uiSkin));
        mainTable.row();
        mainTable.add(tilesetAuthorLink);
        mainTable.row();
        mainTable.add(tilesetAuthors);
        mainTable.row();
        mainTable.add(new Label("is licensed under ", game.uiSkin));
        mainTable.row();
        mainTable.add(tilesetLicense);
        mainTable.row();
        mainTable.add(new Label("", game.uiSkin));
        mainTable.row();
        mainTable.add(playerSpriteHeadline);
        mainTable.row();
        mainTable.add(playerSpriteTitle);
        mainTable.row();
        mainTable.add(new Label("by", game.uiSkin));
        mainTable.row();
        mainTable.add(playerSpriteAuthorLink);
        mainTable.row();
        mainTable.add(new Label("is licensed under ", game.uiSkin));
        mainTable.row();
        mainTable.add(playerSpriteLicense);
        mainTable.row();
        mainTable.add(new Label("", game.uiSkin));
        mainTable.row();
        mainTable.add(new Label("CC0 licensed objects/No Copyright:", game.uiSkin));
        mainTable.row();
        /*
        mainTable.add(fountainTitle);
        mainTable.add(assetsTitle);
        mainTable.row();
        mainTable.add(new Label("by", game.uiSkin));
        mainTable.row();
        mainTable.add(fountainAuthorLink);
        mainTable.row();
        mainTable.add(new Label("", game.uiSkin));
        mainTable.row();

        mainTable.row();
        mainTable.add(new Label("by", game.uiSkin));
        mainTable.row();
        mainTable.add(assetsAuthorLink);
        */

        subTable.add(fountainTitle).spaceRight(50);
        subTable.add(assetsTitle);
        subTable.row();
        subTable.add(new Label("by", game.uiSkin)).spaceRight(50);
        subTable.add(new Label("by", game.uiSkin));
        subTable.row();
        subTable.add(fountainAuthorLink).spaceRight(50);
        subTable.add(assetsAuthorLink);
        mainTable.add(subTable);

        libgdxPNG = new Texture(Gdx.files.internal("libgdx.png"));
        myNamePNG = new Texture(Gdx.files.internal("myName.png"));
        Image myNameImage = new Image(myNamePNG);
        myNameImage.setPosition(Gdx.graphics.getWidth()/2f - myNamePNG.getWidth()/2f, 0);
        myNameImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://github.com/peter-str");
            }
        });

        Image libgdxImage = new Image(libgdxPNG);
        libgdxImage.setPosition(Gdx.graphics.getWidth() - libgdxPNG.getWidth() - 5, 5);
        libgdxImage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.net.openURI("https://libgdx.badlogicgames.com/");
            }
        });

        stage.addActor(myNameImage);
        stage.addActor(libgdxImage);
        stage.addActor(mainTable);
        stage.addActor(backButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
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

    }
}
