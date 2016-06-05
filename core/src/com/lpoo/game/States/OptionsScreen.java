package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 05-06-2016.
 */
public class OptionsScreen extends State {

    private TextButton sound;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private Stage stage;
    private boolean on;
    TextButton.TextButtonStyle style;

    protected OptionsScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);

        stage = new Stage();
        on = true;

        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("Buttons.pack");
        skin.addRegions(buttonAtlas);
        stage.clear();

        style = new TextButton.TextButtonStyle();
        style.down = skin.getDrawable("musicOn");
        style.up = skin.getDrawable("musicOn");

        sound = new TextButton("", style);
        sound.setPosition(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2);

        stage.addActor(sound);
    }

    @Override
    public void handleInput() {
        if (sound.isChecked()){
            if (on) {
                on = false;
                style.checked = skin.getDrawable("musicOff");
                sound.setStyle(style);
            }
            else{
                on = true;
                style.checked = skin.getDrawable("musicOn");
                sound.setStyle(style);
            }
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        stage.draw();
        sb.end();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
