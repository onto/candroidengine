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
		synchronized(renderables){
			renderables.add(r);
		}
	}

	public void removeRenderable(int location){
		synchronized(renderables){
			renderables.remove(location);
		}
	}

	public void removeRenderable(Renderable r){
		synchronized(renderables){
			renderables.remove(r);
		}
	}

	public void drawFrame(Canvas canvas) {

		canvas.drawColor(Color.BLACK);
		background.draw(canvas);
		background.update();
		synchronized(renderables){
			for (Renderable r : renderables) {
				r.update();
				r.draw(canvas);
			}
		}

	}


	public void setBackground(Background bg){
		background = bg;
	}



}
