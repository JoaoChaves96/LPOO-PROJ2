package com.lpoo.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Plane;
import com.lpoo.game.PlaneRacing;

import java.util.Stack;

/**
 * Created by Joao on 13-05-2016.
 */
public class ScreenManager{
    private Stack<State> states;

    public SpriteBatch sb;

    public ScreenManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }
}
