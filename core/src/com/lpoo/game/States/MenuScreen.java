package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 13-05-2016.
 */
public class MenuScreen extends State {
    private Texture playBtn;
    private Texture background;

    public MenuScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        playBtn = new Texture("playBtn.png");
        background = new Texture("menu_background.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayScreen(gsm, game));
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
        sb.draw(playBtn, (PlaneRacing.WIDTH / 10), (PlaneRacing.HEIGHT / 2)-(playBtn.getHeight()/2));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
