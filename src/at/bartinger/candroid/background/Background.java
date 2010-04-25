package at.bartinger.candroid.background;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import at.bartinger.candroid.CandroidActivity;
import at.bartinger.candroid.Constants;
import at.bartinger.candroid.Renderable;

public class Background extends Renderable{
	
	public Background() {
	
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		super.draw(canvas);
	}
	

	public void update(){}
	
	protected Bitmap loadBitmap(Context context, String assetPath) {
		Bitmap bitmap = null;
		if (context != null) {

			InputStream is = null;
			try {
				is = context.getResources().getAssets().open(assetPath);
				bitmap = BitmapFactory.decodeStream(is);
				
				Log.d(Constants.LOGTAG, "Loaded: " + assetPath);
			}catch (IOException e) {
				Log.d(Constants.LOGTAG, "Can't load Bitmp " + assetPath);

			} finally {
				try {
					is.close();
				} catch (IOException e) {
					Log.d(Constants.LOGTAG, "Can't close Stream from " + assetPath);
				}
			}
		}

		return bitmap;
	}
	
	
	
}
