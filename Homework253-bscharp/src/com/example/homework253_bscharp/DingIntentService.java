package com.example.homework253_bscharp;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

public class DingIntentService extends IntentService {
	
	MediaPlayer mediaPlayer;
	String TAG = "DingIntentService";
	static boolean continueDing = true;
	
	public DingIntentService() 
	{
		super("DingIntentService");
		Log.d(TAG, "constructor method");
	}
	
	
	  @Override
	    public void onCreate() {
		  Log.d(TAG, "onCreate method");
	      super.onCreate();
	    }
	
	  
	@Override
    public void onDestroy() {
		Log.d(TAG, "onDestroy method");
		
		// stop the sound
		continueDing = false;
		if (mediaPlayer.isPlaying()) {
	        mediaPlayer.stop(); 
	        mediaPlayer.release();
		 }  
		
		super.onDestroy();
    }


	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent method");
		
		// initialize the sound
		mediaPlayer = MediaPlayer.create(this, R.raw.windows_ding);
		continueDing = true;
	    
		// sound loop
	    try {
	    	while ( continueDing ) 
	    	{
	    		mediaPlayer.start();
	    		Thread.sleep(5000);
	    	}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
