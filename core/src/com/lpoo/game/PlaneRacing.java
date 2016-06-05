package com.lpoo.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.game.States.GameOverScreen;
import com.lpoo.game.States.MenuScreen;
import com.lpoo.game.States.ScreenManager;

public class PlaneRacing extends Game {
	public static final int WIDTH = 1100;
	public static final int HEIGHT = 480;

	public static final String TITLE = "PlaneRacing";

	private ScreenManager gsm;
	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new ScreenManager();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuScreen(gsm, this));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
}
