package at.bartinger.candroid.renderable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Bitmap.Config;
import android.util.Log;
import at.bartinger.candroid.CandroidActivity;
import at.bartinger.candroid.Renderable;

public class CellSprite extends Renderable{

	private Map<Character, Sprite>stringToCell;
	private int cellWidth,cellHeight;
	private String[] fullCells;

	public CellSprite(int x, int y, int cellWidth, int cellHeight, Map<Character,Sprite> stringToCell, String[] fullCells) {
		//super(Bitmap.createBitmap(cellWidth*fullCells[0].length(), cellHeight*fullCells.length, Config.RGB_565),x,y);

		this.x=x;
		this.y=y;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.stringToCell = stringToCell;
		this.fullCells = fullCells;

	}

	@Override
	public void draw(Canvas canvas) {
		int dx = (int)x;
		int dy = (int)y;

		for(int i = 0; i < fullCells.length; i++){
			dx=(int)x;
			for(int j = 0; j < fullCells[i].length(); j++){

				Bitmap toDraw = stringToCell.get(fullCells[i].charAt(j)).sprite;
				canvas.drawBitmap(toDraw, dx, dy, null);

				x+=cellWidth;
			}
			dy+=cellHeight;
		}
	}



}
