package at.bartinger.candroid.background;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import at.bartinger.candroid.Renderable;

public class Background extends Renderable{
	
	public Background() {
	
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
	}
	

	public void update(){}
	
	protected Bitmap loadBitmap(Context context, String assetPath) {
		Bitmap bitmap = null;
		try {
			bitmap = BitmapFactory.decodeStream(context.getResources().getAssets().open(assetPath));
		} catch (IOException e) {
			Log.e("CanvasGame","Could not found File: " + assetPath);
		}

		return bitmap;
	}
	
	
	
}
