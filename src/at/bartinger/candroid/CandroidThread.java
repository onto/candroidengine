package at.bartinger.candroid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import at.bartinger.candroid.renderer.Renderer;

/**
 * A generic Canvas rendering Thread. Delegates to a Renderer instance to do
 * the actual drawing.
 */
public class CandroidThread extends Thread {
	private boolean mDone;

	private Renderer mRenderer;
	private Runnable mEvent;
	private SurfaceHolder mSurfaceHolder;
	private CandroidSurfaceView view;

	private long pastTime;
	private long lastTime;
	public long deltaTime;


	public CandroidThread(CandroidSurfaceView view, Renderer renderer) {
		super();
		mDone = false;

		mRenderer = renderer;
		this.view = view;
		mSurfaceHolder = view.getHolder();
		setName("CandroidThread");
		lastTime = System.currentTimeMillis();
	}

	@Override
	public void run() {

		/*
		 * This is our main activity thread's loop, we go until
		 * asked to quit.
		 */

		while (!mDone) {

			//Timebased Movement
			long yetTime = System.currentTimeMillis();
			long deltaTime = yetTime - lastTime;
			pastTime+=deltaTime;
			view.FPScount++;
			if(view.FPScount == 10){

				view.FPS = (int)(1000d/deltaTime);
				view.allFPS+=view.FPS;

				view.avgFPS=view.allFPS/view.FPScount;
				view.FPScount = 0;
			}
			lastTime = yetTime;

			if (mEvent != null) {
				mEvent.run();
			}


//			if (mDone) {
//				break;
//			}

			Canvas canvas = mSurfaceHolder.lockCanvas();

			onUpdate(deltaTime);

			if (canvas != null) {

				view.onStartDrawing(canvas);

				mRenderer.drawFrame(canvas);
				
				view.onStopDrawing(canvas);
				mSurfaceHolder.unlockCanvasAndPost(canvas);

			}

		}
	}

	/**
	 * Override this for your updates in your game.
	 *              calculating, sprite-moving, animation updates, background updates, ...
	 * @param pastTime The time between the last update and now
	 */

	public void onUpdate(long pastTime){}



	//      public void onPause() {
	//              synchronized (this) {
	//                      mPaused = true;
	//              }
	//      }
	//
	//      /**
	//       * Get called from the Activity
	//       */
	//      public void onResume() {
	//              synchronized (this) {
	//                      mPaused = false;
	//                      notify();
	//              }
	//      }


	//don't call this from CanvasThread thread or it is a guaranteed deadlock!
	public void requestExitAndWait() {
		synchronized(this) {
			mDone = true;
			notify();
		}
		try {
			join();
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Queue an "event" to be run on the rendering thread.
	 * @param r the runnable to be run on the rendering thread.
	 */
	public void setSecondRunnable(Runnable r) {
		synchronized(this) {
			mEvent = r;
		}
	}

	public void clearSecondRunnable() {
		synchronized(this) {
			mEvent = null;
		}
	}

}