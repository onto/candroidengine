package at.bartinger.candroid.background;

import android.graphics.Canvas;

public class MultibleBackground extends Background{

	private Background[] backgrounds;

	public MultibleBackground(Background[] bgs) {
		backgrounds = bgs;
	}

	@Override
	public void draw(Canvas canvas) {
		for(Background bg : backgrounds){
			bg.draw(canvas);
		}
	}

	@Override
	public void update() {
		for(Background bg : backgrounds){
			bg.update();
		}
	}
}
