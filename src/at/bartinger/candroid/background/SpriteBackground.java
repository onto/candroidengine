package at.bartinger.candroid.background;

import android.graphics.Canvas;
import at.bartinger.candroid.sprite.Sprite;

public class SpriteBackground extends Background{

	private Sprite[] sprites;

	public SpriteBackground(Sprite[] sprites) {	
		for(int i =0; i< sprites.length; i++){
			this.sprites[i] = sprites[i];
		}
	}

	@Override
	public void draw(Canvas canvas) {
		for(int i = 0; i < sprites.length; i++){
			sprites[i].draw(canvas);
		}
		super.draw(canvas);
	}
	
	@Override
	public void update() {
		for(int i = 0; i < sprites.length; i++){
			sprites[i].update();
		}
	}

}
