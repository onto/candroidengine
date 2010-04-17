package at.bartinger.candroid.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class FixedBackground extends Background{
	
	private Bitmap mBackground;
	
	public FixedBackground(Context context, String assetPath) {
		mBackground = loadBitmap(context, assetPath);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mBackground, x, y, null);
		super.draw(canvas);
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

}
