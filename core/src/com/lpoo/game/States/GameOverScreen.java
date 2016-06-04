package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 04-06-2016.
 */
public class GameOverScreen extends State {

    Texture background;

    public GameOverScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        background = new Texture("gameover.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            gsm.set(new MenuScreen(gsm, game));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, PlaneRacing.WIDTH, PlaneRacing.HEIGHT);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
