package com.lpoo.game.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Joao on 14-05-2016.
 */
public class AnimationExpl {
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;


    public AnimationExpl(TextureRegion region, int frameCount, float cycleTime, int size){
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update(float dt) {
        currentFrameTime += dt;
        if (frame < frameCount) {
            if (currentFrameTime > maxFrameTime) {
                currentFrameTime = 0;
            }
        }
        else
            dispose();

        frame++;
    }

    public TextureRegion getFrame() {
        if (frame < frameCount)
            return frames.get(frame);
        else
            return null;
    }

    public void dispose(){
        for(TextureRegion frame : frames)
            frame.getTexture().dispose();
    }

    public int getFrameCount(){
        return frameCount;
    }

    public int getCurrFrame(){
        return frame;
    }
}