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

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import android.widget.Toast;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.Constants;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;
import at.bartinger.candroid.background.FixedBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.ScrollingBackground;
import at.bartinger.candroid.background.SpriteBackground;
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderable.CellSprite;
import at.bartinger.candroid.renderable.Sprite;
import at.bartinger.candroid.renderable.TileAnimation;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class Example04View extends CandroidSurfaceView {

	private Context context;
	private SurfaceRenderer spriteRenderer;
	private TileAnimation marioAnimation;
	private TileAnimation sun;
	private Sprite sun2;
	private TileAnimation sonicAnimation;
	private Sprite marioSprite;
	private Sprite[] clouds = new Sprite[5];
	private TileAnimation explosion;
	private CellSprite box;
	private Text fps;

	// private Sprite[] clouds = new Sprite[5];

	private Paint whitetext = new Paint();

	private Background[] bgs = new Background[2];

	private MultibleBackground mbg;

	private int startX;
	private int startY;



	public Example04View(Context context ,boolean usesAccelerometer) {
		super(context, usesAccelerometer);
		this.context = context;

		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();


		Texture[] cloudtextures = new Texture[]{
				new Texture("graphics/sun2.gif"),
				new Texture("graphics/clouds/cloud1.png"),
				new Texture("graphics/clouds/cloud2.png"),
				new Texture("graphics/clouds/cloud3.png"),
				new Texture("graphics/clouds/cloud4.png"),
		};
		for(int i = 0; i < cloudtextures.length; i++ ){
			atlas.addTexture(cloudtextures[i]);
		}




		Texture[] sonictextures = new Texture[]{
				new Texture("graphics/sonic/sonic1.png"),
				new Texture("graphics/sonic/sonic2.png"),
				new Texture("graphics/sonic/sonic3.png"),
				new Texture("graphics/sonic/sonic4.png"),
				new Texture("graphics/sonic/sonic5.png"),
				new Texture("graphics/sonic/sonic6.png")
		};
		for(int i = 0; i < sonictextures.length; i++ ){
			atlas.addTexture(sonictextures[i]);
		}
		
		TextureManager.load(context, atlas);
		
		


//		sonicAnimation = new TileAnimation(context, 100, 100, sonicSprites, 100);
//		explosion = new TileAnimation(context, 100, 250, "graphics/explosion.png", 5, 5, 60);
//		marioAnimation = new TileAnimation(context, 10, 100,"graphics/mario_sprite.png", 8, 1, 100);
		fps = new Text(context,20,20,"fps: ",Color.WHITE,16,true,"fonts/Purisa-Oblique.ttf");

		//                Sprite test;
		//                Map<Character,Sprite> charsAndPaths= new HashMap<Character,Sprite>();
		//                charsAndPaths.put('o', test = new Sprite(context,"graphics/cells/oben.png",0,0));
		//                charsAndPaths.put('l', new Sprite(context,"graphics/cells/ecke1.png",0,0));
		//                charsAndPaths.put('r', new Sprite(context,"graphics/cells/ecke2.png",0,0));
		//                charsAndPaths.put('m', new Sprite(context,"graphics/cells/erde.png",0,0));
		//                charsAndPaths.put('u', new Sprite(context,"graphics/cells/unten.png",0,0));
		//                charsAndPaths.put('k', new Sprite(context,"graphics/cells/erdek1.png",0,0));
		//                charsAndPaths.put('1', new Sprite(context,"graphics/cells/rand1.png",0,0));
		//                charsAndPaths.put('2', new Sprite(context,"graphics/cells/rand2.png",0,0));
		//                charsAndPaths.put('3', new Sprite(context,"graphics/cells/eckeu1.png",0,0));
		//                charsAndPaths.put('4', new Sprite(context,"graphics/cells/eckeu2.png",0,0));
		//                
		//                String[] boxString = new String[4];
		//                boxString[0]="looor";
		//                boxString[1]="1mkm2";
		//                boxString[2]="1mmm2";
		//                boxString[3]="3uuu4";

		//                box = new CellSprite(0, 0, 64, 64, charsAndPaths, boxString);

		Log.e(Constants.LOGTAG, "Loading finished");
		for(int i = 0; i < cloudtextures.length; i++){
			if(cloudtextures[i].tex == null) Log.e(Constants.LOGTAG, cloudtextures[i].path + "is null");
		}

		clouds[0] = new Sprite(cloudtextures[0], 10, 10);
		clouds[1] = new Sprite(cloudtextures[1], -50,20);
		clouds[2] = new Sprite(cloudtextures[2], -150,100);
		clouds[3] = new Sprite(cloudtextures[3], -120,14);
		clouds[4] = new Sprite(cloudtextures[4], -100,80);

		clouds[4].velocityX = 1;
		clouds[1].velocityX = 1.3d;
		clouds[2].velocityX = 1.6d;
		clouds[3].velocityX = 2;

//		spriteRenderer.addSprite(marioSprite);
//		spriteRenderer.addSprite(marioAnimation);
//		spriteRenderer.addSprite(sonicAnimation);
//		spriteRenderer.addSprite(explosion);

		bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));
		bgs[1] = new SpriteBackground(clouds,0,0,320,480);


		mbg = new MultibleBackground(bgs);

		spriteRenderer.setBackground(mbg);

		setRendererAndStart(spriteRenderer);
	}


	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {

	}

	@Override
	public void onShaking() {
		Toast.makeText(context, "SHAKE ME!", Toast.LENGTH_SHORT).show();
		super.onShaking();
	}

	@Override
	public void onStopDrawing(Canvas canvas) {
		fps.setText("fps: " + FPS);
	}
	
	public void recycle(){
		for(int i =0; i < clouds.length; i++){
			clouds[i].sprite.recycle();
		}
	}

}