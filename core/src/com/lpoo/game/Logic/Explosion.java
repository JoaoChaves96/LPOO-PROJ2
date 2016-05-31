package com.lpoo.game.Logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lpoo.game.Sprites.Animation;
import com.lpoo.game.Sprites.AnimationExpl;

/**
 * Created by Joao on 27-05-2016.
 */
public class Explosion {
    private Vector2 position;
    private AnimationExpl explAnimation;
    private Rectangle bounds;

    public Explosion(int x, int y){
        position = new Vector2(x, y);
        Texture texture = new Texture("explosionAnim.png");
        explAnimation = new AnimationExpl(new TextureRegion(texture), 10, 0.5f);
        bounds = new Rectangle(position.x, position.y, explAnimation.getFrame().getRegionWidth(), explAnimation.getFrame().getRegionHeight());
    }
    public void handleInput(){}

    public void update(float dt){
        if (explAnimation.getCurrFrame() < explAnimation.getFrameCount() - 1)
            explAnimation.update(dt);
        else
            dispose();
    }

    public TextureRegion getTexture(){
        return explAnimation.getFrame();
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

       explAnimation.dispose();
    }
}
