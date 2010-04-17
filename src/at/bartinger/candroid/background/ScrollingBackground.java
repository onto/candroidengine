package at.bartinger.candroid.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class ScrollingBackground extends Background{
	
	private Bitmap mBackground;
	private int offsetX;
	private int offsetY;

	private int autooffsetX;
	private int autooffsetY;

	public ScrollingBackground(Context context, String assetPath) {
		mBackground = loadBitmap(context, assetPath);
	}
	
	public ScrollingBackground(Bitmap background) {
		mBackground = background;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mBackground, x, y, null);
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
	
	public void setOffset(int offsetX, int offsetY){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public void setAutoOffset(int autooffsetX, int autooffsetY){
		this.autooffsetX = autooffsetX;
		this.autooffsetY = autooffsetY;
	}
	
	
}
