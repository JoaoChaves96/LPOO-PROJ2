package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 05-06-2016.
 */
public class PauseScreen extends State {

    private PlayScreen playScreen;
    private Texture background;

    protected PauseScreen(ScreenManager gsm, PlaneRacing game, PlayScreen play) {
        super(gsm, game);
        playScreen = play;
        background = new Texture("background.png");
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()){
            dispose();
            Gdx.input.setInputProcessor(playScreen.stage);
            gsm.set(playScreen);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
