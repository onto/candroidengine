package at.bartinger.candroid.renderer;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import at.bartinger.candroid.Renderable;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;


public class SurfaceRenderer implements Renderer {

        private ArrayList<Renderable> mSprites = new ArrayList<Renderable>();
        private Background background = new ColoredBackground(Color.green(100));

        public void setSprites(ArrayList<Renderable> sprites) {
                mSprites = sprites;
        }

        public void addSprite(Renderable sprite) {
                mSprites.add(sprite);
        }

        public void removeSprite(int location){
                mSprites.remove(location);
        }

        public void removeSprite(Renderable r){
                mSprites.remove(r);
        }

        public void drawFrame(Canvas canvas) {
                if (mSprites != null) {
                        canvas.drawColor(Color.BLACK);
                        background.draw(canvas);
                        background.update();
                        for (Renderable r : mSprites) {
                                r.update();
                                r.draw(canvas);
                        }
                }
                
        }


        public void setBackground(Background bg){
                background = bg;
        }
        


}
