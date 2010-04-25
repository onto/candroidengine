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
import at.bartinger.candroid.texture.Texture;

public class Sprite extends Renderable{

	public Bitmap sprite;
	
	private Matrix mMatrix = new Matrix();
	public boolean isVisible = true;


	public Sprite(Texture tex, int x, int y) {
		this.x = x;
		this.y = y;
		
		sprite = tex.tex;
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

}
