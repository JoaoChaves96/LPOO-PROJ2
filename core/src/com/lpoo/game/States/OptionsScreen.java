package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 05-06-2016.
 */
public class OptionsScreen extends State {

    private Texture background;

    private TextButton sound;
    private TextButton l1;
    private TextButton l2;
    private TextButton l3;
    private TextButton exit;
    private TextButton hb;
    private TextButton hg;
    private TextButton ho;

    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;

    private Stage stage;

    private TextButton.TextButtonStyle styleSound;
    private TextButton.TextButtonStyle style1;
    private TextButton.TextButtonStyle style2;
    private TextButton.TextButtonStyle style3;
    private TextButton.TextButtonStyle styleexit;
    private TextButton.TextButtonStyle styleB;
    private TextButton.TextButtonStyle styleG;
    private TextButton.TextButtonStyle styleO;

    private OrthographicCamera cam;
    private Viewport port;

    public OptionsScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);

        background = new Texture("options_background.png");

        cam = new OrthographicCamera();
        port = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);

        stage = new Stage(port, game.batch);

        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("buttons.pack");
        skin.addRegions(buttonAtlas);
        stage.clear();

        styleSound = new TextButton.TextButtonStyle();
        styleSound.down = skin.getDrawable("musicOn");
        styleSound.up = skin.getDrawable("musicOn");
        styleSound.font = font;

        style1 = new TextButton.TextButtonStyle();
        style1.up = skin.getDrawable("siganl1");
        style1.down = skin.getDrawable("siganl1");
        style1.checked = skin.getDrawable("siganl1_c");
        style1.font = font;

        style2 = new TextButton.TextButtonStyle();
        style2.up = skin.getDrawable("signal2");
        style2.down = skin.getDrawable("signal2");
        style2.checked = skin.getDrawable("signal2_c");
        style2.font = font;

        style3 = new TextButton.TextButtonStyle();
        style3.up = skin.getDrawable("signal3");
        style3.down = skin.getDrawable("signal3");
        style3.checked = skin.getDrawable("signal3_c");
        style3.font = font;

        styleexit = new TextButton.TextButtonStyle();
        styleexit.up = skin.getDrawable("home");
        styleexit.down = skin.getDrawable("home");
        styleexit.font = font;

        styleB = new TextButton.TextButtonStyle();
        styleB.up = skin.getDrawable("heroB");
        styleB.down = skin.getDrawable("heroB");
        styleB.checked = skin.getDrawable("heroBS");
        styleB.font = font;

        styleO = new TextButton.TextButtonStyle();
        styleO.up = skin.getDrawable("heroO");
        styleO.down = skin.getDrawable("heroO");
        styleO.checked = skin.getDrawable("heroOS");
        styleO.font = font;

        styleG = new TextButton.TextButtonStyle();
        styleG.up = skin.getDrawable("heroG");
        styleG.down = skin.getDrawable("heroG");
        styleG.checked = skin.getDrawable("heroGS");
        styleG.font = font;

        sound = new TextButton("", styleSound);
        sound.setPosition(PlaneRacing.WIDTH / 9 - 100, PlaneRacing.HEIGHT / 2);
        sound.setBounds(sound.getX(), sound.getY() + 50, 150, 150);

        l1 = new TextButton("", style1);
        l1.setPosition(PlaneRacing.WIDTH / 4 + 25, PlaneRacing.HEIGHT / 2);
        l1.setBounds(l1.getX() + 70, l1.getY() + 50, 150, 150);

        l2 = new TextButton("", style2);
        l2.setPosition(PlaneRacing.WIDTH / 4 + 150, PlaneRacing.HEIGHT / 2);
        l2.setBounds(l2.getX() + 70, l2.getY() + 50, 150, 150);

        l3 = new TextButton("", style3);
        l3.setPosition(PlaneRacing.WIDTH / 4 + 275, PlaneRacing.HEIGHT / 2);
        l3.setBounds(l3.getX() + 70, l3.getY() + 50, 150, 150);

        hb = new TextButton("", styleB);
        hb.setPosition(PlaneRacing.WIDTH / 4 - 150, PlaneRacing.HEIGHT / 2 - 150);
        hb.setBounds(hb.getX(), hb.getY() + 25, 100, 100);

        ho = new TextButton("", styleO);
        ho.setPosition(PlaneRacing.WIDTH / 4 - 150, PlaneRacing.HEIGHT / 2 - 150);
        ho.setBounds(ho.getX() + 100 + hb.getWidth(), ho.getY()+ 25, 100, 100);

        hg = new TextButton("", styleG);
        hg.setPosition(PlaneRacing.WIDTH / 4 - 150, PlaneRacing.HEIGHT / 2 - 150);
        hg.setBounds(hg.getX() + 100 + ho.getWidth() + 100 + hb.getWidth(), hg.getY() + 25, 100, 100);

        exit = new TextButton("", styleexit);
        exit.setPosition(PlaneRacing.WIDTH / 2 + 200, 10);


        stage.addActor(sound);
        stage.addActor(l1);
        stage.addActor(l2);
        stage.addActor(l3);
        stage.addActor(hb);
        stage.addActor(ho);
        stage.addActor(hg);
        stage.addActor(exit);

        switch (game.dif){
            case 1:
                l1.setChecked(true);
                break;
            case 2:
                l2.setChecked(true);
                break;
            case 3:
                l3.setChecked(true);
                break;
        }

        if (game.on)
            sound.setChecked(false);
        else
            sound.setChecked(true);

        switch(game.hero){
            case 1:
                hb.setChecked(true);
                break;
            case 2:
                ho.setChecked(true);
                break;
            case 3:
                hg.setChecked(true);
                break;
        }
    }

    @Override
    public void handleInput() {
        if (sound.isChecked()){
            styleSound.checked = skin.getDrawable("musicOff");
            sound.setStyle(styleSound);
            game.song.setVolume(0);
            game.on = false;
        }
        else{
            styleSound.checked = skin.getDrawable("musicOn");
            sound.setStyle(styleSound);
            game.song.setVolume(0.1f);
            game.on = true;
        }


        if (l2.isPressed()){
            l1.setChecked(false);
            l3.setChecked(false);
            l2.setChecked(true);
            game.dif = 2;
        }

        else if (l1.isPressed()){
            l2.setChecked(false);
            l3.setChecked(false);
            l1.setChecked(true);
            game.dif = 1;
        }

        if (l3.isPressed()) {
            l1.setChecked(false);
            l2.setChecked(false);
            l3.setChecked(true);
            game.dif = 3;
        }

        if (ho.isPressed()){
            hb.setChecked(false);
            hg.setChecked(false);
            ho.setChecked(true);
            game.hero = 2;
        }

        else if (hb.isPressed()){
            ho.setChecked(false);
            hg.setChecked(false);
            hb.setChecked(true);
            game.hero = 1;
        }

        if (hg.isPressed()) {
            hb.setChecked(false);
            ho.setChecked(false);
            hg.setChecked(true);
            game.hero = 3;
        }

        if (exit.isPressed()) {
            dispose();
            gsm.set(new MenuScreen(gsm, game));
        }
    }

    @Override
    public void update(float dt) { handleInput();
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
    }
}
