package com.lpoo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.lpoo.game.States.MenuScreen;
import com.lpoo.game.States.ScreenManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

import sun.rmi.runtime.Log;

public class PlaneRacing extends Game {
	public static final int WIDTH = 1100;
	public static final int HEIGHT = 480;

	public static final String TITLE = "PlaneRacing";

	private ScreenManager gsm;
	public SpriteBatch batch;
	public Music song;
	public boolean on;
	public int dif;
	public ArrayList<Integer> scores;

	private Preferences scoresPref;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new ScreenManager();

		scores = new ArrayList<Integer>();
		scores.add(0);
		scores.add(0);
		scores.add(0);

		scoresPref = Gdx.app.getPreferences("High scores");
		String name = scoresPref.getString("name", "no name");
		if (name.equals("no name")){
			scoresPref.putInteger("score1", 0);
			scoresPref.putInteger("score2", 0);
			scoresPref.putInteger("score3", 0);
		}
		else
			readScores();

		on = true;
		dif = 1;

		song = Gdx.audio.newMusic((Gdx.files.internal("unity.mp3")));

		song.setLooping(true);
		song.setVolume(0.1f);
		song.play();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuScreen(gsm, this));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	public void readScores() {
		scores.add(scoresPref.getInteger("score1"));
		scores.add(scoresPref.getInteger("score2"));
		scores.add(scoresPref.getInteger("score3"));
	}

	public void writeScores(){
		Collections.sort(scores);
		Collections.reverse(scores);
		scoresPref.putString("name", "plane racing");
		scoresPref.putInteger("score1", scores.get(0));
		scoresPref.putInteger("score2", scores.get(1));
		scoresPref.putInteger("score3", scores.get(2));

		scoresPref.flush();
	}
}
