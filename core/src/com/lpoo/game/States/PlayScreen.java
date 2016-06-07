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
import com.badlogic.gdx.math.Plane;
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
    private ArrayList<Explosion> explosions;
    private ArrayList<Enemy> enemies;
    private int count;
    private int count2;
    private float timeCount1;
    private float timeCount2;
    private int enemy_spawner;
    Random rand;
    private Sound shot;
    private Sound explosion;
    private Sound getHit;

    private OrthographicCamera cam;
    private Viewport port;

    public Stage stage;
    private TextButton buttonUp;
    private TextButton buttonDown;
    private TextButton buttonLeft;
    private TextButton buttonRight;
    private TextButton buttonShot;
    private TextButton buttonPause;
    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;

    public PlayScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);

        cam = new OrthographicCamera();
        cam.setToOrtho(false);
        port = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);

        hero = new Hero(50, Gdx.graphics.getHeight() / 2, game.hero);
        background = new Texture("background.png");
        hero_bullets = new ArrayList<Bullet>();
        enemy_bullets = new ArrayList<Bullet>();
        hud = new Hud (game.batch, hero);
        explosions = new ArrayList<Explosion>();
        enemies = new ArrayList<Enemy>();
        count = 0;
        count2 = 0;
        timeCount1 = 0;
        timeCount2 = 0;

        switch(game.dif){
            case  1:
                enemy_spawner = 3;
                break;
            case 2:
                enemy_spawner = 2;
                break;
            case 3:
                enemy_spawner = 1;
                break;
        }

        rand = new Random();

        shot = Gdx.audio.newSound(Gdx.files.internal("laser.ogg"));
        getHit = Gdx.audio.newSound(Gdx.files.internal("gethit.ogg"));
        explosion = Gdx.audio.newSound((Gdx.files.internal("explosion.ogg")));

        stage = new Stage(port, game.batch);

        Gdx.input.setInputProcessor(stage);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("buttons.pack");
        skin.addRegions(buttonAtlas);

        stage.clear();

        TextButton.TextButtonStyle styleUp = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleDown = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleLeft = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleRight = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle styleShot = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle stylePause = new TextButton.TextButtonStyle();

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

        stylePause.up = skin.getDrawable("pause");
        stylePause.down = skin.getDrawable("pause");
        stylePause.font = font;

        buttonUp = new TextButton("", styleUp);
        buttonUp.setPosition(Gdx.graphics.getWidth() / 8  - 50, Gdx.graphics.getHeight() / 4 - 20);
        buttonUp.setBounds(buttonUp.getX(), buttonUp.getY(), 100, 100);

        buttonDown = new TextButton("", styleDown);
        buttonDown.setPosition(Gdx.graphics.getWidth() / 8 - 50, Gdx.graphics.getHeight() / 4 - 120);
        buttonDown.setBounds(buttonDown.getX(), buttonDown.getY(), 100, 100);

        buttonLeft = new TextButton("", styleLeft);
        buttonLeft.setPosition(Gdx.graphics.getWidth() / 8  - 120, Gdx.graphics.getHeight() / 4 - 70);
        buttonLeft.setBounds(buttonLeft.getX(), buttonLeft.getY(), 100, 100);

        buttonRight = new TextButton("", styleRight);
        buttonRight.setPosition(Gdx.graphics.getWidth() / 8   + 20, Gdx.graphics.getHeight() / 4 - 70);
        buttonRight.setBounds(buttonRight.getX(), buttonRight.getY(), 100, 100);

        buttonShot = new TextButton("", styleShot);
        buttonShot.setPosition(Gdx.graphics.getWidth() / 2 + 200, Gdx.graphics.getHeight() / 4 - 90);
        buttonShot.setBounds(buttonShot.getX(), buttonShot.getY(), 150, 150);

        buttonPause = new TextButton("", stylePause);
        buttonPause.setPosition(Gdx.graphics.getWidth() / 2 + 350, Gdx.graphics.getHeight() - 50);
        buttonPause.setBounds(buttonPause.getX(), buttonPause.getY(), 50, 50);

        stage.addActor(buttonUp);
        stage.addActor(buttonDown);
        stage.addActor(buttonLeft);
        stage.addActor(buttonRight);
        stage.addActor(buttonShot);
        stage.addActor(buttonPause);

        buttonShot.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                count++;
            }
        });

        buttonPause.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                count2++;
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

        if (count2 > 0) {
            count2 = 0;
            gsm.set(new PauseScreen(gsm, game, this));

        }

        if (count != 0) {
            Bullet bullet = new Bullet((int) hero.getPositionX() + 30, (int) hero.getPositionY() + 17, "H");
            hero_bullets.add(bullet);
            if (game.on) shot.play(0.05f);
        }

        count = 0;

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
           Bullet bullet = new Bullet((int) hero.getPositionX() + 30,(int) hero.getPositionY()+17, "H");
            hero_bullets.add(bullet);
           if (game.on) shot.play(0.05f);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        timeCount1 += dt;
        timeCount2 += dt;
        if (timeCount1 > enemy_spawner){
                int y_pos = rand.nextInt(Gdx.graphics.getHeight() - 60);
                Enemy enemy = new Enemy(Gdx.graphics.getWidth(), y_pos);
                enemies.add(enemy);
                timeCount1 = 0;
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
                                enemies.get(i).getHit(40);
                                if (enemies.get(i).getHealth() <= 0) {
                                    if (game.on) explosion.play(0.1f);
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
                Gdx.app.log("Collision","Hero");
                if (game.on) getHit.play(0.1f);
                Gdx.input.vibrate(50);
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

        for (int i = 0; i<explosions.size(); i++){
            if (explosions.get(i).isDead()) {
                explosions.get(i).dispose();
                explosions.remove(i);
            }
            else
                explosions.get(i).update(dt);
        }

        for (int i = 0; i < enemies.size(); i++){
           if (enemies.get(i).getPositionX() < 0) {
               Gdx.input.vibrate(50);
               hero.getHit(10);
               enemies.remove(i);
           }
        }
        for(Enemy en : enemies)
        en.update(dt);

        hud.update(dt);

        if (collision()) {
            gsm.set(new GameOverScreen(gsm, game, hud.getScore()));
            dispose();
        }

        if (hero.isDead()) {
            gsm.set(new GameOverScreen(gsm, game, hud.getScore()));
            dispose();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0,  Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            for (Explosion exp : explosions) {
                if (exp.getSize() == 3)
                    sb.draw(exp.getTexture(), exp.getPositionX(), exp.getPositionY(), hero.getTexture().getWidth(), hero.getTexture().getHeight());
                else
                    sb.draw(exp.getTexture(), exp.getPositionX(), exp.getPositionY());
            }
            for(Enemy en : enemies){
                sb.draw(en.getTexture(), en.getPositionX(), en.getPositionY(),  en.getTexture().getWidth() * 2, en.getTexture().getHeight() * 2);
            }
            for (Bullet bullet : hero_bullets) {
                sb.draw(bullet.getTexture(), bullet.getPositionX(), bullet.getPositionY());
            }
            for (Bullet bullet : enemy_bullets){
                sb.draw(bullet.getTexture(), bullet.getPositionX(), bullet.getPositionY());
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
    }

    public boolean collision() {

        boolean tmp = false;
        for(Enemy en : enemies)
        {
            if (en.getBox().overlaps(hero.getBox()))
            {
                tmp = true;
            }
        }
        return tmp;
    }
}
