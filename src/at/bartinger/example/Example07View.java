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
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderer.SurfaceRenderer;

public class Example07View extends CandroidSurfaceView{
	
	
	private SurfaceRenderer spriteRenderer;
	
	private String label = "Current Event: ";
	private Text event;
	
	private Text move;


	public Example07View(Context context) {
		super(context);
		
		//This is an example about events.
		
		spriteRenderer = new SurfaceRenderer();
		
		event = new Text(context, 20, 20, label, Color.WHITE, 18, true);
		move = new Text(context, 100, 100, "onTouchMove", Color.WHITE, 18, true);
		
		spriteRenderer.addRenderable(event);
		spriteRenderer.addRenderable(move);

		setRendererAndStart(spriteRenderer);
	}
	
	
	//Here you can see some events from the input events.
	//I think if you read the name you will understand what input the user did to get this event called.
	@Override
	public void onTrackballDown() {
		event.setText(label + "onTrackballDown");
	}
	
	@Override
	public void onTrackballUp() {
		event.setText(label + "onTrackballUp");
	}
	
	@Override
	public void onTrackballLeft() {
		event.setText(label + "onTrackballLeft");
	}
	
	@Override
	public void onTrackballRight() {
		event.setText(label + "onTrackballRight");
	}
	
	@Override
	public void onTrackballPress() {
		event.setText(label + "onTrackballPress");
	}
	
	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		event.setText(label + "onTouchDown");
	}
	
	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
		move.x = touchX;
		move.y = touchY;
	}
	
	@Override
	public void onTouchUp(int touchX, int touchY, int pressure) {
		event.setText(label + "onTouchUp");
	}

	@Override
	public void onDPadCenter() {
		event.setText(label + "onDPadCenter");
	}
	
	@Override
	public void onDPadDown() {
		event.setText(label + "onDPadDown");
	}
	
	@Override
	public void onDPadUp() {
		event.setText(label + "onDPadUp");
	}
	
	@Override
	public void onDPadLeft() {
		event.setText(label + "onDPadLeft");
	}
	
	@Override
	public void onDPadRight() {
		event.setText(label + "onDPadRight");
	}
}
