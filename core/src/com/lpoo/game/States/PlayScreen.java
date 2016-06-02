package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
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
import com.lpoo.game.Logic.Bullet;
import com.lpoo.game.Logic.Enemy;
import com.lpoo.game.Logic.Explosion;
import com.lpoo.game.Logic.Hero;
import com.lpoo.game.PlaneRacing;
import com.lpoo.game.Scenes.Hud;
import com.lpoo.game.Sprites.AnimationExpl;

import java.io.Console;

/**
 * Created by Joao on 13-05-2016.
 */
public class PlayScreen extends State{
    private Hero hero;
    private Texture background;
    private Hud hud;
    private Array<Bullet> hero_bullets;
    private Array<Bullet> enemy_bullets;
    private Array<Explosion> explosions;
    private Array<Enemy> enemies;

    private Stage stage;
    private TextButton buttonUp;
    private TextButton buttonDown;
    private TextButton buttonLeft;
    private TextButton buttonRight;
    private TextButton buttonShot;
    BitmapFont font;
    Skin skin;
    TextureAtlas buttonAtlas;

    public PlayScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        hero = new Hero(50, PlaneRacing.HEIGHT/2);
        background = new Texture("background.png");
        hero_bullets = new Array<Bullet>();
        enemy_bullets = new Array<Bullet>();
        hud = new Hud (game.batch);
        explosions = new Array<Explosion>();
        enemies = new Array<Enemy>();

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
        //buttonShot.setPosition(PlaneRacing.WIDTH - PlaneRacing.WIDTH / 8, PlaneRacing.HEIGHT / 4 - 20);
        buttonShot.setPosition(PlaneRacing.WIDTH / 2 + 100, PlaneRacing.HEIGHT / 4 - 90);
        buttonShot.setBounds(buttonShot.getX(), buttonShot.getY(), 150, 150);

        stage.addActor(buttonUp);
        stage.addActor(buttonDown);
        stage.addActor(buttonLeft);
        stage.addActor(buttonRight);
        stage.addActor(buttonShot);
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

       /* buttonShot.addListener(new InputListener() {
            boolean touchdown;
            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                boolean touchdown = true;
               /* if (touchdown) {
                    Bullet bullet = new Bullet((int) hero.getPositionX() + 30, (int) hero.getPositionY() + 17, "H");
                    hero_bullets.add(bullet);
                    touchdown = true;
                }
                System.out.println("button released");
                //it will work when finger is released..
                Bullet bullet = new Bullet((int) hero.getPositionX() + 30, (int) hero.getPositionY() + 17, "H");
                hero_bullets.add(bullet);

            }

            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                touchdown=false;
                System.out.println("button pressed");
                //do your stuff it will work when u touched your actor
                return true;
            }

        });*/
        /*buttonShot.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                Bullet bullet = new Bullet((int) hero.getPositionX() + 30, (int) hero.getPositionY() + 17, "H");
                hero_bullets.add(bullet);
            }
        });*/

        buttonShot.addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                Bullet bullet = new Bullet((int) hero.getPositionX() + 30, (int) hero.getPositionY() + 17, "H");
                hero_bullets.add(bullet);
            }
        });

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
           Bullet bullet = new Bullet((int) hero.getPositionX() + 30,(int) hero.getPositionY()+17, "H");
            hero_bullets.add(bullet);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            Enemy enemy = new Enemy(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2);
            enemies.add(enemy);
        }
       /* if (Gdx.input.justTouched()){
            Bullet bullet = new Bullet((int) hero.getPositionX() + 30,(int) hero.getPositionY()+17, "H");
            hero_bullets.add(bullet);
        }*/
    }

    @Override
    public void update(float dt) {
        handleInput();
        Array<Bullet> temp = new Array<Bullet>();
        for (Bullet bullet : hero_bullets) {
            if (bullet.getPositionX() < PlaneRacing.WIDTH + 50)
                temp.add(bullet);
            else{
            if (enemies.size == 0)
                temp.add(bullet);
            else {
                for (Enemy en : enemies) {
                    if (bullet.colides(en.getBox())) {
                        Gdx.app.log("Bullet", "Colision");
                        Explosion exp = new Explosion((int) bullet.getPositionX(), (int) bullet.getPositionY(), 1);
                        explosions.add(exp);
                        bullet.dispose();
                        en.getHit(20);
                        if (en.getHealth() == 0) {
                            hud.incScore(100);
                            en.dispose();
                            hero_bullets.clear();
                            enemies.clear();
                            Explosion exp2 = new Explosion((int) en.getPositionX(), (int) en.getPositionY(), 3);
                            explosions.add(exp2);
                        }
                    } else
                        temp.add(bullet);
                }
                }
            }
        }
        hero_bullets.clear();
        hero_bullets = temp;
        for (Bullet bullet : hero_bullets)
            bullet.update(dt);
        for (Explosion exp : explosions)
            exp.update(dt);
        for (Enemy en : enemies)
            en.update(dt);
        hero.update(dt);
        hud.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(background, 0, 0);
            for (Bullet bullet : hero_bullets) {
                sb.draw(bullet.getTexture(), bullet.getPositionX(), bullet.getPositionY());
            }
            for (Bullet bullet : enemy_bullets){
                sb.draw(bullet.getTexture(), bullet.getPositionX(), bullet.getPositionY());
            }
            for (Explosion exp : explosions) {
                sb.draw(exp.getTexture(), exp.getPositionX(), exp.getPositionY());
            }
            for(Enemy en : enemies){
                sb.draw(en.getTexture(), en.getPositionX(), en.getPositionY(),  en.getTexture().getWidth() * 2, en.getTexture().getHeight() * 2);
            }
        sb.draw(hero.getTexture(), hero.getPositionX(), hero.getPositionY(), hero.getTexture().getWidth() * 2, hero.getTexture().getHeight() * 2);
        //System.out.println(hero.getPositionX());
        sb.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.draw();
    }

    @Override
    public void dispose() {

    }
}
