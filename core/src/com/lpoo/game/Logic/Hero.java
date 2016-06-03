package com.lpoo.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public Hero(int x, int y){
        position = new Vector2(x, y);
        hero = new Texture("hero.png");
    }

    public void handleInput(){
       if(Gdx.input.isKeyPressed(Input.Keys.W)){
          if(position.y + 5 <= PlaneRacing.HEIGHT - hero.getHeight() - 30)
           position.add(0, 5);
       }
       if (Gdx.input.isKeyPressed(Input.Keys.S)){
           if (position.y >= 0)
            position.add(0, -5);
       }
       if (Gdx.input.isKeyPressed(Input.Keys.A)){
           if (position.x >= 0)
               position.add(-5, 0);
       }
       if (Gdx.input.isKeyPressed(Input.Keys.D)){
           if(position.x + 5 <= PlaneRacing.WIDTH - hero.getWidth() - 30)
               position.add(5, 0);
       }
    }

    public void moveUp(){
        if(position.y + 5 <= PlaneRacing.HEIGHT - hero.getHeight() - 30)
            position.add(0, 5);
    }

    public void moveDown(){
        if (position.y >= 0)
            position.add(0, -5);
    }

    public void moveLeft(){
        if (position.x >= 0)
            position.add(-5, 0);
    }

    public void moveRight(){
        if(position.x + 5 <= PlaneRacing.WIDTH - hero.getWidth() - 30)
            position.add(5, 0);
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
}
