package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 05-06-2016.
 */
public class PauseScreen extends State {

    private PlayScreen playScreen;
    private Texture background;

    private OrthographicCamera cam;
    private Viewport port;

    public PauseScreen(ScreenManager gsm, PlaneRacing game, PlayScreen play) {
        super(gsm, game);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(PlaneRacing.WIDTH, PlaneRacing.HEIGHT, cam);

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
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
