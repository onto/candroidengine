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
		
		spriteRenderer = new SurfaceRenderer();
		
		setFocusable(true);
		music = new BackgroundSound(context, "sounds/backgroundmusic.ogg", true);
		music.start();
		
		sfx = new SFXManager(context);
		
		sfx.addSound("TOM", "sounds/tom1.ogg");
		sfx.addSound("BASS", "sounds/bass.ogg");
		sfx.addSound("CLOSEDHAT", "sounds/closedhat.ogg");
		
		spriteRenderer.addRenderable(new Text(context, 10, 20, "Tap the Screen", Color.WHITE, 16, true));
		spriteRenderer.addRenderable(new Text(context, 10, 40, "Touch the Trackball", Color.WHITE, 16, true));
		
		
		setRendererAndStart(spriteRenderer);
		
	}
	
	@Override
	public void recycle() {
		// TODO Auto-generated method stub
		super.recycle();
	}
	
	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
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
		music.stop();
		super.onDestroy();
	}
	

}
