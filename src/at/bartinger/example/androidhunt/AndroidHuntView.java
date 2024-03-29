package at.bartinger.example.androidhunt;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.SpriteBackground;
import at.bartinger.candroid.ingame.Score;
import at.bartinger.candroid.ingame.Text;
import at.bartinger.candroid.renderable.Sprite;
import at.bartinger.candroid.renderer.SurfaceRenderer;
import at.bartinger.candroid.texture.Texture;
import at.bartinger.candroid.texture.TextureAtlas;
import at.bartinger.candroid.texture.TextureManager;

public class AndroidHuntView extends CandroidSurfaceView{


	private Context context;

	//Stuff for rendering
	private SurfaceRenderer spriteRenderer;
	private Sprite[] clouds = new Sprite[5];
	private Background[] bgs = new Background[2];
	private MultibleBackground mbg;

	private Score score;
	//stuff for the game
	private int spawndelay = 700;
	private long lasttime;
	private long pasttime;
	Texture ducktex;
	private ArrayList<Sprite> ducks = new ArrayList<Sprite>();
	private boolean stopped = false;

	public AndroidHuntView(Context context, DisplayMetrics dm) {
		super(context, dm);

		this.context = context;

		spriteRenderer = new SurfaceRenderer();

		TextureAtlas atlas = new TextureAtlas();

		score = new Score(context, 20, 20, "Score: ", Color.BLACK, 20, true);
		score.score = 0;

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
		atlas.addTexture(ducktex = new Texture("graphics/androidlogoghost.png"));

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
		clouds[3].velocityX = 4;
		clouds[4].velocityX = 1;

		//Create just a normal Background
		bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));

		//Create a SpriteBackground
		//The parameters are
		bgs[1] = new SpriteBackground(clouds,0,0,dm.widthPixels,dm.heightPixels);

		//Put both backgrounds in the MultibleBackground
		mbg = new MultibleBackground(bgs);

		//Set the background to the Renderer...
		spriteRenderer.setBackground(mbg);

		lasttime = System.currentTimeMillis();

		spriteRenderer.addRenderable(score);

		addnewDuck();

		setRendererAndStart(spriteRenderer);
	}

	//Make your game updates here...
	@Override
	public void onUpdate() {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {}
		if(!stopped){
			pasttime += (System.currentTimeMillis() - lasttime);
			lasttime = System.currentTimeMillis();
			if(pasttime > spawndelay){
				pasttime-=spawndelay;
				addnewDuck();
			}
			if(ducks.size() > 15){
				stopped = true;
				spriteRenderer.addRenderable(new Text(context, 50, 50, "GAME OVER!", Color.RED, 25, true));
				spriteRenderer.addRenderable(new Text(context, 50, 80, "Your score: " + score.score, Color.RED, 25, true));
			}
		}
	}

	//
	private void addnewDuck(){
		Sprite nduck = new Sprite(ducktex, (int)(Math.random()*(SCREEN_WIDTH-70)), (int)(Math.random()*(SCREEN_HEIGHT-70)));
		spriteRenderer.addRenderable(nduck);
		ducks.add(nduck);
	}

	@Override
	public void onTouchDown(int touchX, int touchY, int pressure) {
		if(!stopped){
			for(Sprite d : ducks){
				if(d.pointOnSprite(touchX, touchY)){
					spriteRenderer.removeRenderable(d);
					ducks.remove(d);
					score.score+=10;
					if(spawndelay > 200){
						spawndelay-=3;
					}
					break;

				}
			}
		}
		super.onTouchDown(touchX, touchY, pressure);
	}

}
