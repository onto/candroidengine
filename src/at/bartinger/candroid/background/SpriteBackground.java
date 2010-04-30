package at.bartinger.candroid.background;

import android.graphics.Canvas;
import at.bartinger.candroid.Renderable;
import at.bartinger.candroid.renderable.Sprite;

public class SpriteBackground extends Background{

	private Renderable[] renderables;
	private int boundx1,boundy1,boundx2,boundy2;


	public SpriteBackground(Renderable[] renderables, int boundx1, int boundy1, int boundx2, int boundy2) {	
		this.renderables = renderables;

		this.boundx1=boundx1;
		this.boundx2=boundx2;
		this.boundy1=boundy1;
		this.boundy2=boundy2;
	}

	@Override
	public void draw(Canvas canvas) {
		for(int i = 0; i < renderables.length; i++){
			renderables[i].draw(canvas);
		}
		super.draw(canvas);
	}

	@Override
	public void update() {
		for(int i = 0; i < renderables.length; i++){
			renderables[i].update();
			
			if(renderables[i].velocityX > 0){//RIGHT
				if(renderables[i].x >= boundx2){
					renderables[i].x=boundx1-renderables[i].width;
				}
			}
			if(renderables[i].velocityY > 0){//DOWN
				if(renderables[i].y >= boundy2){
					renderables[i].y = boundy1-renderables[i].height;
				}
			}

			if(renderables[i].velocityX < 0){//LEFT
				if(renderables[i].x <= boundx1 - renderables[i].width){
					renderables[i].x=boundx2;
				}
			}
			if(renderables[i].velocityY < 0){//UP
				if(renderables[i].y <= boundy1 - renderables[i].height){
					renderables[i].y=boundy2;
				}
			}

		}
	}

}
