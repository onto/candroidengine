package at.bartinger.candroid.ingame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import at.bartinger.candroid.Renderable;

public class Text extends Renderable{
	
	private String text = "";
	private Paint paint = new Paint();
	
	public Text(Context context, int x, int y, String text, int color, int textSize, boolean bold, String fontPath) {
		this.text = text;
		this.x = x;
		this.y = y;
		
		paint.setTextSize(textSize);
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setFakeBoldText(bold);
		paint.setTypeface(Typeface.createFromAsset(context.getAssets(),fontPath));
	}
	
	public Text(Context context, int x, int y, String text, int color, int textSize, boolean bold) {
		this.text = text;
		this.x = x;
		this.y = y;
		
		paint.setTextSize(textSize);
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setFakeBoldText(bold);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(text, (float)x, (float)y, paint);
		super.draw(canvas);
	}

}
