package Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.my.game.MainClass;

import java.util.logging.Level;

import Screens.PlayScreen;

public class Hud implements Disposable{
	public Stage stage;
	public Viewport viewport;
	
	private Integer worldTimer;
	private float timeCount;
	private static Integer score;
	
	Label countdownLabel;
	static Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label taleLabel;
	
	public Hud(SpriteBatch sb,int level)
	{
		worldTimer = 30;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(MainClass.V_WIDTH, MainClass.V_HEIGHT,new OrthographicCamera());
		stage = new Stage(viewport,sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		
		 countdownLabel = new Label(String.format("%03d", worldTimer),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		 scoreLabel = new Label(String.format("%06d", score),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		 timeLabel = new Label(" Time",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		 levelLabel = new Label(String.format("%d", level),new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		 worldLabel = new Label("Level",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		 taleLabel = new Label("TALE",new Label.LabelStyle(new BitmapFont(),Color.WHITE));
		 
		 
		 table.add(taleLabel).expandX().padTop(10);
		 table.add(worldLabel).expandX().padTop(10);
		 table.add(timeLabel).expandX().padTop(10);
		 table.row();
		 table.add(scoreLabel).expandX();
		 table.add(levelLabel).expandX();
		 table.add(countdownLabel).expandX();
		 
		 stage.addActor(table);
		
	}
	
	
	public Hud() {}

	public Integer get_worldtime()
	{
		return worldTimer;
	}


	public void update(float dt)
	{
		timeCount += dt;
		if(timeCount >= 1)
		{
			worldTimer--;
			countdownLabel.setText(String.format("%03d", worldTimer));
			timeCount = 0;
			
		}
	}
	
	public void addScore(int coin)
	{
		score += coin;
		scoreLabel.setText(String.format("%06d", score));
	}

	public void add_level()
	{
		levelLabel.setText(PlayScreen.level + 1);
	}
	@Override
	public void dispose() {
		stage.dispose();
		
	}
	

}