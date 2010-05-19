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
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.renderable.Sprite;
import at.bartinger.candroid.renderable.TileAnimation;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class Example02View extends CandroidSurfaceView{
	
	
	private SurfaceRenderer spriteRenderer;

	Texture explosionTex;
	Texture marioTex;
	
	TileAnimation explosionAni;
	Sprite marioSprite;
	TileAnimation marioAni;
	
	public Example02View(Context context) {
		super(context);
		
		
		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();

		explosionTex = new Texture("graphics/explosion.png");
		marioTex = new Texture("graphics/mario_sprite.png");
		
		atlas.addTexture(explosionTex);
		atlas.addTexture(marioTex);
	
		TextureManager.load(context, atlas);
		
		//TileAnimation is for Animations in your game. There are three different methods to create an Animation.
		//First is this. The parameters are:
		//your loaded Texture, the x and y coordinate of your Animation,
		//then in how many columns the image should be cut
		//then in how many rows the image should be cut
		//and last the time between the frame and the next one (ms)
		explosionAni = new TileAnimation(explosionTex, 2, 2, 5, 5, 100);
		
		//The whole Sprite.
		marioSprite = new Sprite(marioTex, 10, 90);
		//And an animation of this Sprite (8 columns and 1 rows)
		//When you run the example you see the great effect of these Animation
		marioAni = new TileAnimation(marioTex, 10, 150, 8, 1, 100);
		
		//Add it to the Renderer
		spriteRenderer.addRenderable(explosionAni);
		spriteRenderer.addRenderable(marioSprite);
		spriteRenderer.addRenderable(marioAni);
		
		//And go
		setRendererAndStart(spriteRenderer);
	}
	
	@Override
	public void recycle() {
		explosionTex.recycle();
		marioTex.recycle();
		explosionAni.recycle();
		marioSprite.recycle();
		marioAni.recycle();
		super.recycle();
	}
	
	
	
	
}
