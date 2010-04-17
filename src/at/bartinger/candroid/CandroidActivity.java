package at.bartinger.candroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class CandroidActivity extends Activity{

	public boolean isFullscreen = true;


	public void setFullscreen(boolean fullscreen){
		if(fullscreen){
			this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
	        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		}else{
			this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN); 

			this.getWindow().requestFeature(Window.FEATURE_CUSTOM_TITLE);
		}
	}
}
