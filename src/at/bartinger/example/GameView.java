package at.bartinger.example;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.widget.Toast;
import at.bartinger.candroid.CandroidSurfaceView;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;
import at.bartinger.candroid.background.FixedBackground;
import at.bartinger.candroid.background.MultibleBackground;
import at.bartinger.candroid.background.ScrollingBackground;
import at.bartinger.candroid.background.SpriteBackground;
import at.bartinger.candroid.renderable.CellSprite;
import at.bartinger.candroid.renderable.Sprite;
import at.bartinger.candroid.renderable.TileAnimation;
import at.bartinger.candroid.renderer.SurfaceRenderer;

public class GameView extends CandroidSurfaceView {

        private Context context;
        private SurfaceRenderer spriteRenderer;
        private TileAnimation marioAnimation;
        private TileAnimation sun;
        private Sprite sun2;
        private TileAnimation sonicAnimation;
        private Sprite marioSprite;
        private TileAnimation explosion;
        private CellSprite box;
        
        private Sprite[] clouds = new Sprite[5];

        private Paint whitetext = new Paint();
        
        private Background[] bgs = new Background[2];
        private ScrollingBackground sbg;
        private MultibleBackground mbg;
        
        private int startX;
        private int startY;
        


        public GameView(Context context, boolean usesTrackball,boolean usesAccelerometer, double shakeStrongnes,long TimeBetweenShakes) {
                super(context, usesTrackball, usesAccelerometer, shakeStrongnes,TimeBetweenShakes);
                this.context = context;
                
               
                

                whitetext.setStrokeWidth(2);
                whitetext.setTextSize(16);
                whitetext.setColor(Color.WHITE);
                whitetext.setFakeBoldText(true);
                whitetext.setAntiAlias(true);
                whitetext.setTextAlign(Align.CENTER);

                spriteRenderer = new SurfaceRenderer();


                String[] sonicSprites = new String[]{
                                "graphics/sonic/sonic1.png",
                                "graphics/sonic/sonic2.png",
                                "graphics/sonic/sonic3.png",
                                "graphics/sonic/sonic4.png",
                                "graphics/sonic/sonic5.png",
                                "graphics/sonic/sonic6.png"
                };


//                marioSprite = new Sprite(context,"graphics/mario_sprite.png", 200,10);
//                sonicAnimation = new TileAnimation(context, 100, 100, sonicSprites, 100);
//                explosion = new TileAnimation(context, 100, 250, "graphics/explosion.png", 5, 5, 60);
//                sun = new TileAnimation(context, 20, 25, "graphics/sun.png", 3, 1, 100);
//                marioAnimation = new TileAnimation(context, 10, 100,"graphics/mario_sprite.png", 8, 1, 100);
                
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
                
//                clouds[0] = new Sprite(context, "graphics/sun2.gif", 10, 10);
//                clouds[1] = new Sprite(context,"graphics/clouds/cloud2.png", -50,20);
//                clouds[2] = new Sprite(context,"graphics/clouds/cloud3.png", -150,100);
//                clouds[3] = new Sprite(context,"graphics/clouds/cloud4.png", -120,14);
//                clouds[4] = new Sprite(context,"graphics/clouds/cloud1.png", -100,80);
//                
//                clouds[4].velocityX = 1;
//                clouds[1].velocityX = 1.3d;
//                clouds[2].velocityX = 1.6d;
//                clouds[3].velocityX = 2;

//                spriteRenderer.addSprite(marioSprite);
//                spriteRenderer.addSprite(marioAnimation);
//                spriteRenderer.addSprite(sonicAnimation);
//                spriteRenderer.addSprite(explosion);
                
//                bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));
//                bgs[1] = new SpriteBackground(clouds,0,0,320,480);
//                
//                
//                mbg = new MultibleBackground(bgs);
                
                spriteRenderer.setBackground(new ColoredBackground(Color.CYAN));
                
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
                canvas.drawText("FPS: "+FPS, 200, 300, whitetext);
        }

}