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

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import at.bartinger.candroid.CandroidCellView;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.SpriteBackground;
import at.bartinger.candroid.renderable.Sprite;
import at.bartinger.candroid.renderer.CellRenderer;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class Example09View extends CandroidCellView{


	private CellRenderer cellrenderer;

	private Sprite[] clouds = new Sprite[5];

	private Background[] bgs = new Background[2];

	private MultibleBackground mbg;

	private int sx;
	private int sy;


	public Example09View(Context context, DisplayMetrics dm) {
		super(context);

		cellrenderer = new CellRenderer();

		//Texturen machen und laden
		TextureAtlas atlas = new TextureAtlas();

		Texture corner1 = new Texture("graphics/cells/ecke1.png");
		Texture corner2 = new Texture("graphics/cells/ecke2.png");
		Texture corneru1 = new Texture("graphics/cells/eckeu1.png");
		Texture corneru2 = new Texture("graphics/cells/eckeu2.png");
		Texture brown = new Texture("graphics/cells/erde.png");
		Texture brownk = new Texture("graphics/cells/erdek1.png");
		Texture top = new Texture("graphics/cells/oben.png");
		Texture border1 = new Texture("graphics/cells/rand1.png");
		Texture border2 = new Texture("graphics/cells/rand2.png");
		Texture bottom = new Texture("graphics/cells/unten.png");
		Texture space = new Texture("graphics/cells/spacer.png");

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

		atlas.addTexture(corner1);
		atlas.addTexture(corner2);
		atlas.addTexture(corneru1);
		atlas.addTexture(corneru2);
		atlas.addTexture(brown);
		atlas.addTexture(brownk);
		atlas.addTexture(top);
		atlas.addTexture(border1);
		atlas.addTexture(border2);
		atlas.addTexture(bottom);
		atlas.addTexture(space);

		TextureManager.load(context, atlas);

		HashMap<Character, Texture> interpreter = new HashMap<Character, Texture>();
		interpreter.put('0', corner1);
		interpreter.put('1', corner2);
		interpreter.put('2', corneru1);
		interpreter.put('3', corneru2);
		interpreter.put('4', brown);
		interpreter.put('5', brownk);
		interpreter.put('6', top);
		interpreter.put('7', border1);
		interpreter.put('8', border2);
		interpreter.put('9', bottom);
		interpreter.put('-', space);

		String[] level = new String[]{
				"--------------------------0661------------------------------------------------------------",
				"--------------------------7558------------------------------------------------------------",
				"---------------------0661-7458------------------------------------0661--------------------",
				"---01----------------7558-7448---061------------061---------------7558--------061---------",
				"---23----------------7458-7548---748------------293---------------7458-0661---293---------",
				"----------------0661-7548-7548---748------------------------------7548-7548---------------",
				"----------0661--7458-7448-7448---748---066661----------0661--0661-2993-7448---061---066661",
				"----------2993--7548-7448-7448---293---299993----------2993--7548------7448---293---299993",
				"----------------7448-7548-7548-------------------------------7448------7548---------------",
				"0661-0661-------7548-7448-7448---------------0661----0661----7548------7448---------------",
		};

		set(interpreter, level, 32, 32);
		cellrenderer.setMap(getMap());

		cellrenderer.setMapPosition(0,dm.heightPixels-cellrenderer.mapHeight);

		clouds[0] = new Sprite(cloudtextures[0], 10, 10);
		clouds[1] = new Sprite(cloudtextures[1], -50,20);
		clouds[2] = new Sprite(cloudtextures[2], -150,100);
		clouds[3] = new Sprite(cloudtextures[3], -120,14);
		clouds[4] = new Sprite(cloudtextures[4], -100,80);

		clouds[4].velocityX = 0.7;
		clouds[1].velocityX = 0.6d;
		clouds[2].velocityX = 0.5d;
		clouds[3].velocityX = 0.4;


		bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));
		bgs[1] = new SpriteBackground(clouds,0,0,320,480);


		mbg = new MultibleBackground(bgs);

		cellrenderer.setBackground(mbg);

		setRendererAndStart(cellrenderer);
	}

	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		sx = touchX;
		sy = touchY;
	}

	@Override
	public void onTouchMove(int touchX, int touchY, int pressure) {
		int newX = touchX-sx;
		if(cellrenderer.x+newX <= 0){
			cellrenderer.x += newX;
			
		}else{
			cellrenderer.x = 0;
		}
		sx = touchX;
	}

	@Override
	public void onUpdate() {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {}
		super.onUpdate();
	}

}
