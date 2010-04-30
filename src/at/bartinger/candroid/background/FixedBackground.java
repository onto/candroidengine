package at.bartinger.candroid.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import at.bartinger.candroid.texture.Texture;

public class FixedBackground extends Background{
	
	private Bitmap mBackground;
	
	public FixedBackground(Texture tex) {
		mBackground = tex.tex;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mBackground, (int)x, (int)y, null);
		super.draw(canvas);
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

}
