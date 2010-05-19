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
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.SpriteBackground;
import at.bartinger.candroid.renderable.Sprite;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class Example04View extends CandroidSurfaceView {

	private SurfaceRenderer spriteRenderer;
	private Sprite[] clouds = new Sprite[5];

	private Background[] bgs = new Background[2];

	private MultibleBackground mbg;



	public Example04View(Context context) {
		super(context);
		
		//In this example we make another type of the backgrounds.
		//In the SpriteBackground just contains sprites and let them respawn them if they move out of the an fixed area. 

		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();


		Texture[] cloudtextures = new Texture[]{
				new Texture("graphics/sun.png"),
				new Texture("graphics/clouds/cloud1.png"),
				new Texture("graphics/clouds/cloud2.png"),
				new Texture("graphics/clouds/cloud3.png"),
				new Texture("graphics/clouds/cloud4.png"),
		};
		for(int i = 0; i < cloudtextures.length; i++ ){
			atlas.addTexture(cloudtextures[i]);
		}
		
		TextureManager.load(context, atlas);

		//Put the sprites for the background together in an array.
		clouds[0] = new Sprite(cloudtextures[0], 10, 10);
		clouds[1] = new Sprite(cloudtextures[1], -50,20);
		clouds[2] = new Sprite(cloudtextures[2], -150,100);
		clouds[3] = new Sprite(cloudtextures[3], -120,14);
		clouds[4] = new Sprite(cloudtextures[4], -100,80);

		
		//Set a speed the the clouds
		clouds[1].velocityX = 3;
		clouds[2].velocityX = 2;
		clouds[3].velocityX = 4d;
		clouds[4].velocityX = 1;

		//Create just a normal Background
		bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));
		
		//Create a SpriteBackground
		//The parameters are
		bgs[1] = new SpriteBackground(clouds,0,0,320,480);

		//Put both backgrounds in the MultibleBackground
		mbg = new MultibleBackground(bgs);

		//Set the background to the Renderer...
		spriteRenderer.setBackground(mbg);

		
		//...and start the game.
		setRendererAndStart(spriteRenderer);
	}
	
	@Override
	public void onUpdate() {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void recycle(){
		for(int i =0; i < clouds.length; i++){
			clouds[i].sprite.recycle();
		}
	}

}