package at.bartinger.candroid;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import at.bartinger.candroid.background.Background;
import at.bartinger.candroid.background.ColoredBackground;
import at.bartinger.candroid.ingame.Score;
import at.bartinger.candroid.renderer.Renderer;

public class CandroidSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	//TODO SpriteHolder
	//TODO Background

	//CANVAS
	//	private boolean mSizeChanged = true;

	private SurfaceHolder mHolder;
	public CandroidThread mCanvasThread;
	public int FPS,FPScount,allFPS,avgFPS = 0;

	//Touchscreen
	public boolean doTouch = false;
	public float touchPressure = 0;

	//Trackball
	private int trackballSensitivity = 5;


	//Accelerometer
	private SensorManager sensorManager;
	private boolean usesAccelerometer;
	private SensorEventListener listener;
	//
	//	private long lastShakeTimestamp=0;
	//	private double threshold=1.0d;
	//	private long timeBetweenShakes;

	private static final int FORCE_THRESHOLD = 350;
	private static final int TIME_THRESHOLD = 100;
	private static final int SHAKE_TIMEOUT = 500;
	private static final int SHAKE_DURATION = 1000;
	private static final int SHAKE_COUNT = 3;

	private SensorManager mSensorMgr;
	private float mLastX=-1.0f, mLastY=-1.0f, mLastZ=-1.0f;
	private long mLastTime;
	//	private OnShakeListener mShakeListener;
	private Context mContext;
	private int mShakeCount = 0;
	private long mLastShake;
	private long mLastForce;
	
	

	//KeyCodes




	//Score
	public Score score;

	public CandroidSurfaceView(Context context, boolean usesTrackball, boolean usesAccelerometer, double shakeStrongnes,long TimeBetweenShakes) {
		super(context);
		this.usesAccelerometer = usesAccelerometer;
		//		this.threshold=shakeStrongnes*shakeStrongnes;
		//		this.threshold=this.threshold*SensorManager.GRAVITY_EARTH*SensorManager.GRAVITY_EARTH;
		//		this.timeBetweenShakes = TimeBetweenShakes;
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_NORMAL);
		setFocusable(usesTrackball);
		if(usesAccelerometer){
			sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);

			listener = new SensorEventListener(){

				@Override
				public void onAccuracyChanged(Sensor arg0, int arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSensorChanged(SensorEvent e) {
					//Log.d("CanvasGame","drin1");
					if (e.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
						//Log.d("CanvasGame","drin2");
						long now = System.currentTimeMillis();

						if ((now - mLastForce) > SHAKE_TIMEOUT) {
							mShakeCount = 0;
							
						}

						if ((now - mLastTime) > TIME_THRESHOLD) {
							//Log.d("CanvasGame","drin4");
							long diff = now - mLastTime;
							float speed = Math.abs(e.values[0] + e.values[1] + e.values[2] - mLastX - mLastY - mLastZ) / diff * 10000;
							if (speed > FORCE_THRESHOLD) {
								if ((++mShakeCount >= SHAKE_COUNT) && (now - mLastShake > SHAKE_DURATION)) {
									mLastShake = now;
									mShakeCount = 0;
									Log.d("CanvasGame","drin5");
									onShaking(); 
								}
							}
							mLastForce = now;
						}
						mLastTime = now;
						mLastX = e.values[0];
						mLastY = e.values[1];
						mLastZ = e.values[2];
					}
					//					if (e.sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
					//						onAccelerometerChanged(
					//								e.values[0], 
					//								e.values[1], 
					//								e.values[2]);
					//						double netForce=e.values[0]*e.values[0];
					//
					//						netForce+=e.values[1]*e.values[1];
					//						netForce+=e.values[2]*e.values[2];
					//
					//						if (threshold<netForce) {
					//							lastShakeTimestamp=SystemClock.uptimeMillis();
					//							onShaking();
					//						}else{
					//							long now=SystemClock.uptimeMillis();
					//
					//							if (lastShakeTimestamp>0) {
					//								if (now-lastShakeTimestamp>timeBetweenShakes) {
					//									lastShakeTimestamp=0;
					//								}
					//							}
					//						}
					//
					//					}
				}

			};
			sensorManager.registerListener(listener,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_GAME);
		}
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


	public void onAccelerometerChanged(float x, float y, float z){}

	public void onShaking() {Log.d("CanvasGame","SHAKE");}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode){
		case 19:
			onTrackballUp();
			break;
		case 20:
			onTrackballDown();
			break;
		case 21:
			onTrackballLeft();
			break;
		case 22:
			onTrackballRight();
			break;
		case 23:
			onTrackballPress();
			break;
		case KeyEvent.KEYCODE_HOME:
			Log.d("CanvasGame","HOME PRESSED");
			break;
		case KeyEvent.KEYCODE_BACK:
			Log.d("CanvasGame","BACK PRESSED");
			break;
		case KeyEvent.KEYCODE_SEARCH:
			Log.d("CanvasGame","SEARCH PRESSED");
			break;
		case KeyEvent.KEYCODE_MENU:
			Log.d("CanvasGame","MENU PRESSED");
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}

	public void destroy(){
		if(usesAccelerometer){
			sensorManager.unregisterListener(listener);
		}
	}


	/**
	 * Here you update, calculate and move everything you need
	 * deltaTime is the time between the last update and now
	 * @param deltaTime
	 */
	public void onUpdate(long deltaTime){}


	public SurfaceHolder getSurfaceHolder() {
		return mHolder;
	}
	
	public void setBackground(Background bg){
		
	}

	/** Sets the user's renderer and kicks off the rendering thread. */
	public void setRendererAndStart(Renderer renderer) {
		mCanvasThread = new CandroidThread(this, renderer);
		mCanvasThread.start();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		//mCanvasThread.surfaceCreated();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// Surface will be destroyed when we return
		//mCanvasThread.surfaceDestroyed();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// Surface size or format has changed. This should not happen in this
		// example.
		//mCanvasThread.onWindowResize(w, h);
	}

	/** This should be called from the Activity */
//	public void onPause() {
//		mCanvasThread.onPause();
//	}
//
//	/** This should be called from the Activity */
//	public void onResume() {
//		mCanvasThread.onResume();
//	}

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
	 * This is called when the View begins drawing
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



}
