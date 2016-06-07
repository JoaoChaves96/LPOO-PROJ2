package com.lpoo.game.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lpoo.game.PlaneRacing;
import com.lpoo.game.Sprites.Animation;
import com.lpoo.game.Sprites.AnimationExpl;

import java.util.Random;

/**
 * Created by Joao on 13-05-2016.
 */
public class Bullet {
    private Vector2 position;
    private Animation bulletAnimation;
    private Rectangle bounds;
    String type;

    /*
    Constructor of the Bullet
    @param x Coordenate x of the object
    @param y Coordenate y of the object
    @param typ Type of bullet
     */
    public Bullet(int x, int y, String typ){
        position = new Vector2(x, y);
        String s;
        if (typ == "H")
            s = "banim.png";
        else
            s = "enbanim.png";

        Texture texture = new Texture(s);
        bulletAnimation = new Animation(new TextureRegion(texture), 6, 0.5f);
        bounds = new Rectangle(position.x, position.y, bulletAnimation.getFrame().getRegionWidth(), bulletAnimation.getFrame().getRegionHeight());
        type = typ;
    }

    /**
     Update of the bullet position
     @param dt distance to be moved
     */
    public void update(float dt){
        if (type == "H")
            position.add(5,0);
        else if (type == "E")
            position.add(-5, 0);

        bounds.setPosition(position.x, position.y);
        bulletAnimation.update(dt);
    }

    /**
     Get of the Texture
     @return texture of the bullet
     */
    public TextureRegion getTexture()
    {
       return bulletAnimation.getFrame();
    }

    /**
     Get of the Position X
     @return position x of the bullet
     */
    public float getPositionX(){
        return position.x;
    }
    /**
     Get of the Position y
     @return position y of the bullet
     */
    public float getPositionY(){
        return position.y;
    }

    /**
     Boolean function to check if the bullet has colided with another object
     * @param player rectangle to check if colided
     * @return true if colides, false if not
     */
    public boolean colides(Rectangle player){
        return player.overlaps(bounds);
    }

    /**
     Get of the bounds of the bullet
     @return bounds of the bullet
     */
    public Rectangle getBounds(){
        return bounds;
    }

    /**
     This function dispose the bullet
     */
    public void dispose(){
        bounds.setWidth(0);
        bounds.setHeight(0);
        bounds = null;
        bulletAnimation.dispose();
    }
}
