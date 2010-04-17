package at.bartinger.example;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import at.bartinger.candroid.CandroidActivity;


public class CanvasGame extends CandroidActivity {

	private GameView mSurfaceView;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		mSurfaceView = new GameView(this, true, true, 1.5d, 500);
		setFullscreen(true);

		// We need to know the width and height of the display pretty soon,
		// so grab the information now.
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		

		// Now's a good time to run the GC.  Since we won't do any explicit
		// allocation during the test, the GC should stay dormant and not
		// influence our results.
		Runtime r = Runtime.getRuntime();
		r.gc();

		//Add here your sprites
		
		setContentView(mSurfaceView);
	}


	/** Recycles all of the bitmaps loaded in onCreate(). */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mSurfaceView.clearSecondRunnable();
		mSurfaceView.stopDrawing();
		
		//Call here the recycle method from every Sprite
		
	}

}