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

package at.bartinger.candroid;

import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import at.bartinger.candroid.texture.Texture;

public class CandroidCellView extends CandroidSurfaceView{

	private Map<Character,Texture> interpreter = null;
	private String[] fullCells = null;
	private int cellWidth;
	private int cellHeight;
	private Bitmap map;

	public boolean allowStart = false;

	public CandroidCellView(Context context) {
		super(context);
	}

	public void set(Map<Character,Texture> interpreter, String[] fullCells, int cellWidth, int cellHeight){
		this.interpreter = interpreter;
		this.fullCells = fullCells;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		allowStart = true;
		map = Bitmap.createBitmap(fullCells[0].length()*cellWidth, fullCells.length*cellHeight, Config.ARGB_8888);
		init();
	}

	private void init(){
		Canvas c = new Canvas(map);
		if(allowStart){
			int dx = 0;
			int dy = 0;

			for(int i = 0; i < fullCells.length; i++){
				dx=0;
				for(int j = 0; j < fullCells[i].length(); j++){

					Bitmap toDraw = interpreter.get(fullCells[i].charAt(j)).tex;
					c.drawBitmap(toDraw, dx, dy, null);

					dx+=cellWidth;
				}
				dy+=cellHeight;
			}
		}
	}
	
	public Texture getMap(){
		return new Texture(map);
	}
	
	
	

}