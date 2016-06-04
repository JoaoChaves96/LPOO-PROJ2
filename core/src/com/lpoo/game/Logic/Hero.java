package com.lpoo.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.lpoo.game.PlaneRacing;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Joao on 13-05-2016.
 */
public class Hero {
    private Vector2 position;
    private Texture hero;
    private int health;
    private Rectangle box;

    public Hero(int x, int y){
        position = new Vector2(x, y);
        hero = new Texture("hero.png");
        box = new Rectangle(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2, hero.getWidth(), hero.getHeight() + 10);
        box.setPosition(x + 23, y + 10);
        health = 100;
    }

    public void handleInput(){
       if(Gdx.input.isKeyPressed(Input.Keys.W)){
           moveUp();
       }
       if (Gdx.input.isKeyPressed(Input.Keys.S)){
           moveDown();
       }
       if (Gdx.input.isKeyPressed(Input.Keys.A)){
           moveLeft();
       }
       if (Gdx.input.isKeyPressed(Input.Keys.D)){
           moveRight();
       }
    }

    public void moveUp() {
        if (position.y + 5 <= PlaneRacing.HEIGHT - hero.getHeight() - 30) {
            position.add(0, 5);
            box.setPosition(position.x, position.y + 5);
        }
    }

    public void moveDown(){
        if (position.y >= 0) {
            position.add(0, -5);
            box.setPosition(position.x + 23, position.y + 10);
        }
    }

    public void moveLeft(){
        if (position.x >= 0) {
            position.add(-5, 0);
            box.setPosition(position.x + 23, position.y + 10);
        }
    }

    public void moveRight(){
        if(position.x + 5 <= PlaneRacing.WIDTH - hero.getWidth() - 30) {
            position.add(5, 0);
            box.setPosition(position.x + 23, position.y + 10);
        }
    }

    public void update(float dt){
        handleInput();
    }

    public Texture getTexture(){
        return hero;
    }

    public float getPositionX(){
        return position.x;
    }
    public float getPositionY(){
        return position.y;
    }

    public Rectangle getBox(){
        return box;
    }

    public void getHit(int hp){
        health -= hp;
    }

    public int getHealth(){
        return health;
    }

    public void dispose(){
        hero.dispose();
        box.setWidth(0);
        box.setHeight(0);
        box = null;
    }
}
