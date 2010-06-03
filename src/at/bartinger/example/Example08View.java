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
import android.hardware.Sensor;
import at.bartinger.candroid.AccelerometerListener;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.AccelerometerListener.AccelerationListener;
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderable.TileAnimation;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.sound.SFXManager;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class Example08View extends CandroidSurfaceView{


	private SurfaceRenderer spriteRenderer;

	private AccelerometerListener myShake;

	private Text event;
	private Text info_accuracy;
	private Text info_x;
	private Text info_y;
	private Text info_z;

	Texture marioTex;
	TileAnimation marioAni;

	private SFXManager sfx;

	private float x;



	public Example08View(Context context) {
		super(context);
		
		//This is an example about the Accelerometer

		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();

		marioTex = new Texture("graphics/mario_sprite.png");

		atlas.addTexture(marioTex);

		TextureManager.load(context, atlas);

		marioAni = new TileAnimation(marioTex, 10, 250, 8, 1, 100);

		sfx = new SFXManager(context);

		sfx.addSound("TOM", "sounds/tom1.ogg");
		
		
		//I initialize the AccelerometerListener
		myShake = new AccelerometerListener(context, new AccelerationListener() {

			//I also include an shakeListener
			//You can set the settings of how strong the device should be shaking or other things.
			//These are the settings you can change
			//FORCE_THRESHOLD
			//TIME_THRESHOLD
			//SHAKE_TIMEOUT
			//SHAKE_DURATION
			//SHAKE_COUNT
			@Override
			public void onShake() {
				sfx.play("TOM");
			}

			//And here you get the normal x,y and z coordinates of the device
			@Override
			public void onAccelerometerChange(float x, float y, float z) {
				Example08View.this.x = x;

				info_x.setText("x: "+x);
				info_y.setText("y: "+y);
				info_z.setText("z: "+z);
			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy){				
				info_accuracy.setText("Accuracy: "+accuracy);
			}
		});

		event = new Text(context, 30, 100, "Shake Me", Color.WHITE, 18, true);
		info_accuracy = new Text(context, 30, 125, "Accuracy: 0", Color.WHITE, 18, true);
		info_x = new Text(context, 30, 150, "x: 0", Color.WHITE, 18, true);
		info_y = new Text(context, 30, 175, "y: 0", Color.WHITE, 18, true);
		info_z = new Text(context, 30, 200, "z: 0", Color.WHITE, 18, true);


		spriteRenderer.addRenderable(event);
		spriteRenderer.addRenderable(info_accuracy);
		spriteRenderer.addRenderable(info_x);
		spriteRenderer.addRenderable(info_y);
		spriteRenderer.addRenderable(info_z);
		spriteRenderer.addRenderable(marioAni);

		setRendererAndStart(spriteRenderer);
	}

	@Override
	public void onUpdate() {
		marioAni.x += ((int)x)*(-1);
	}

	@Override
	public void onDestroy() {
		myShake.stop();
		super.onDestroy();
	}


}
