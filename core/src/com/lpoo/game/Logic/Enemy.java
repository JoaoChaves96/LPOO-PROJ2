package com.lpoo.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 01-06-2016.
 */
public class Enemy {
    private Vector2 position;
    private Texture enemy;
    private int health;
    private Rectangle box;

    /**
     Constructor of the enemy
     * @param x Position in X of the enemy
     * @param y Position in Y of the enemy
     */
    public Enemy(int x, int y){
        position = new Vector2(x, y);
        enemy = new Texture("enemy.png");
        box = new Rectangle(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, enemy.getWidth(), enemy.getHeight() + 10);
        box.setPosition(x + 23, y + 10);
        health = 100;
    }

    /**
     Update of the enemy position
     @param dt distance to be moved
     */
    public void update(float dt){
        position.add(-2, 0);
        box.setPosition(position.x + 23, position.y + 10);
    }

    /**
     Get of the Texture
     @return texture of the enemy
     */
    public Texture getTexture(){
        return enemy;
    }

    /**
     Get of the Position X
     @return position x of the enemy
     */
    public float getPositionX(){
        return position.x;
    }

    /**
     Get of the Position y
     @return position y of the enemy
     */
    public float getPositionY(){
        return position.y;
    }

    /**
     Get of the box of the enemy
     @return box of the enemy
     */
    public Rectangle getBox(){
        return box;
    }

    /**
     Function when the enemy is hitted, reducing his health
     @param dmg Size of health that enemy loses
     */
    public void getHit(int dmg){
        health-=dmg;
    }

    /**
     This function dispose the enemy
     */
    public void dispose(){
        enemy.dispose();
        box.setWidth(0);
        box.setHeight(0);
        box = null;
    }

    /**
     Get of the actual health of the enenmy
     @return health of the enemy
     */
    public int getHealth(){
        return health;
    }
}
