package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.game.Logic.Bullet;
import com.lpoo.game.Logic.Enemy;
import com.lpoo.game.Logic.Explosion;
import com.lpoo.game.Logic.Hero;
import com.lpoo.game.PlaneRacing;
import com.lpoo.game.Scenes.Hud;
import com.lpoo.game.Sprites.AnimationExpl;

import java.io.Console;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Joao on 13-05-2016.
 */
public class PlayScreen extends State{
    private Hero hero;
    private Texture background;
    private Hud hud;
    private ArrayList<Bullet> hero_bullets;
    private ArrayList<Bullet> enemy_bullets;
    private Array<Explosion> explosions;
    private ArrayList<Enemy> enemies;
    private int count;
    private float timeCount1;
    private float timeCount2;
    int pos;
    Random rand;
    private Sound shot;
    private Sound explosion;
    private Sound getHit;
    private Music song;

    private OrthographicCamera cam;
    private Viewport port;

    private Stage stage;
    private TextButton buttonUp;
    private TextButton buttonDown;
    private TextButton buttonLeft;
    private TextButton buttonRight;
    private TextButton buttonShot;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;

    public PlayScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        hero = new Hero(50, PlaneRacing.HEIGHT/2);
        background = new Texture("background.png");
        hero_bullets = new ArrayList<Bullet>();
        enemy_bullets = new ArrayList<Bullet>();
        hud = new Hud (game.batch, hero);
        explosions = new Array<Explosion>();
        enemies = new ArrayList<Enemy>();
        count = 0;
        timeCount1 = 0;
        timeCount2 = 0;
        pos = 0;
        rand = new Random();
        song = Gdx.audio.newMusic((Gdx.files.internal("unity.mp3")));
        shot = Gdx.audio.newSound(Gdx.files.internal("laser.ogg"));
        getHit = Gdx.audio.newSound(Gdx.files.internal("getHit.ogg"));
        explosion = Gdx.audio.newSound((Gdx.files.internal("explosion.ogg")));

        song.setLooping(true);
        song.setVolume(0.05f);
        song.play();

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("Buttons.pack");
        skin.addRegions(buttonAtlas);
        stage.clear();
        TextButton.TextButtonStyle styleUp = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleDown = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleLeft = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleRight = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleShot = new TextButton.TextButtonStyle();

        styleUp.up = skin.getDrawable("arrowUp");
        styleUp.down = skin.getDrawable("arrowUp");
        styleUp.checked = skin.getDrawable("arrowUp");
        styleUp.font = font;

        styleDown.up = skin.getDrawable("arrowDown");
        styleDown.down = skin.getDrawable("arrowDown");
        styleDown.checked = skin.getDrawable("arrowDown");
        styleDown.font = font;

        styleLeft.up = skin.getDrawable("arrowLeft");
        styleLeft.down = skin.getDrawable("arrowLeft");
        styleLeft.checked = skin.getDrawable("arrowLeft");
        styleLeft.font = font;

        styleRight.up = skin.getDrawable("arrowRight");
        styleRight.down = skin.getDrawable("arrowRight");
        styleRight.checked = skin.getDrawable("arrowRight");
        styleRight.font = font;

        styleShot.up = skin.getDrawable("buttonA");
        styleShot.down = skin.getDrawable("buttonA");
        styleShot.checked = skin.getDrawable("buttonA");
        styleShot.font = font;

        buttonUp = new TextButton("", styleUp);
        buttonUp.setPosition(PlaneRacing.WIDTH / 8  - 50, PlaneRacing.HEIGHT / 4 - 20);
        buttonUp.setBounds(buttonUp.getX(), buttonUp.getY(), 100, 100);

        buttonDown = new TextButton("", styleDown);
        buttonDown.setPosition(PlaneRacing.WIDTH / 8 - 50, PlaneRacing.HEIGHT / 4 - 120);
        buttonDown.setBounds(buttonDown.getX(), buttonDown.getY(), 100, 100);

        buttonLeft = new TextButton("", styleLeft);
        buttonLeft.setPosition(PlaneRacing.WIDTH / 8  - 120, PlaneRacing.HEIGHT / 4 - 70);
        buttonLeft.setBounds(buttonLeft.getX(), buttonLeft.getY(), 100, 100);

        buttonRight = new TextButton("", styleRight);
        buttonRight.setPosition(PlaneRacing.WIDTH / 8   + 20, PlaneRacing.HEIGHT / 4 - 70);
        buttonRight.setBounds(buttonRight.getX(), buttonRight.getY(), 100, 100);

        buttonShot = new TextButton("", styleShot);
        buttonShot.setPosition(PlaneRacing.WIDTH / 2 + 100, PlaneRacing.HEIGHT / 4 - 90);
        buttonShot.setBounds(buttonShot.getX(), buttonShot.getY(), 150, 150);

        stage.addActor(buttonUp);
        stage.addActor(buttonDown);
        stage.addActor(buttonLeft);
        stage.addActor(buttonRight);
        stage.addActor(buttonShot);

