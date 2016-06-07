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
    private int size;

    /**
     Constructor of the explosion
     * @param x x of the explosion
     * @param y y of the explosion
     * @param size size of the explosion
     */
    public Explosion(int x, int y, int size){
        position = new Vector2(x, y);
        Texture texture = new Texture("explosionanim3.png");
        this.size = size;
        explAnimation = new AnimationExpl(new TextureRegion(texture), 7, 0.7f, size);
        bounds = new Rectangle(position.x, position.y, explAnimation.getFrame().getRegionWidth(), explAnimation.getFrame().getRegionHeight());
    }

    /**
     Input handled
     */
    public void handleInput(){}

    /**
     Update of the explosion position
     @param dt distance to be moved
     */
    public void update(float dt){
        if (explAnimation.getCurrFrame() < explAnimation.getFrameCount() - 1)
            explAnimation.update(dt);
        else
            dispose();
    }

    /**
     Get of the Texture
     @return texture of the explosion
     */
    public TextureRegion getTexture(){
        return explAnimation.getFrame();
    }

    /**
     Get of the Position X
     @return position x of the explosion
     */
    public float getPositionX(){
        return position.x;
    }
    /**
     Get of the Position y
     @return position y of the explosion
     */
    public float getPositionY(){
        return position.y;
    }

    /**
     Boolean function to check if the enemy has colided with another object
     * @param player rectangle to check if colided
     * @return true if colides, false if not
     */
    public boolean colides(Rectangle player){
        return player.overlaps(bounds);
    }

    /**
     Get of the bounds of the enemy
     @return bounds of the enemy
     */
    public Rectangle getRectangle(){
        return bounds;
    }

    /**
     This function dispose the explosion
     */
    public void dispose(){

       explAnimation.dispose();
    }

    /**
     Get of the frame of the explosion
     @return number of the current frame of the explosion
     */
    public int getFrame(){
        return explAnimation.getCurrFrame();
    }

    /**
     Get of the size of the explosion
     @return size of the explosion
     */
    public int getSize(){
        return size;
    }

    /**
     Boolean to check if it is already dead
     @return true if it is dead, false if it is not
     */
    public boolean isDead(){
        return explAnimation.isDead();
    }
}
