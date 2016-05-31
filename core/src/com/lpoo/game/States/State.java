package com.lpoo.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 13-05-2016.
 */
public abstract class State {
   protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected ScreenManager gsm;
    protected PlaneRacing game;

    protected State(ScreenManager gsm, PlaneRacing game){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
        this.game = game;
    }

    public abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
