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
	
	public Example02View(Context context, boolean usesAccelerometer) {
		super(context, usesAccelerometer);
		
		
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
		//and last but not least the time between two frames (ms)
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
