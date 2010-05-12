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

package at.bartinger.example;

import android.content.Context;
import android.graphics.Color;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.shape.Circle;
import at.bartinger.candroid.shape.Line;
import at.bartinger.candroid.shape.Point;
import at.bartinger.candroid.shape.Polygon;
import at.bartinger.candroid.shape.Rectangle;

public class Example10View extends CandroidSurfaceView {
	
	
	private SurfaceRenderer spriteRenderer;

	public Example10View(Context context) {
		super(context);
		
		spriteRenderer = new SurfaceRenderer();
		
		Circle myCircle = new Circle(10, 20, 20, Color.CYAN);
		
		Rectangle myRectangle = new Rectangle(100,20,30,50,Color.GREEN);
		
		Line myLine = new Line(0,0,400,400,Color.BLUE);
		myLine.setStrokeWidth(4);
		
		Point[] points = new Point[]{
				new Point(10,300),
				new Point(120,260),
				new Point(230,300),
				new Point(210,333),
				new Point(110,190),
				new Point(10,300)
		};
		
		Polygon myPolygon = new Polygon(points, Color.YELLOW);
		
		spriteRenderer.addRenderable(myLine);
		spriteRenderer.addRenderable(myCircle);
		spriteRenderer.addRenderable(myRectangle);
		spriteRenderer.addRenderable(myPolygon);

		setRendererAndStart(spriteRenderer);
	}
	
}