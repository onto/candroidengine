package at.bartinger.example;

import android.content.Context;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.FixedBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.ScrollingBackground;
import at.bartinger.candroid.renderable.Sprite;
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

	public Example03View(Context context ,boolean usesAccelerometer) {
		super(context, usesAccelerometer);

		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();

		blueBackground = new Texture("graphics/backgrounds/blue_bg.jpg");
		slowBackground = new Texture("graphics/backgrounds/town_bg.png");
		
		mario = new Texture("graphics/mario_sprite.png");

		atlas.addTexture(mario);
		atlas.addTexture(blueBackground);
		atlas.addTexture(slowBackground);
	
		
		TextureManager.load(context, atlas);
		
		marioAni = new TileAnimation(mario, 10, 100, 8, 1, 100);
		
		bluescreen = new FixedBackground(blueBackground);
		town = new ScrollingBackground(slowBackground);
		Background[] myBGs = new Background[]{
				bluescreen,town
		};
		
		allinone = new MultibleBackground(myBGs);
		
		spriteRenderer.addRenderable(marioAni);
		
		spriteRenderer.setBackground(allinone);

		setRendererAndStart(spriteRenderer);
	}
	
	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
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