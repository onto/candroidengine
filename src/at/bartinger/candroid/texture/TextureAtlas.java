package at.bartinger.candroid.texture;

import java.util.ArrayList;

public class TextureAtlas {
	
	private ArrayList<Texture> textures;
	public boolean loadedQueue = false;
	
	public TextureAtlas() {
		textures = new ArrayList<Texture>();
	}

	public void addTexture(Texture tex){
		textures.add(tex);
	}

	public ArrayList<Texture> getQueue() {
		return textures;
	}
	
}
