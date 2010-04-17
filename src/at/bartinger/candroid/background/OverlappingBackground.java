package at.bartinger.candroid.background;

import java.util.ArrayList;

import android.graphics.Canvas;

public class OverlappingBackground extends Background{

	private ArrayList<Background> backgrounds = new ArrayList<Background>();

	public OverlappingBackground() {
		// TODO Auto-generated constructor stub
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

	public void addBackground(Background background){
		backgrounds.add(background);
	}

	public void removeBackground(Background background){
		backgrounds.remove(background);
	}

	public void removeBackground(int backgroundLocation){
		backgrounds.remove(backgroundLocation);
	}
}
