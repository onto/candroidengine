package at.bartinger.candroid.sprite;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import at.bartinger.candroid.Renderable;

public class Sprite extends Renderable{

	public Bitmap sprite;
	public static BitmapFactory.Options DEFAULT_BITMAP_OPTIONS = new BitmapFactory.Options();
	
	private Matrix mMatrix = new Matrix();

		
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
		try {
			sprite = BitmapFactory.decodeStream(context.getResources().getAssets().open(assetPath));
		} catch (IOException e) {
			Log.e("CanvasGame","Could not found " + assetPath);
		}
		this.height = sprite.getHeight();
		this.width = sprite.getWidth();
		
	}
	
	
	@Override
	public void draw(Canvas canvas){
		canvas.drawBitmap(sprite, x, y, null);
	}
	
	public void recycle(){
		sprite.recycle();
		sprite = null;
	}

	public void rotate(int degrees){
		mMatrix.postRotate(degrees);
		sprite = Bitmap.createBitmap(sprite, x, y, height, width, mMatrix, false);
	}

	public void rotate(int px, int py, int degrees){
		mMatrix.postRotate(degrees,px,py);
		sprite = Bitmap.createBitmap(sprite, x, y, height, width, mMatrix, false);
	}

	public void scale(int sx, int sy){
		mMatrix.postScale(sx, sy);
		sprite = Bitmap.createBitmap(sprite, x, y, height, width, mMatrix, false);
	}

	public void scale(int sx, int sy, int px, int py){
		mMatrix.postScale(sx, sy, px, py);
		sprite = Bitmap.createBitmap(sprite, x, y, height, width, mMatrix, false);
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
	private Bitmap loadBitmap(Context context, int resourceId, BitmapFactory.Options sBitmapOptions) {
		Bitmap bitmap = null;
		if (context != null) {

			InputStream is = context.getResources().openRawResource(resourceId);
			try {
				if(sBitmapOptions == null){
					bitmap = BitmapFactory.decodeStream(is);
				}else{
					bitmap = BitmapFactory.decodeStream(is, null, sBitmapOptions);
				}
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					Log.d("CanvasGame", "Can't load Bitmp " + resourceId);
				}
			}
		}

		return bitmap;
	}


}
