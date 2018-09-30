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
	public Texture[] overlay = new Texture[2];

	@Override
	public void create() {
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
		uiSkin = new Skin(Gdx.files.internal(ResourcePaths.UIPATH));
		this.setScreen(new MainMenuScreen(this));
		overlay[0] = new Texture(Gdx.files.internal(ResourcePaths.NEUTRAL));
		overlay[1] = new Texture(Gdx.files.internal(ResourcePaths.WALL));
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
