package at.bartinger.candroid.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ScrollingBackground extends Background{
	
	private Bitmap mBackground;
	private double offsetX;
	private double offsetY;

	private double autooffsetX;
	private double autooffsetY;

	public ScrollingBackground(Context context, String assetPath) {
		mBackground = loadBitmap(context, assetPath);
	}
	
	public ScrollingBackground(Bitmap background) {
		mBackground = background;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mBackground, (int)x, (int)y, null);
		super.draw(canvas);
	}
	
	@Override
	public void update() {
		x+=offsetX;
		y+=offsetY;
		
		x+=autooffsetX;
		y+=autooffsetY;
		
		offsetX=0;
		offsetY=0;
	}
	
	public void setOffset(double offsetX, double offsetY){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public void setSpeed(double autooffsetX, double autooffsetY){
		this.autooffsetX = autooffsetX;
		this.autooffsetY = autooffsetY;
	}
	
	
}
