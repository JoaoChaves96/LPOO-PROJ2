package com.lpoo.game.Logic;

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
    private int dificulty;
    private int health;
    private Rectangle box;

    public Enemy(int x, int y){
        position = new Vector2(x, y);
        enemy = new Texture("enemy.png");
        box = new Rectangle(PlaneRacing.WIDTH / 2, PlaneRacing.HEIGHT / 2, enemy.getWidth(), enemy.getHeight() + 10);
        box.setPosition(x + 23, y + 10);
        health = 100;
    }

    public void handleInput(){}

    public void update(float dt){
        handleInput();
    }

    public Texture getTexture(){
        return enemy;
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

    public void getHit(int dmg){
        health-=dmg;
    }

    public void dispose(){
        enemy.dispose();
    }

    public int getHealth(){
        return health;
    }
}
