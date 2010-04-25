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
        public double x;
        public double y;
        public double z;

        // Velocity.
        public double velocityX;
        public double velocityY;
        public double velocityZ;
        
        //Acceleration
        public double accelerationX = 1;
        public double accelerationY = 1;
        public double accelerationZ = 1;

        // Size.
        public int width;
        public int height;
        
        public void draw(Canvas canvas){}
        
        public void update(){
                x+=velocityX;
                y+=velocityY;
                z+=velocityZ;
                
                velocityX*=accelerationX;
                velocityY*=accelerationY;
                velocityZ*=accelerationZ;
        }
}