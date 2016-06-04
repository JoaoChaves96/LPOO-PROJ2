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

/**
 * Created by Joao on 13-05-2016.
 */
public class Bullet {
    private Vector2 position;
    private Animation bulletAnimation;
    private Rectangle bounds;
    String type;

    public Bullet(int x, int y, String typ){
        position = new Vector2(x, y);
        String s;
        /*if(typ == "H")
            s = "bAnim.png";
        else
            s = "enbAnim.png";*/
        s = "bAnim.png";

        Texture texture = new Texture(s);
        bulletAnimation = new Animation(new TextureRegion(texture), 6, 0.5f);
        bounds = new Rectangle(position.x, position.y, bulletAnimation.getFrame().getRegionWidth(), bulletAnimation.getFrame().getRegionHeight());
        type = typ;
    }
    public void handleInput(){}

    public void update(float dt){
        if (type == "H")
            position.add(5,0);
        else
            position.add(-5, 0);
        bounds.setPosition(position.x, position.y);
        bulletAnimation.update(dt);
    }

    public TextureRegion getTexture()
    {
       return bulletAnimation.getFrame();
    }

    public float getPositionX(){
        return position.x;
    }
    public float getPositionY(){
        return position.y;
    }

    public boolean colides(Rectangle player){
        return player.overlaps(bounds);
    }

    public Rectangle getRectangle(){
        return bounds;
    }

    public void dispose(){
        bounds.setWidth(0);
        bounds.setHeight(0);
        bounds = null;
        bulletAnimation.dispose();
    }
}
