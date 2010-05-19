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
import at.bartinger.candroid.ingame.Score;
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderer.SurfaceRenderer;

public class Example06View extends CandroidSurfaceView{
	
	
	private SurfaceRenderer spriteRenderer;
	
	private Text welcome;
	private Text press;
	private Score gamescore;

	public Example06View(Context context) {
		super(context);
		
		//This is an really easy example about text and fonts.
		
		spriteRenderer = new SurfaceRenderer();
		
		//just create an new Text with the parameters:
		//the application context, the x and y coordinate, then the text, the color, the text size, a boolean for bold text or not, and then the path to the font 
		welcome = new Text(context, 20, 20, "Welcome to the Text and Fonts Tutorial", Color.WHITE, 18, true, "fonts/GeosansLight.ttf");
		press = new Text(context, 20, 150, "Pressure: 0", Color.WHITE, 50, true, "fonts/GeosansLight.ttf");
		gamescore = new Score(context, 20,100, "Your Score: ", Color.GREEN,35,false,"fonts/digital.ttf");
		
		//add them to the renderer
		spriteRenderer.addRenderable(welcome);	
		spriteRenderer.addRenderable(gamescore);
		spriteRenderer.addRenderable(press);
		
		// and start
		setRendererAndStart(spriteRenderer);
	}
	
	
	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		press.setText("Pressure: " + pressure);
	}
	
	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
		gamescore.score++;
	}

}
