package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
 * Created by Joao on 05-06-2016.
 */
public class PauseScreen extends State {

    private PlayScreen playScreen;
    private Texture background;

    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private TextButton resume;
    private TextButton home;

    private OrthographicCamera cam;
    private Viewport port;
    public Stage stage;
    private int count;

    public PauseScreen(ScreenManager gsm, PlaneRacing game, PlayScreen play) {
        super(gsm, game);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(PlaneRacing.WIDTH, PlaneRacing.HEIGHT, cam);

        stage = new Stage();

        count = 0;

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("Buttons.pack");
        skin.addRegions(buttonAtlas);
        TextButton.TextButtonStyle resStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle homeStyle = new TextButton.TextButtonStyle();

        resStyle.up = skin.getDrawable("resume");
        resStyle.down = skin.getDrawable("resume");
        resStyle.font = font;

        homeStyle.up = skin.getDrawable("homeblue");
        homeStyle.down = skin.getDrawable("homeblue");
        homeStyle.font = font;

        resume = new TextButton("", resStyle);
        resume.setPosition(PlaneRacing.WIDTH / 6 - 100, PlaneRacing.HEIGHT / 2 - 50);

        home = new TextButton("", homeStyle);
        home.setPosition(PlaneRacing.WIDTH/4 + 300, PlaneRacing.HEIGHT / 2 - 20);


        stage.addActor(resume);
        stage.addActor(home);

        playScreen = play;
        background = new Texture("options_background.png");

        resume.addListener(new InputListener(){
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
        if (count > 0){
            Gdx.input.setInputProcessor(playScreen.stage);
            gsm.set(playScreen);
            count = 0;
        }

        if (home.isPressed()){
            dispose();
            gsm.set(new MenuScreen(gsm, game));
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

        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
