package com.lpoo.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.lpoo.game.PlaneRacing;

import java.util.Collections;

/**
 * Created by Joao on 06-06-2016.
 */
public class HighScoreScreen extends State {
    private Texture leaderBoard;
    private Texture first;
    private Texture second;
    private Texture third;

    private Stage stage;

    private BitmapFont font;
    private Skin skin;
    private TextureAtlas buttonAtlas;
    private TextButton home;

    private  Label score1Label;
    private Label score2Label;
    private Label score3Label;

    public HighScoreScreen(ScreenManager gsm, PlaneRacing game) {
        super(gsm, game);

        stage = new Stage();

        Collections.sort(game.scores);
        Collections.reverse(game.scores);

        font = new BitmapFont();
        skin = new Skin();
        buttonAtlas = new TextureAtlas("Buttons.pack");
        skin.addRegions(buttonAtlas);
        TextButton.TextButtonStyle homeStyle = new TextButton.TextButtonStyle();

        homeStyle.up = skin.getDrawable("home");
        homeStyle.down = skin.getDrawable("home");
        homeStyle.checked = skin.getDrawable("home");
        homeStyle.font = font;

        home = new TextButton("", homeStyle);
        home.setPosition(PlaneRacing.WIDTH / 2 + 200, 0);

        leaderBoard = new Texture("leaderboardsComplex.png");
        first = new Texture("trophy.png");
        second = new Texture("medal2.png");
        third = new Texture("medal1.png");

        score1Label = new Label(String.format("1-     %05d", game.scores.get(0)), new Label.LabelStyle(new BitmapFont(), Color.RED));
        score1Label.setFontScale(3f);
        score2Label = new Label(String.format("2-     %05d", game.scores.get(1)), new Label.LabelStyle(new BitmapFont(), Color.RED));
        score2Label.setFontScale(3f);
        score3Label = new Label(String.format("3-     %05d", game.scores.get(2)), new Label.LabelStyle(new BitmapFont(), Color.RED));
        score3Label.setFontScale(3f);

        Table table = new Table();
        table.left();
        table.setFillParent(true);


        table.padTop(60);
        table.add(score1Label).height(100).padLeft(PlaneRacing.WIDTH/4);
        table.row();
        table.add(score2Label).height(100).padLeft(PlaneRacing.WIDTH/4);
        table.row();
        table.add(score3Label).height(100).padLeft(PlaneRacing.WIDTH/4);
        table.row();

        stage.addActor(table);
        stage.addActor(home);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void handleInput() {
        if (home.isPressed()){
            dispose();
            gsm.set(new MenuScreen(gsm, game));
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(leaderBoard, PlaneRacing.WIDTH/4, PlaneRacing.HEIGHT-100, leaderBoard.getWidth()*3, leaderBoard.getHeight()*2);
        sb.draw(first, PlaneRacing.WIDTH/2- 30, PlaneRacing.HEIGHT/2 + 55);
        sb.draw(second,PlaneRacing.WIDTH/2- 30, PlaneRacing.HEIGHT/2 - 45);
        sb.draw(third,PlaneRacing.WIDTH/2- 30, PlaneRacing.HEIGHT/2 - 145);
        sb.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        first.dispose();
        second.dispose();
        third.dispose();
        leaderBoard.dispose();
    }
}
