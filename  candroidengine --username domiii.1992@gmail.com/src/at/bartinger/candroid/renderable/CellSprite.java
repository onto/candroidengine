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

import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import at.bartinger.candroid.Renderable;
import at.bartinger.candroid.texture.Texture;

public class CellSprite extends Renderable{

	private Map<Character, Texture>CharToCell;
	private int cellWidth,cellHeight;
	private String[] fullCells;
	private Bitmap sprite;

	public CellSprite(int x, int y, int cellWidth, int cellHeight, Map<Character,Texture> CharToCell, String[] fullCells) {
		super();

		this.x=x;
		this.y=y;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.CharToCell = CharToCell;
		this.fullCells = fullCells;
		init();
		
	}
	
	private void init(){
		
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(sprite, (float)y, (float)x, null);
	}



}
