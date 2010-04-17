package at.bartinger.candroid.ingame;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;

public class Score {

	public int score = 0;
	public String label;
	public int x = 10;
	public int y = 10;
	
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
	
	
}
