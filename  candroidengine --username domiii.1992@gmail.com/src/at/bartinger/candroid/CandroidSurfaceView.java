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

package at.bartinger.candroid;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import at.bartinger.candroid.renderer.Renderer;

public class CandroidSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	private SurfaceHolder mHolder;
	public CandroidThread mCanvasThread;
	public int FPS,FPScount,allFPS,avgFPS = 0;

	//Touchscreen
	public boolean doTouch = false;
	public float touchPressure = 0;


	public CandroidSurfaceView(Context context) {
		super(context);
		
		
		setFocusable(true);
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		int pressure = (int) (event.getPressure()*100);

		if(event.getAction() == MotionEvent.ACTION_DOWN){
			doTouch = true;
			onTouchDown(x,y,pressure);
			return true;
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			onTouchMove(x,y,pressure);
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			doTouch = false;
			onTouchUp(x,y,pressure);
			return super.onTouchEvent(event);
		}
		return super.onTouchEvent(event);
	}

	public void onTouchDown(int touchX, int touchY, int pressure){}
	public void onTouchMove(int touchX, int touchY, int pressure){}
	public void onTouchUp(int touchX, int touchY, int pressure){}

	@Override
	public boolean onTrackballEvent(MotionEvent event) {

		return super.onTrackballEvent(event);
	}

	public void onTrackballLeft(){}
	public void onTrackballRight(){}
	public void onTrackballUp(){}
	public void onTrackballDown(){}
	public void onTrackballPress(){}
	
	public void onDPadLeft(){}
	public void onDPadRight(){}
	public void onDPadUp(){}
	public void onDPadDown(){}
	public void onDPadCenter(){}
	
	public void onHomeButton(){}
	public void onBackButton(){}
	public void onSearchButton(){}
	public void onMenuButton(){}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode){
		case KeyEvent.KEYCODE_DPAD_UP:
			onDPadUp();
			onTrackballUp();
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			onDPadDown();
			onTrackballDown();
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			onDPadLeft();
			onTrackballLeft();
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			onDPadRight();
			onTrackballRight();
			break;
		case KeyEvent.KEYCODE_DPAD_CENTER:
			onDPadCenter();
			onTrackballPress();
			break;
		case KeyEvent.KEYCODE_HOME:
			onHomeButton();
			break;
		case KeyEvent.KEYCODE_BACK:
			onBackButton();
			break;
		case KeyEvent.KEYCODE_SEARCH:
			onSearchButton();
			break;
		case KeyEvent.KEYCODE_MENU:
			onMenuButton();
			break;
		}
		return super.onKeyDown(keyCode, event);
	}


	public void onDestroy(){

	}


	/**
	 * Here you update, calculate and move everything you need
	 * deltaTime is the time between the last update and now
	 * @param deltaTime
	 */
	public void onUpdate(){}


	public SurfaceHolder getSurfaceHolder() {
		return mHolder;
	}


	/** Sets the user's renderer and kicks off the rendering thread. */
	public void setRendererAndStart(Renderer renderer) {
		mCanvasThread = new CandroidThread(this, renderer);
		mCanvasThread.start();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(Constants.LOGTAG, "surfaceCreated");
		mCanvasThread.surfaceCreated();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Surface will be destroyed when we return
		onDestroy();
		mCanvasThread.requestExitAndWait();
		mCanvasThread.surfaceDestroyed();
		mHolder.removeCallback(this);

	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// Surface size or format has changed. This should not happen in this
		// example.
		//mCanvasThread.onWindowResize(w, h);
	}

	/** This should be called from the Activity */
	//      public void onPause() {
	//              mCanvasThread.onPause();
	//      }
	//
	//      /** This should be called from the Activity */
	//      public void onResume() {
	//              mCanvasThread.onResume();
	//      }

	/** This should be called from the Activity */
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		//mCanvasThread.onWindowFocusChanged(hasFocus);
	}

	/**
	 * Set an "event" to be run on the rendering thread.
	 * @param r the runnable to be run on the rendering thread.
	 */
	public void setSecondRunnable(Runnable r) {
		mCanvasThread.setSecondRunnable(r);
	}

	/** Clears the runnable event, if any, from the rendering thread. */
	public void clearSecondRunnable() {
		mCanvasThread.clearSecondRunnable();
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mCanvasThread.requestExitAndWait();
	}

	public void stopDrawing() {
		mCanvasThread.requestExitAndWait();
	}




	/**
	 * This is called when t bgs[0] = new ColoredBackground(Color.rgb(58, 115, 186));
//                bgs[1] = new SpriteBackground(clouds,0,0,320,480);
//                
//                
//                mbg = nhe View begins drawing
	 * Override this for your purposes!
	 * @param canvas draw with this Canvas
	 */
	public void onStartDrawing(Canvas canvas){}

	/**
	 * This is called when the View stops drawing
	 * Override this for your purposes!
	 * @param canvas draw with this Canvas
	 */
	public void onStopDrawing(Canvas canvas){}

	public void recycle(){}



}