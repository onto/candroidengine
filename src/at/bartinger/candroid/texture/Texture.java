package at.bartinger.candroid.texture;

import android.graphics.Bitmap;

public class Texture {
	
	public Bitmap tex = null;
	public String path;
	public boolean isLoaded = false;
	
	public Texture(String path) {
		this.path = path;
	}

}
