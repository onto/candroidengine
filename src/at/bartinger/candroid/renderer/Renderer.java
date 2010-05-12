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
import at.bartinger.candroid.Renderable;

/** A generic renderer interface. */
public interface Renderer {
	
	int fps = 0;
	
	/**
	 * The list from where the Renderer get its Sources.
	 */
	public ArrayList<Renderable> renderables = new ArrayList<Renderable>();
   
    /**
     * Draw the current frame.
     * @param canvas The target canvas to draw into.
     */
    public void drawFrame(Canvas canvas);

}