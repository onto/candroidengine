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
import android.util.DisplayMetrics;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.FixedBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.ScrollingBackground;
import at.bartinger.candroid.renderable.TileAnimation;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class Example03View extends CandroidSurfaceView {
	
	
	private SurfaceRenderer spriteRenderer;
	
	
	Texture blueBackground;
	Texture slowBackground;
	Texture mario;
	
	FixedBackground bluescreen;
	ScrollingBackground town;
	MultibleBackground allinone;
	
	TileAnimation marioAni;
	
	int oldX = 10;

	public Example03View(Context context, DisplayMetrics dm) {
		super(context, dm);

		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();

		blueBackground = new Texture("graphics/backgrounds/blue_bg.png");
		slowBackground = new Texture("graphics/backgrounds/town_bg.png");
		
		mario = new Texture("graphics/mario_sprite.png");

		atlas.addTexture(mario);
		atlas.addTexture(blueBackground);
		atlas.addTexture(slowBackground);
	
		
		TextureManager.load(context, atlas);
		
		marioAni = new TileAnimation(mario, 10, 100, 8, 1, 100);
		
		//Create an new FixedBackground
		//parameter is a Texture
		//This Background is fixed and can't move
		bluescreen = new FixedBackground(blueBackground);
		
		//This is a ScrollingBackground you can scroll it in x and y direction.
		//town.setOffset(offsetX, offsetY); This moves the Background around the given offsets
		//town.setSpeed(autooffsetX, autooffsetY); This moves the Background around the given offsets every update
		town = new ScrollingBackground(slowBackground);
		town.setOffset(0, SCREEN_HEIGHT-town.height);
		
		//Create an Background Array for the MultibleBackground
		Background[] myBGs = new Background[]{
				bluescreen,town
		};
		
		//With a MultibleBackground you can combine several Backgrounds
		allinone = new MultibleBackground(myBGs);

		spriteRenderer.addRenderable(marioAni);
		
		//Set the MultibleBackgound to the Renderer
		spriteRenderer.setBackground(allinone);

		setRendererAndStart(spriteRenderer);
	}
	
	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
		
		//Move the Backgrounds slower than Mario
		marioAni.x = touchX;
		town.x+=(oldX-marioAni.x)/8;
		oldX=(int) marioAni.x;
		
	}
	
	@Override
	public void recycle() {
		mario.recycle();
		blueBackground.recycle();
		slowBackground.recycle();
	}
	
	
	
	
	
}