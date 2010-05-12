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