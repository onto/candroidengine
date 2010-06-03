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


	//A new Renderer
	private CellRenderer cellrenderer;

	private Sprite[] clouds = new Sprite[5];

	private Background[] bgs = new Background[2];

	private MultibleBackground mbg;

	private int sx;


	public Example09View(Context context, DisplayMetrics dm) {
		super(context,dm);
		
		//This is one really cool example. Its about cells. This will replace Characters from a String[] with an image.
		//Just look at the example on your device.

		//For this we don't need the normal SurfaceRenderer.
		//We use the CellRenderer for this
		cellrenderer = new CellRenderer();

		TextureAtlas atlas = new TextureAtlas();

		Texture corner1 = new Texture("graphics/cells/cells_01.png");
		Texture corner2 = new Texture("graphics/cells/cells_03.png");
		Texture corneru1 = new Texture("graphics/cells/cells_07.png");
		Texture corneru2 = new Texture("graphics/cells/cells_09.png");
		Texture brown = new Texture("graphics/cells/cells_05.png");
		Texture top = new Texture("graphics/cells/cells_02.png");
		Texture border1 = new Texture("graphics/cells/cells_04.png");
		Texture border2 = new Texture("graphics/cells/cells_06.png");
		Texture bottom = new Texture("graphics/cells/cells_08.png");
		Texture space = new Texture("graphics/cells/spacer.png");

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

		atlas.addTexture(corner1);
		atlas.addTexture(corner2);
		atlas.addTexture(corneru1);
		atlas.addTexture(corneru2);
		atlas.addTexture(brown);
		atlas.addTexture(top);
		atlas.addTexture(border1);
		atlas.addTexture(border2);
		atlas.addTexture(bottom);
		atlas.addTexture(space);

		TextureManager.load(context, atlas);

		//Here we save in a map which Character will get replaced with witch Texture
		//Make sure that every texture have the same size.
		//My images are all 32x32 big
		HashMap<Character, Texture> interpreter = new HashMap<Character, Texture>();
		interpreter.put('0', corner1);
		interpreter.put('1', corner2);
		interpreter.put('2', corneru1);
		interpreter.put('3', corneru2);
		interpreter.put('4', brown);
		// 5 is missing because i removed one Texture and i didnt want to change the whole String[] level
		interpreter.put('6', top);
		interpreter.put('7', border1);
		interpreter.put('8', border2);
		interpreter.put('9', bottom);
		interpreter.put('-', space);

		//Here we say how the level looks like.
		String[] level = new String[]{
				"--------------------------0661------------------------------------------------------------",
				"--------------------------7448------------------------------------------------------------",
				"---------------------0661-7448------------------------------------0661--------------------",
				"---01----------------7448-7448---061------------061---------------7448--------061---------",
				"---23----------------7448-7448---748------------293---------------7448-0661---293---------",
				"----------------0661-7448-7448---748------------------------------7448-7448---------------",
				"----------0661--7448-7448-7448---748---066661----------0661--0661-2993-7448---061---066661",
				"----------2993--7448-7448-7448---293---299993----------2993--7448------7448---293---299993",
				"----------------7448-7448-7448-------------------------------7448------7448---------------",
				"0661-0661-------7448-7448-7448---------------0661----0661----7448------7448---------------",
		};

		//IMPORTANT
		//Now call the set method and set the map, the String[] and the image size (32x32)
		set(interpreter, level, 32, 32);
		cellrenderer.setMap(getMap());

		//You can just use The SCREEN_... variables if you used the constructor with the DisplayMetrics parameter.
		//Here we move the map to the bottom of the screen. It doesn't matter witch screensize the device has.
		//because of the SCREEN_HEIGHT!!!!!!
		cellrenderer.setMapPosition(0,SCREEN_HEIGHT-cellrenderer.mapHeight);

		
		//Here I just add the Cloud-Background from example 4.
		clouds[0] = new Sprite(cloudtextures[0], 10, 10);
		clouds[1] = new Sprite(cloudtextures[1], -50,20);
		clouds[2] = new Sprite(cloudtextures[2], -150,100);
		clouds[3] = new Sprite(cloudtextures[3], -120,14);
		clouds[4] = new Sprite(cloudtextures[4], -100,80);

		clouds[4].velocityX = 1;
		clouds[1].velocityX = 2;
		clouds[2].velocityX = 3;
		clouds[3].velocityX = 4;


		bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));
		bgs[1] = new SpriteBackground(clouds,0,0,320,480);


		mbg = new MultibleBackground(bgs);

		cellrenderer.setBackground(mbg);

		// and start
		setRendererAndStart(cellrenderer);
	}

	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		sx = touchX;
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
			//Make sure that you let the thread pause a little bit to free some cpu.
			//If you don't do that and you make in input the framerate will go terrible down
			Thread.sleep(16);
		} catch (InterruptedException e) {}
		super.onUpdate();
	}

}
