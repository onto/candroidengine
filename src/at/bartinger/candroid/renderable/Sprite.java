package at.bartinger.candroid.renderable;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import at.bartinger.candroid.CandroidActivity;
import at.bartinger.candroid.Constants;
import at.bartinger.candroid.Renderable;

public class Sprite extends Renderable{

	public Bitmap sprite;
	public static BitmapFactory.Options DEFAULT_BITMAP_OPTIONS = new BitmapFactory.Options();
	private Matrix mMatrix = new Matrix();
	public boolean isVisible = true;


	public Sprite(Bitmap bitmap, int x, int y) {
		this.x = x;
		this.y = y;
		sprite = bitmap;
		DEFAULT_BITMAP_OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;

		this.height = sprite.getHeight();
		this.width = sprite.getWidth();

	}

	public Sprite(Context context, String assetPath, int x, int y) {
		this.x = x;
		this.y = y;


		DEFAULT_BITMAP_OPTIONS.inPreferredConfig = Bitmap.Config.RGB_565;

		sprite = loadBitmap(context, assetPath);
		this.height = sprite.getHeight();
		this.width = sprite.getWidth();
	}

	@Override
	public void update() {

		super.update();
	}
	
	public void setVisibility(boolean visible){
		isVisible=visible;
	}


	@Override
	public void draw(Canvas canvas){
		if(isVisible)
		canvas.drawBitmap(sprite, (int)x, (int)y, null);
	}

	public void recycle(){
		sprite.recycle();
		sprite = null;
	}

	public void rotate(int degrees){
		mMatrix.postRotate(degrees);
		sprite = Bitmap.createBitmap(sprite, (int)x, (int)y, height, width, mMatrix, false);
	}

	public void rotate(int px, int py, int degrees){
		mMatrix.postRotate(degrees,px,py);
		sprite = Bitmap.createBitmap(sprite, (int)x, (int)y, height, width, mMatrix, false);
	}

	public void scale(int sx, int sy){
		mMatrix.postScale(sx, sy);
		sprite = Bitmap.createBitmap(sprite, (int)x, (int)y, height, width, mMatrix, false);
	}

	public void scale(int sx, int sy, int px, int py){
		mMatrix.postScale(sx, sy, px, py);
		sprite = Bitmap.createBitmap(sprite, (int)x, (int)y, height, width, mMatrix, false);
	}

	/**
	 * Loads a bitmap from a resource and converts it to a bitmap.  This is
	 * a much-simplified version of the loadBitmap() that appears in
	 * SimpleGLRenderer.
	 * @param context  The application context.
	 * @param resourceId  The id of the resource to load.
	 * @param sBitmapOptions The options for the bitmap (see also: Sprite.DEFAULT_BITMAP_OPTIONS)
	 * @return  A bitmap containing the image contents of the resource, or null
	 *     if there was an error.
	 */
	private Bitmap loadBitmap(Context context, String assetPath) {
	
		Bitmap bitmap = null;
		if (context != null) {

			InputStream is = null;
			try {
				is = context.getResources().getAssets().open(assetPath);
				bitmap = BitmapFactory.decodeStream(is, null, DEFAULT_BITMAP_OPTIONS);
				
			}catch (IOException e) {
				Log.d(Constants.LOGTAG, "Can't load Bitmp " + assetPath);

			} finally {
				try {
					is.close();
				} catch (IOException e) {
					Log.d(Constants.LOGTAG, "Can't close Stream");
				}
			}
		}
		
		return bitmap;
	}


}
