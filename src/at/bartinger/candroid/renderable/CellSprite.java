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

	public CellSprite(int x, int y, int cellWidth, int cellHeight, Map<Character,Texture> CharToCell, String[] fullCells) {
		super();

		this.x=x;
		this.y=y;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.CharToCell = CharToCell;
		this.fullCells = fullCells;

	}

	@Override
	public void draw(Canvas canvas) {
		int dx = (int)x;
		int dy = (int)y;

		for(int i = 0; i < fullCells.length; i++){
			dx=(int)x;
			for(int j = 0; j < fullCells[i].length(); j++){

				Bitmap toDraw = CharToCell.get(fullCells[i].charAt(j)).tex;
				canvas.drawBitmap(toDraw, dx, dy, null);

				x+=cellWidth;
			}
			dy+=cellHeight;
		}
	}



}
