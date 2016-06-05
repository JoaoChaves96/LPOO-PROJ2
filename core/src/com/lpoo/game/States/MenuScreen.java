package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 13-05-2016.
 */
public class MenuScreen extends State {
    private Texture background;
    private Stage stage;
    private TextButton playBtn;
    private TextButton options;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private OrthographicCamera cam;
    private Viewport port;

    private int count;

    public MenuScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        background = new Texture("menu_background.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("Buttons.pack");
        skin.addRegions(buttonAtlas);
        stage.clear();

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);

        count = 0;

        TextButton.TextButtonStyle play = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleOptions = new TextButton.TextButtonStyle();

        play.up = skin.getDrawable("playBtn");
        play.down = skin.getDrawable("playBtn");
        play.checked = skin.getDrawable("playBtn");
        play.font = font;

        styleOptions.up = skin.getDrawable("wrench");
        styleOptions.down = skin.getDrawable("wrench");
        styleOptions.font = font;

        playBtn = new TextButton("", play);
        playBtn.setPosition(PlaneRacing.WIDTH / 10, PlaneRacing.HEIGHT / 2 + PlaneRacing.HEIGHT / 6);

        options = new TextButton("", styleOptions);
        options.setPosition(PlaneRacing.WIDTH / 4 - 200, 50);

        stage.addActor(playBtn);
        stage.addActor(options);

        playBtn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                count++;
            }
        });
    }

    @Override
    public void handleInput() {
        if(count > 0){
            dispose();
            gsm.push(new PlayScreen(gsm, game));
            count = 0;
        }

        if (options.isPressed()){
            dispose();
            gsm.set(new OptionsScreen(gsm, game));
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0, PlaneRacing.WIDTH, PlaneRacing.HEIGHT);//, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        stage.dispose();
        font.dispose();
        skin.dispose();
        buttonAtlas.dispose();
    }
}
