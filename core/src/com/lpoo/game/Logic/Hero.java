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
import java.util.ArrayList;

/**
 * Created by Joao on 13-05-2016.
 */
public class Hero {
    private Vector2 position;
    private Texture hero;
    private int health;
    private Rectangle box;
    private boolean dead;

    /**
     * Constructor of the Hero
     * @param x Position in x of the hero
     * @param y Position in y of the hero
     * @param type type of hero
     */
    public Hero(int x, int y, int type){
        position = new Vector2(x, y);
        String s = "";
        switch(type){
            case 1:
                s= "hero.png";
                break;
            case 2:
                s = "heroorange.png";
                break;
            case 3:
                s = "herogreen.png";
        }
        hero = new Texture(Gdx.files.internal(s));
        box = new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, hero.getWidth(), hero.getHeight() + 10);
        box.setPosition(x + 23, y + 10);
        health = 100;
        dead = false;
    }

    /**
     Input handled
     */
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

    /**
     * Moves the hero up
     */
    public void moveUp() {
        if (position.y + 5 <= PlaneRacing.HEIGHT - hero.getHeight() - 30) {
            position.add(0, 5);
            box.setPosition(position.x, position.y + 5);
        }
    }

    /**
     * Moves the hero down
     */
    public void moveDown(){
        if (position.y >= 0) {
            position.add(0, -5);
            box.setPosition(position.x + 23, position.y + 10);
        }
    }

    /**
     * Moves the hero left
     */
    public void moveLeft(){
        if (position.x >= 0) {
            position.add(-5, 0);
            box.setPosition(position.x + 23, position.y + 10);
        }
    }

    /**
     * Moves the hero right
     */
    public void moveRight(){
        if(position.x + 5 <= PlaneRacing.WIDTH - hero.getWidth() - 30) {
            position.add(5, 0);
            box.setPosition(position.x + 23, position.y + 10);
        }
    }

    /**
     * Update the handle input
     * @param dt delta time
     */
    public void update(float dt){
        handleInput();
    }

    /**
     * Get of the texture of the hero
     * @return hero texture
     */
    public Texture getTexture(){
        return hero;
    }

    /**
     * Get of the Position in X of the hero
     * @return position in x
     */
    public float getPositionX(){
        return position.x;
    }

    /**
    * Get of the Position in y of the hero
    * @return position in y
    */
    public float getPositionY(){
        return position.y;
    }

    /**
     * Get of the rectangle
     * @return box of the hero
     */
    public Rectangle getBox(){
        return box;
    }

    /**
     * Reduces the health of the hero when it is hitted
     * @param hp health lost
     */
    public void getHit(int hp){
        health -= hp;
    }

    /**
     * Get of the health of the hero
     * @return health of the hero
     */
    public int getHealth(){
        return health;
    }

    /**
     * Disposes the hero
     */
    public void dispose(){
        hero.dispose();
        box.setWidth(0);
        box.setHeight(0);
        box = null;
    }

    /**
     * Boolean to check if the hero is dead
     * @return true if is dead, false if it is not
     */
    public boolean isDead(){
        if (health <= 0)
            dead = true;
        return dead;
    }

    public void checkCollisions(ArrayList<Enemy> enemies) {
        for(Enemy en : enemies)
        {
            if (en.getBox().overlaps(getBox()))
            {
                dead = true;
            }
        }
    }
}
