package at.bartinger.candroid.sound;

import java.io.IOException;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.content.res.AssetFileDescriptor;
import at.bartinger.candroid.Constants;


public class BackgroundSound {

	private MediaPlayer aPlayer=new MediaPlayer();
	private float volume = 1;

	public BackgroundSound(Context context, String assetpath , boolean loop) {
		
		AssetFileDescriptor afd = null;
		try {
			afd = context.getAssets().openFd(assetpath);
			aPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			aPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			aPlayer.prepare();
			aPlayer.setLooping(loop);
		} catch (IOException e) {
			Log.e(Constants.LOGTAG, "Could not open " + assetpath);
		}
		
		
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
