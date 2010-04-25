package at.bartinger.candroid.sound;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundSound {

	private MediaPlayer aPlayer=new MediaPlayer();
	private float volume = 1;

	public BackgroundSound(Context context, int resId, boolean loop) {
		
		aPlayer= MediaPlayer.create(context, resId);
		aPlayer.setLooping(loop);
	}

	public void start(){
		if(!aPlayer.isPlaying()){
			aPlayer.start();
		}
	}

	public void stop(){
		if(aPlayer.isPlaying()){
			aPlayer.stop();
		}
	}
	
	public void setVolume(float v){
		aPlayer.setVolume(v, v);
		volume=v;
	}
	
	public void mute(){
		setVolume(0);
		volume=0;
	}
	
	public void toogleOn_Off(){
		if(volume == 0f) setVolume(1);
		else setVolume(0);
	}

}
