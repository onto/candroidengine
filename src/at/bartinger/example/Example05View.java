package at.bartinger.example;

import android.content.Context;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.sound.BackgroundSound;

public class Example05View extends CandroidSurfaceView{

	public Example05View(Context context, boolean usesAccelerometer) {
		super(context, usesAccelerometer);
		
		BackgroundSound music = new BackgroundSound(context, "sounds/backgroundmusic.ogg", true);
		music.start();
	}
	
	@Override
	public void recycle() {
		// TODO Auto-generated method stub
		super.recycle();
	}

}
