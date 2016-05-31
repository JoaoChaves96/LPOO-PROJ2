package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.lpoo.game.Logic.Bullet;
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
    private Rectangle box;
    private Array<Explosion> explosions;

    public PlayScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);
        hero = new Hero(50, PlaneRacing.HEIGHT/2);
        background = new Texture("background.png");
        box = new Rectangle(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2, 100, 100);
        bullets = new Array<Bullet>();
        hud = new Hud (game.batch);
        box.setPosition(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2);
        explosions = new Array<Explosion>();
    }

    @Override
    public void handleInput() {
        hero.handleInput();
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
           Bullet bullet = new Bullet((int) hero.getPositionX() + 30,(int) hero.getPositionY()+17);
            bullets.add(bullet);
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        Array<Bullet> temp = new Array<Bullet>();
        for (Bullet bullet : bullets) {
            if(bullet.colides(box)) {
                Gdx.app.log("Bullet", "Colision");
                Explosion exp = new Explosion((int)bullet.getPositionX(),(int) bullet.getPositionY());
                explosions.add(exp);
                bullet.dispose();
            }
            else
                temp.add(bullet);
        }
        bullets.clear();
        bullets = temp;
        for (Bullet bullet : bullets)
            bullet.update(dt);
        for (Explosion exp : explosions)
            exp.update(dt);
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
        sb.draw(hero.getTexture(), hero.getPositionX(), hero.getPositionY(), hero.getTexture().getWidth() * 2, hero.getTexture().getHeight() * 2);
        sb.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void dispose() {

    }
}
