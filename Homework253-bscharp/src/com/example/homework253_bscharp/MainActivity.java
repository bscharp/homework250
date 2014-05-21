package com.example.homework253_bscharp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
	String TAG = "MainActiviy";
	Button dingButton;
	MediaPlayer mediaPlayer;
	Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d( TAG, "onCreate method ");
        setContentView(R.layout.activity_main);
        
        
        dingButton = (Button) findViewById(R.id.playDing);
        dingButton.setOnClickListener(this);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public void onClick(View v) {
    
    	 Log.d( TAG, "Button was tapped ");
    	 String text = dingButton.getText().toString();
    	 Log.d( TAG, "Button label is " + text);
    	 
    	 // Invoke the service that plays a sound
    	 if ( text.equals("Start"))
    	 {
    		 // test the sound
    		 //mediaPlayer = MediaPlayer.create(this, R.raw.windows_ding);
    	     //mediaPlayer.start();
    		 startService(new Intent(this, DingIntentService.class));
    	     dingButton.setText("Stop");
    	 }
    	 
    	 // Stop the service that plays a sound
    	 else if ( text.equals("Stop"))
    	 {
    		 // test the sound
    		 //if (mediaPlayer.isPlaying()) {
    	     //   mediaPlayer.stop(); 
    	     //   mediaPlayer.release();
    		 //}
    		 stopService(new Intent(this, DingIntentService.class));
    		 dingButton.setText("Start");
    	 }
    	
    }
    
    @Override
    protected void onStart() {
        super.onStart();
        Log.d( TAG, "onStart method ");
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        Log.d( TAG, "onResume method ");
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        Log.d( TAG, "onPause method ");
        
        // Create a pending intent
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.setAction("android.intent.action.MAIN");
        mainIntent.addCategory("android.intent.category.LAUNCHER");  
		PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);
		
		// Attach pending intent to a notification 
		notification =  new Notification.Builder(this)
		.setContentIntent(mainPendingIntent)
		.setContentTitle("Ding Service")
		.setContentText("Ding service is running")
		.setSmallIcon(R.drawable.ic_launcher)
		// AutoCancel doesn't seem to work
		.setAutoCancel(true)
		.getNotification();
		
		// Give notification to the notify manager
		NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notifyManager.notify(0, notification);
    }
    
    @Override
    protected void onStop() {
        super.onStop();
        Log.d( TAG, "onStop method ");
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d( TAG, "onDestroy method ");
        
        // Stop the service if this activity is stopped
        stopService(new Intent(this, DingIntentService.class));
    }

}
