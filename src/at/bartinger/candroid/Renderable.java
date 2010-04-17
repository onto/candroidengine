
/**
 * This is an open source canvas based library.
 * You can use, edit and modify whatever you want.
 * 
 * http://bartinger.blogspot.com/
 */


package at.bartinger.candroid;

import android.graphics.Canvas;

/**
 * 
 * @author dominic
 * This is the base class of everything witch is able to be rendered by an SurfaceRenderer
 */

public abstract class Renderable {

	// Position.
	public int x;
	public int y;
	public int z;

	// Velocity.
	public int velocityX;
	public int velocityY;
	public int velocityZ;
	
	//Acceleration
	public int accelerationX;
	public int accelerationY;
	public int accelerationZ;

	// Size.
	public int width;
	public int height;
	
	public void draw(Canvas canvas){}
	
	public void update(){
		x+=velocityX;
		y+=velocityY;
		z+=velocityZ;
		
		velocityX+=accelerationX;
		velocityY+=accelerationY;
		velocityZ+=accelerationZ;
	}
}

