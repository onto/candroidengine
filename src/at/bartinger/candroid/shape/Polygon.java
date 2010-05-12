package at.bartinger.candroid.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import at.bartinger.candroid.Renderable;

public class Polygon extends Renderable{

	public float[] pts;
	private Paint mPaint = new Paint();
	
	public Polygon(Point [] points, int color) {
		pts = new float[points.length*2];
		for(int i = 0; i < points.length;i+=2){
			pts[i] = points[i].x;
			pts[i+1] = points[i].y;
		}
		
		mPaint.setColor(color);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(2);
	}
	
	public void setStrokeWidth(float w){
		mPaint.setStrokeWidth(w);
	}
	
	public Polygon(float [] points, int color) {
		pts = points;
		
		mPaint.setColor(color);
		mPaint.setAntiAlias(true);
		mPaint.setStrokeWidth(2);
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawLines(pts, mPaint);
	}
	
}