        buttonShot.addListener(new InputListener(){
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
        hero.handleInput();
        if (buttonUp.isPressed())
            hero.moveUp();

        if (buttonDown.isPressed())
            hero.moveDown();

        if (buttonLeft.isPressed())
            hero.moveLeft();

        if (buttonRight.isPressed())
            hero.moveRight();

        if (count != 0) {
            Bullet bullet = new Bullet((int) hero.getPositionX() + 30, (int) hero.getPositionY() + 17, "H");
            hero_bullets.add(bullet);
            shot.play(0.05f);
        }

        count = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
           Bullet bullet = new Bullet((int) hero.getPositionX() + 30,(int) hero.getPositionY()+17, "H");
            hero_bullets.add(bullet);
            shot.play(0.05f);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            Enemy enemy = new Enemy(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2);
            enemies.add(enemy);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        timeCount1 += dt;
        timeCount2 += dt;
        if (timeCount1 > 3){
            int y_pos = rand.nextInt(PlaneRacing.HEIGHT - 60);
            Enemy enemy = new Enemy(PlaneRacing.WIDTH, y_pos);
            enemies.add(enemy);
            timeCount1 = 0;
            pos += 100;
        }

        if (timeCount2 > 2){
            for (Enemy en : enemies) {
                Bullet b = new Bullet((int) en.getPositionX() - 30, (int) en.getPositionY() + 17, "E");
                enemy_bullets.add(b);
            }
            timeCount2 = 0;
        }

        for (int j = 0; j < hero_bullets.size(); j++) {
            if (hero_bullets.get(j).getPositionX() > PlaneRacing.WIDTH + 50){
                hero_bullets.get(j).dispose();
                hero_bullets.remove(j);
                if (hero_bullets.size() > 1 && j > 0){}
            }
            else{
                if (enemies.size() == 0){}
                else {
                    for (int i = 0; i < enemies.size(); i++) {
                        if (hero_bullets.size() == 0)
                            break;
                        if(j < hero_bullets.size() && i < enemies.size()) {
                            if (hero_bullets.get(j).colides(enemies.get(i).getBox())) {
                                Gdx.app.log("Bullet", "Collision");
                                Explosion exp = new Explosion((int) hero_bullets.get(j).getPositionX(), (int) hero_bullets.get(j).getPositionY(), 1);
                                explosions.add(exp);
                                hero_bullets.get(j).dispose();
                                hero_bullets.remove(j);
                                enemies.get(i).getHit(20);
                                if (enemies.get(i).getHealth() == 0) {
                                    explosion.play(0.1f);
                                    hud.incScore(100);
                                    enemies.get(i).dispose();
                                    enemies.remove(i);
                                    if (i > 0 && enemies.size() > 1) {}
                                    else
                                        break;
                                    Explosion exp2 = new Explosion((int) enemies.get(i).getPositionX(), (int) enemies.get(i).getPositionY(), 3);
                                    explosions.add(exp2);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int j = 0; j < enemy_bullets.size(); j++){
            if (enemy_bullets.get(j).colides(hero.getBox())){
                Gdx.app.log("Collision", "Hero");
                getHit.play(0.1f);
                Explosion exp = new Explosion((int) enemy_bullets.get(j).getPositionX(), (int) enemy_bullets.get(j).getPositionY(), 1);
                explosions.add(exp);
                enemy_bullets.get(j).dispose();
                enemy_bullets.remove(j);
                hero.getHit(10);

            }
        }

        for (Bullet bullet : hero_bullets)
            bullet.update(dt);
        for (Bullet bullet : enemy_bullets)
            bullet.update(dt);
        for (Explosion exp : explosions)
            exp.update(dt);
        for (int i = 0; i < enemies.size(); i++){
           if (enemies.get(i).getPositionX() < 0) {
               hero.getHit(10);
               enemies.remove(i);
           }
        }
        for(Enemy en : enemies)
        en.update(dt);

        hud.update(dt);

        if (hero.getHealth() <= 0) {
            gsm.set(new MenuScreen(gsm, game));
            dispose();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0);
            for (Explosion exp : explosions) {
                sb.draw(exp.getTexture(), exp.getPositionX(), exp.getPositionY());
            }
            for(Enemy en : enemies){
                sb.draw(en.getTexture(), en.getPositionX(), en.getPositionY(),  en.getTexture().getWidth() * 2, en.getTexture().getHeight() * 2);
            }
            for (Bullet bullet : hero_bullets) {
                sb.draw(bullet.getTexture(), bullet.getPositionX(), bullet.getPositionY());
            }
            for (Bullet bullet : enemy_bullets){
                TextureRegion t = bullet.getTexture();
                t.flip(true, false);
                sb.draw(t, bullet.getPositionX(), bullet.getPositionY());
            }
        sb.draw(hero.getTexture(), hero.getPositionX(), hero.getPositionY(), hero.getTexture().getWidth() * 2, hero.getTexture().getHeight() * 2);
        sb.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.draw();
    }

    @Override
    public void dispose() {
        for (Enemy en : enemies)
            en.dispose();
        for (Bullet hb : hero_bullets)
            hb.dispose();
        for (Bullet hb : enemy_bullets)
            hb.dispose();
        for (Explosion exp : explosions)
            exp.dispose();

        hero.dispose();
        background.dispose();
        stage.dispose();
        skin.dispose();
        font.dispose();
        buttonAtlas.dispose();
        shot.dispose();
        song.dispose();
    }
}
