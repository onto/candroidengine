package at.bartinger.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.widget.Toast;
import at.bartinger.candroid.CandroidThread;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.background.FixedBackground;
import at.bartinger.candroid.background.ScrollingBackground;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.sprite.Sprite;
import at.bartinger.candroid.sprite.TileAnimation;

public class GameView extends CandroidSurfaceView {

	private Context context;
	private SurfaceRenderer spriteRenderer;
	private TileAnimation marioAnimation;
	private Sprite marioSprite;
	private TileAnimation explosion;

	private Paint whitetext = new Paint();
	
	private ScrollingBackground bg;
	private int touchstartX;
	private int touchstartY;


	public GameView(Context context, boolean usesTrackball,boolean usesAccelerometer, double shakeStrongnes,long TimeBetweenShakes) {
		super(context, usesTrackball, usesAccelerometer, shakeStrongnes,TimeBetweenShakes);
		this.context = context;
		
		bg = new ScrollingBackground(context, "graphics/backgrounds/sm_bg.png");
		bg.setAutoOffset(-1, 0);

		whitetext.setStrokeWidth(2);
		whitetext.setTextSize(16);
		whitetext.setColor(Color.WHITE);
		whitetext.setFakeBoldText(true);
		whitetext.setAntiAlias(true);
		whitetext.setTextAlign(Align.CENTER);

		spriteRenderer = new SurfaceRenderer();


		String[] sonicSprites = new String[]{
				"graphics/sonic/sonic1.png",
				"graphics/sonic/sonic2.png",
				"graphics/sonic/sonic3.png",
				"graphics/sonic/sonic4.png",
				"graphics/sonic/sonic5.png",
				"graphics/sonic/sonic6.png"
		};


		marioSprite = new Sprite(context,"graphics/mario_mysprite.png", 10,10);
		//sonicAnimation = new TileAnimation(context, 100, 100, sonicSprites, 100);
		explosion = new TileAnimation(context, 100, 250, "graphics/explosion.png", 5, 5, 60);
		//marioAnimation = new TileAnimation(context, 10, 100, R.drawable.mario_mysprite, 8, 100);


		spriteRenderer.addSprite(marioSprite);
		//spriteRenderer.addSprite(marioAnimation);
		spriteRenderer.addSprite(explosion);
		
		spriteRenderer.setBackground(bg);
		setRendererAndStart(spriteRenderer);
	}
	
	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		touchstartX = touchX;
		touchstartY = touchY;
		super.onTouchDown(touchX, touchY, pressure);
	}

	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
		bg.setOffset(touchX-touchstartX, 0);
		touchstartX = touchX;
		super.onTouchDown(touchX, touchY, pressure);
	}
	
	@Override
	public void onTouchUp(int touchX, int touchY, int pressure) {
		touchstartX = 0;
		touchstartY = 0;
		super.onTouchUp(touchX, touchY, pressure);
	}

	@Override
	public void onShaking() {
		Toast.makeText(context, "SHAKE ME!", Toast.LENGTH_SHORT).show();
		super.onShaking();
	}

	@Override
	public void onStopDrawing(Canvas canvas) {
		canvas.drawText("FPS: "+avgFPS, 200, 300, whitetext);
	}

}
