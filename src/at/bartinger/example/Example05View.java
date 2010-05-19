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
import android.view.MotionEvent;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.sound.BackgroundSound;
import at.bartinger.candroid.sound.SFXManager;

public class Example05View extends CandroidSurfaceView{

	private SFXManager sfx;
	private BackgroundSound music;
	private SurfaceRenderer spriteRenderer;
	
	
	public Example05View(Context context) {
		super(context);
		
		//This is an example about SFX and background sounds in games.
		//The BackgroundSound is for music to loop a song for instance.
		//The SFXManager is for fast playback in games.
		//For example a shot of a gun or other sound effects.
		setFocusable(true);
		spriteRenderer = new SurfaceRenderer();
		
		
		//Here we initialize the music
		//The paramethers are the application context, the path to the sound file, and a boolean if the sound should loop.
		music = new BackgroundSound(context, "sounds/backgroundmusic.ogg", true);
		music.start();
		
		//Here initialize the SFXManager for some playback
		sfx = new SFXManager(context);
		
		//add some sounds to the manager
		//the first parameter is a name, you can access and play the sound via the name
		//and the second parameter is the path again of the sound file
		sfx.addSound("TOM", "sounds/tom1.ogg");
		sfx.addSound("BASS", "sounds/bass.ogg");
		sfx.addSound("CLOSEDHAT", "sounds/closedhat.ogg");
		
		
		//add some info
		spriteRenderer.addRenderable(new Text(context, 10, 20, "Tap the Screen", Color.WHITE, 16, true));
		spriteRenderer.addRenderable(new Text(context, 10, 40, "Touch the Trackball", Color.WHITE, 16, true));
		
		//and start
		setRendererAndStart(spriteRenderer);
		
	}
	
	@Override
	public void recycle() {
		// TODO Auto-generated method stub
		super.recycle();
	}
	
	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		
		//when you touch the screen it plays the sound with the name "TOM"
		sfx.play("TOM");
		super.onTouchDown(touchX, touchY, pressure);
	}
	
	@Override
	public void onTouchUp(int touchX, int touchY, int pressure) {
		sfx.play("BASS");
		super.onTouchUp(touchX, touchY, pressure);
	}
	
	@Override
	public boolean onTrackballEvent(MotionEvent event) {
		sfx.play("CLOSEDHAT");
		return super.onTrackballEvent(event);
	}
	
	@Override
	public void onDestroy() {
		
		//if you dont stop the music it would simply continue also if you close the app
		music.stop();
		super.onDestroy();
	}
	

}
