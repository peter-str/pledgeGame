package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.screens.MainMenuScreen;


public class PledgeGame extends Game {

	public SpriteBatch spriteBatch;
	public BitmapFont font;
	public Skin uiSkin;
	public Texture[] overlay = new Texture[8];

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		uiSkin = new Skin(Gdx.files.internal("core/assets/uiskin.json"));
		this.setScreen(new MainMenuScreen(this));

		/*
        0: oben
        1: rechts
        2: unten
        3: links
        4: oben + rechts
        5: oben + links
        6: unten + rechts
        7: unten + links
         */
		overlay[0] = new Texture(Gdx.files.internal("core/assets/topBlocked.png"));
		overlay[1] = new Texture(Gdx.files.internal("core/assets/rightBlocked.png"));
		overlay[2] = new Texture(Gdx.files.internal("core/assets/bottomBlocked.png"));
		overlay[3] = new Texture(Gdx.files.internal("core/assets/leftBlocked.png"));
		overlay[4] = new Texture(Gdx.files.internal("core/assets/topRightBlocked.png"));
		overlay[5] = new Texture(Gdx.files.internal("core/assets/topLeftBlocked.png"));
		overlay[6] = new Texture(Gdx.files.internal("core/assets/bottomRightBlocked.png"));
		overlay[7] = new Texture(Gdx.files.internal("core/assets/bottomLeftBlocked.png"));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
		font.dispose();
	}
}
