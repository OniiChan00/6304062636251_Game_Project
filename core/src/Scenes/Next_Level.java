package Scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.my.game.MainClass;

import Screens.PlayScreen;
import Sprites.GameDany;

public class Next_Level implements Screen
{
    private Viewport viewport;
    private Stage stage;
    private Game game;

    public Next_Level(Game game)
    {
        this.game = game;
        viewport = new FitViewport(MainClass.V_WIDTH,MainClass.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, ((MainClass) game).batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);


        Table table = new Table();
        table.center();
        table.setFillParent(true);

        if(GameDany.Level + 1 < 3)
        {
            Label nextlevel = new Label("Next Level",font);
            table.add(nextlevel).expandX();
            stage.addActor(table);
        }
        else
        {
            Label nextlevel = new Label("END",font);
            table.add(nextlevel).expandX();
            stage.addActor(table);
        }

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched())
        {

            if(GameDany.Level + 1 < 3)
            {
                GameDany.Level+= 1;
                game.setScreen(new PlayScreen((MainClass) game));
                dispose();
            }
            else
            {
                GameDany.Level = 1;
                game.setScreen(new PlayScreen((MainClass) game));
                dispose();
            }

        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
