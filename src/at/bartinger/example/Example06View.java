package at.bartinger.example;

import android.content.Context;
import android.graphics.Color;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.ingame.Score;
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderer.SurfaceRenderer;

public class Example06View extends CandroidSurfaceView{
	
	
	private Context context;
	private SurfaceRenderer spriteRenderer;
	
	private Text welcome;
	private Text press;
	private Score gamescore;

	public Example06View(Context context, boolean usesAccelerometer) {
		super(context, usesAccelerometer);
		this.context = context;
		
		spriteRenderer = new SurfaceRenderer();
		
		welcome = new Text(context, 20, 20, "Welcome to the Text and Fonts Tutorial", Color.WHITE, 18, true, "fonts/GeosansLight.ttf");
		press = new Text(context, 20, 150, "Pressure: 0", Color.WHITE, 50, true, "fonts/GeosansLight.ttf");
		gamescore = new Score(context, 20,100, "Your Score: ", Color.GREEN,35,false,"fonts/digital.ttf");
		
		spriteRenderer.addRenderable(welcome);	
		spriteRenderer.addRenderable(gamescore);
		spriteRenderer.addRenderable(press);
		
		setRendererAndStart(spriteRenderer);
	}
	
	
	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		press.setText("Pressure: " + pressure);
	}
	
	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
		gamescore.score++;
	}

}
