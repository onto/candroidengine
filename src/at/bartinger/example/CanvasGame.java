package at.bartinger.example;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import at.bartinger.candroid.CandroidActivity;
import at.bartinger.candroid.Constants;
import at.bartinger.candroid.net.Client;
import at.bartinger.candroid.net.GlobalClient;
import at.bartinger.candroid.net.LocalClient;
import at.bartinger.candroid.net.PacketListener;


public class CanvasGame extends CandroidActivity {

	private GameView mSurfaceView;
	GlobalClient gc;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSurfaceView = new GameView(this, true, false, 1.5d, 500);
		//setFullscreen(true);

		// We need to know the width and height of the display pretty soon,
		// so grab the information now.
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		// Now's a good time to run the GC.  Since we won't do any explicit
		// allocation during the test, the GC should stay dormant and not
		// influence our results.
		Runtime r = Runtime.getRuntime();
		r.gc();
		

		setContentView(mSurfaceView);

		//		TextView ip = (TextView) findViewById(R.id.yourip);
		//		
		//		
		//		gc = new GlobalClient(this, "91.115.166.240", 4445, 4444,false);
		//		try {
		//			ip.setText("InetIP: " + gc.getExternalIP());
		//		} catch (MalformedURLException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
		//		gc.start();

		//		LocalClient lc = new LocalClient("", 4444, new PacketListener() {
		//			
		//			@Override
		//			public void onRecivePacket(byte[] data) {
		//				Log.d(Constants.LOGTAG, new String(data));
		//				
		//			}
		//		});
	}


	/** Recycles all of the bitmaps loaded in onCreate(). */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//		gc.close();
		mSurfaceView.clearSecondRunnable();
		mSurfaceView.stopDrawing();

		//Call here the recycle method from every Sprite

	}

}