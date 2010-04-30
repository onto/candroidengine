package at.bartinger.candroid.renderer;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import at.bartinger.candroid.Renderable;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;


public class SurfaceRenderer implements Renderer {

	private ArrayList<Renderable> renderables = new ArrayList<Renderable>();
	private Background background = new ColoredBackground(Color.green(100));

	public void setRenderables(ArrayList<Renderable> renderables) {
		this.renderables = renderables;
	}

	public void addRenderable(Renderable r) {
		renderables.add(r);
	}

	public void removeRenderable(int location){
		renderables.remove(location);
	}

	public void removeRenderable(Renderable r){
		renderables.remove(r);
	}

	public void drawFrame(Canvas canvas) {

		canvas.drawColor(Color.BLACK);
		background.draw(canvas);
		background.update();
		for (Renderable r : renderables) {
			r.update();
			r.draw(canvas);
		}


	}


	public void setBackground(Background bg){
		background = bg;
	}



}
