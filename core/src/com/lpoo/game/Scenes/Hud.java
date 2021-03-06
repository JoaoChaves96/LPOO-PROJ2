package com.lpoo.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lpoo.game.Logic.Hero;
import com.lpoo.game.PlaneRacing;

/**
 * Created by Joao on 13-05-2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private float timeCount;
    private int score;
    private Hero hero;

    Label scoreLabel;
    Label heroLabel;
    Label healthLabel;
    Label hpLabel;

    public Hud(SpriteBatch sb, Hero h){
        timeCount = 0;
        score = 0;
        viewport = new FitViewport(PlaneRacing.WIDTH, PlaneRacing.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);
        hero = h;

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        heroLabel = new Label ("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        hpLabel = new Label(String.format("%03d", hero.getHealth()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label("HEALTH", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(heroLabel);
        table.add(healthLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(hpLabel).expandX();

        stage.addActor(table);
    }

    public void update(float dt){
        timeCount += dt;
        if (timeCount >= 1){
            score++;
            scoreLabel.setText(String.format("%06d", score));
            timeCount = 0;
        }
        hpLabel.setText((String.format("%03d", hero.getHealth())));
    }

    public void incScore(int num){
        score+=num;
    }

    public int getScore(){
        return score;
    }
}
