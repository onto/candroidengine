/**
 *  Copyright 2010 Bartl Dominic

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package at.bartinger.candroid.renderable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
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
	
	public boolean pointOnSprite(int x, int y){
			return (x > this.x && y > this.y && x < this.x+this.width && y < this.y+this.height);
	}

}
