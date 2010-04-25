package at.bartinger.candroid.ingame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import at.bartinger.candroid.Renderable;

public class Score extends Renderable{

	public int score = 0;
	public String label;
	
	public Paint mPaint = new Paint();
	
	public Score(Context context, int x, int y, String label, int color, int textSize, boolean bold, String fontPath) {
		this.x = x;
		this.y = y;
		this.label = label;
		
		mPaint.setTextSize(textSize);
		mPaint.setColor(color);
		mPaint.setAntiAlias(true);
		mPaint.setFakeBoldText(bold);
		mPaint.setTypeface(Typeface.createFromAsset(context.getAssets(),fontPath));
	}
	
	
	public Score(Context context, int x, int y, String label, int color, int textSize, boolean bold) {
		this.x = x;
		this.y = y;
		this.label = label;
		
		mPaint.setTextSize(textSize);
		mPaint.setColor(color);
		mPaint.setAntiAlias(true);
		mPaint.setFakeBoldText(bold);
	}
	
	public void setColor(int color){
		mPaint.setColor(color);
	}
	
	@Override
	public String toString() {
		return label+score;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(label+score, (float)x, (float)y, mPaint);
		super.draw(canvas);
	}
	
	
}
