package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
public class PlayScreen extends State {
    private Hero hero;
    private Texture background;
    private Hud hud;
    private Array<Bullet> bullets;
   // private Rectangle box;
    private Array<Explosion> explosions;
    private Array<Enemy> enemies;

    public PlayScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        hero = new Hero(50, PlaneRacing.HEIGHT/2);
        background = new Texture("background.png");
       // box = new Rectangle(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2, 100, 100);
        bullets = new Array<Bullet>();
        hud = new Hud (game.batch);
       // box.setPosition(PlaneRacing.WIDTH / 2 + 20, PlaneRacing.HEIGHT / 2);
        explosions = new Array<Explosion>();
        enemies = new Array<Enemy>();
    }

    @Override
    public void handleInput() {
        hero.handleInput();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
           Bullet bullet = new Bullet((int) hero.getPositionX() + 30,(int) hero.getPositionY()+17);
            bullets.add(bullet);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            Enemy enemy = new Enemy(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2);
            enemies.add(enemy);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        Array<Bullet> temp = new Array<Bullet>();
        for (Bullet bullet : bullets) {
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
                            bullets.clear();
                            enemies.clear();
                            Explosion exp2 = new Explosion((int) en.getPositionX(), (int) en.getPositionY(), 3);
                            explosions.add(exp2);
                        }
                    } else
                        temp.add(bullet);
                }
            }
        }
        bullets.clear();
        bullets = temp;
        for (Bullet bullet : bullets)
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
            for (Bullet bullet : bullets) {
                sb.draw(bullet.getTexture(), bullet.getPositionX(), bullet.getPositionY());
            }
            for (Explosion exp : explosions) {
                sb.draw(exp.getTexture(), exp.getPositionX(), exp.getPositionY());
            }
            for(Enemy en : enemies){
                sb.draw(en.getTexture(), en.getPositionX(), en.getPositionY(),  en.getTexture().getWidth() * 2, en.getTexture().getHeight() * 2);
            }
        sb.draw(hero.getTexture(), hero.getPositionX(), hero.getPositionY(), hero.getTexture().getWidth() * 2, hero.getTexture().getHeight() * 2);
        sb.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void dispose() {

    }
}
