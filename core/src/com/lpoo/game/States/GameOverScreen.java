package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 04-06-2016.
 */
public class GameOverScreen extends State {

    private Texture background;
    private BitmapFont font;

    private OrthographicCamera cam;
    private Viewport port;

    private int scoreInt;
    String text;


    public GameOverScreen(ScreenManager gsm, PlaneRacing game, int scor) {
        super(gsm, game);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(PlaneRacing.WIDTH, PlaneRacing.HEIGHT, cam);

        background = new Texture("gameover.png");
        font = new BitmapFont();
        scoreInt = scor;
        text = "YOUR SCORE WAS: " + scoreInt;
        font.setColor(Color.RED);
        font.getData().setScale(2, 2);
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
        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(sb, text, Gdx.graphics.getWidth()/2 - 150, Gdx.graphics.getHeight() / 2 - Gdx.graphics.getHeight()/4 + 50);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
    }
}
