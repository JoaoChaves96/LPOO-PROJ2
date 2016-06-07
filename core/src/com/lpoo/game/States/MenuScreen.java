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
    private TextButton highscoresBtn;
    private TextButton exitBtn;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private OrthographicCamera cam;
    private Viewport port;

    private int count;
    private int count2;
    private int count3;

    public MenuScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
        background = new Texture("menu_background.png");

        stage = new Stage(port, game.batch);

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("buttons.pack");
        skin.addRegions(buttonAtlas);
        stage.clear();

        count = 0;
        count2 = 0;
        count3 = 0;

        TextButton.TextButtonStyle play = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleOptions = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleScore = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle exitScore = new TextButton.TextButtonStyle();

        play.up = skin.getDrawable("playBtn");
        play.down = skin.getDrawable("playBtnDown");
        play.checked = skin.getDrawable("playBtn");
        play.font = font;

        styleOptions.up = skin.getDrawable("options");
        styleOptions.down = skin.getDrawable("optionsDown");
        styleOptions.font = font;

        styleScore.up = skin.getDrawable("highscores");
        styleScore.down = skin.getDrawable("highscoresDown");
        styleScore.font = font;

        exitScore.up = skin.getDrawable("exitblue");
        exitScore.down = skin.getDrawable("exitblue");
        exitScore.font = font;

        playBtn = new TextButton("", play);
        playBtn.setPosition(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 6);

        options = new TextButton("", styleOptions);
        options.setPosition(Gdx.graphics.getWidth() / 10 + 22, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 6 - 150);

        highscoresBtn = new TextButton("", styleScore);
        highscoresBtn.setPosition(Gdx.graphics.getWidth() / 10 + 22, Gdx.graphics.getHeight() / 2 + Gdx.graphics.getHeight() / 6 - 300);

        exitBtn = new TextButton("", exitScore);
        exitBtn.setPosition(PlaneRacing.WIDTH / 2 + 200, 0);

        stage.addActor(playBtn);
        stage.addActor(options);
        stage.addActor(highscoresBtn);
        stage.addActor(exitBtn);



        playBtn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                count++;
            }
        });

        options.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                count2++;
            }
        });

        highscoresBtn.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                count3++;
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

        if (count2 > 0){
            dispose();
            count2 = 0;
            gsm.push(new OptionsScreen(gsm, game));
        }

        if (count3 > 0){
            dispose();
            count3 = 0;
            gsm.push(new HighScoreScreen(gsm, game));
        }

        if (exitBtn.isPressed()){
            dispose();
            System.exit(0);
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
        background.dispose();
        stage.dispose();
        font.dispose();
        skin.dispose();
        buttonAtlas.dispose();
    }
}
