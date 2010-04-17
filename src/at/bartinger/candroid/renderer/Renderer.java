package at.bartinger.candroid.renderer;

import java.util.ArrayList;

import android.graphics.Canvas;
import at.bartinger.candroid.Renderable;

/** A generic renderer interface. */
public interface Renderer {
	
	int fps = 0;
	public ArrayList<Renderable> sprites = new ArrayList<Renderable>();
   
    /**
     * Draw the current frame.
     * @param canvas The target canvas to draw into.
     */
    public void drawFrame(Canvas canvas);

}