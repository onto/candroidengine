package at.bartinger.candroid.background;

import android.graphics.Canvas;

public class ColoredBackground extends Background{
	
	private int color;
	
	public ColoredBackground(int color) {
			this.color = color;
	}
	
	public void changeColor(int color){
		this.color = color;		
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(color);
		super.draw(canvas);
	}

}
